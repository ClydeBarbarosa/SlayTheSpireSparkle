package sparklemod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.UnexpectedPower;
import sparklemod.util.CardStats;

//Unexpected Dividend - skill, 1(0) energy - Draw a card.  Unexpected.
public class UnexpectedDividend extends BaseCard {
    public static final String ID = makeID(UnexpectedDividend.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public UnexpectedDividend () {
        super(ID, info);
        this.setCostUpgrade(0);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int numDraw = (p.hasPower(UnexpectedPower.POWER_ID) ? p.getPower(UnexpectedPower.POWER_ID).amount + 1 : 1);
        addToBot(new DrawCardAction(p, numDraw));
        addToBot(new ApplyPowerAction(p, p, new UnexpectedPower(p, 1)));
    }
}
