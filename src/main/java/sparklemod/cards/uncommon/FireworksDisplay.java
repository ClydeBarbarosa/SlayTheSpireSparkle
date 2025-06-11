package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

public class FireworksDisplay extends BaseCard {
    public static final String ID = makeID(FireworksDisplay.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ALL, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            3 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_DAMAGE = 5;
    private static final int BASE_NUM_TIMES = 2;
    private static final int BASE_MAX_NUM_TIMES = 4;
    private static final int UPGRADED_MAX_NUM_TIMES = 2;

    public FireworksDisplay() {
        super(ID, info);

        setDamage(BASE_DAMAGE);
        setCustomVar("SparkleFireworksDisplayNumBaseHits", BASE_NUM_TIMES);
        setCustomVar("SparkleFireworksDisplayNumMaxHits", BASE_MAX_NUM_TIMES, UPGRADED_MAX_NUM_TIMES);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int numTimes = AbstractDungeon.cardRandomRng.random(customVar("SparkleFireworksDisplayNumBaseHits"), customVar("SparkleFireworksDisplayNumMaxHits"));

        for(int i = 0; i < numTimes; i++) {
            addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }
}
