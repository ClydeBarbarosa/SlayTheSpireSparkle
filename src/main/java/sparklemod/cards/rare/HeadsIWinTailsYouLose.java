package sparklemod.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Heads I win, tails you lose - skill, 1(0) energy - Flip a coin, if heads, gain one energy and draw a card, if tails, deal 9(15) damage and exhaust.
public class HeadsIWinTailsYouLose extends BaseCard {

    public static final String ID = makeID(HeadsIWinTailsYouLose.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_DAMAGE = 9;
    private static final int UPGRADED_DAMAGE_GAIN = 6;
    private static final int ENERGY_GAIN = 2;
    private static final int NUM_CARDS = 1;

    public HeadsIWinTailsYouLose() {
        super(ID, info);
        setDamage(BASE_DAMAGE, UPGRADED_DAMAGE_GAIN);
        setCustomVar("HIWTYLEnergyGain", ENERGY_GAIN);
        setCustomVar("HIWTYLNumCards", NUM_CARDS);

        //setCostUpgrade(0);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int coinToss = AbstractDungeon.cardRandomRng.random(1, 2);

        if(coinToss == 1) {
            //heads, I win! Gain 1 energy and draw a card
            addToBot(new GainEnergyAction(ENERGY_GAIN));
            addToBot(new DrawCardAction(NUM_CARDS));
        }
        else {
            //tails, you lose! Deal damage and exhaust.
            this.exhaust = true;
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            addToBot(new ExhaustSpecificCardAction(this, p.discardPile));
        }
    }
}
