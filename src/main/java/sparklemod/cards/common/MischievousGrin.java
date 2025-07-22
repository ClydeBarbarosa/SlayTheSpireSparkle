package sparklemod.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import sparklemod.cards.BaseCard;

import sparklemod.character.SparkleCharacter;
import sparklemod.powers.EnergizedPower;
import sparklemod.util.CardStats;

//Mischievous grin - skill, 2(1) - gain 2 energy and draw a card next turn.
public class MischievousGrin extends BaseCard {
    public static final String ID = makeID(MischievousGrin.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int DRAW_AMOUNT = 1;

    public MischievousGrin() {
        super (ID, info);

        setCostUpgrade(1);
        this.upgradeCost=true;

        this.setMagic(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, 2), 2));
        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, DRAW_AMOUNT)));
    }
}
