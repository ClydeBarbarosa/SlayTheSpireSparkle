package sparklemod.cards.summoned;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

public class IOCLoseStrength extends BaseCard {
    public static final String ID = makeID(IOCLoseStrength.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.SPECIAL, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //private final int amountToLose;
    private static final int BASE_STRENGTH = 1;
    private static final int UPGRADE_STRENGTH = 1;


    public IOCLoseStrength() {
        this(false);
    }

    public IOCLoseStrength(boolean upgrade) {
        super(ID, info);

        setExhaust(true);
        setEthereal(true);
        setCustomVar("IOCStrLoss", BASE_STRENGTH, UPGRADE_STRENGTH);
        if(upgrade) {
            this.upgrade();
        }
    }

    public void onChoseThisOption() {
        AbstractCard c = this.makeCopy();
        if(upgraded) {
            c.upgrade();
        }
        addToBot(new MakeTempCardInHandAction(c));
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -customVar("IOCStrLoss"))));
    }
}
