package sparklemod.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Sad mask - skill, 3 energy - gain 0-2 vulnerable, 0-2 weak, 0-2 frail. Inflict the same conditions to a target enemy. (twice). Fixed.
public class SadMask extends BaseCard {
    public static final String ID = makeID(SadMask.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int MINIMUM_WEAK = 0;
    private static final int MAXIMUM_WEAK = 2;
    private static final int MINIMUM_VULNERABLE = 0;
    private static final int MAXIMUM_VULNERABLE = 2;
    private static final int MINIMUM_FRAIL = 0;
    private static final int MAXIMUM_FRAIL = 2;
    private static final int NUMBER_TIMES = 1;
    private static final int UPGRADED_NUMBER_TIMES = 2;

    public SadMask() {
        super(ID, info);

        setCustomVar("SadMaskMinimumWeak", MINIMUM_WEAK);
        setCustomVar("SadMaskMinimumVulnerable", MINIMUM_VULNERABLE);
        setCustomVar("SadMaskMinimumFrail", MINIMUM_FRAIL);
        setCustomVar("SadMaskMaximumWeak", MAXIMUM_WEAK);
        setCustomVar("SadMaskMaximumVulnerable", MAXIMUM_VULNERABLE);
        setCustomVar("SadMaskMaximumFrail", MAXIMUM_FRAIL);

        upgradesDescription = true;
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int numTimes;
        if(upgraded) {
            numTimes = UPGRADED_NUMBER_TIMES;
        }
        else {
            numTimes = NUMBER_TIMES;
        }

        int weakAmount = randomIntWithoutVariance(MINIMUM_WEAK, MAXIMUM_WEAK);
        int vulnerableAmount = randomIntWithoutVariance(MINIMUM_VULNERABLE, MAXIMUM_VULNERABLE);
        int frailAmount = randomIntWithoutVariance(MINIMUM_FRAIL, MAXIMUM_FRAIL);

        //apply to player
        if(weakAmount > 0) {
            addToBot(new ApplyPowerAction(p, p, new WeakPower(p, weakAmount, true), weakAmount));
        }
        if(vulnerableAmount > 0) {
            addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, vulnerableAmount, true), vulnerableAmount));
        }
        if(frailAmount > 0) {
            addToBot(new ApplyPowerAction(p, p, new FrailPower(p, frailAmount, true), frailAmount));
        }

        //apply to target monster
        for(int i = 0; i < numTimes; i++) {
            if(weakAmount > 0) {
                addToBot(new ApplyPowerAction(m, p, new WeakPower(m, weakAmount, false), weakAmount));
            }
            if(vulnerableAmount > 0) {
                addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, vulnerableAmount, false), vulnerableAmount));
            }
            if(frailAmount > 0) {
                addToBot(new ApplyPowerAction(m, p, new FrailPower(m, frailAmount, false), frailAmount));
            }
        }
    }
}
