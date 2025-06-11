package sparklemod.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

public class DommyVibes extends BaseCard {
    public static final String ID = makeID(DommyVibes.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            3 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //I could use just two variables, but left it this way in case i want to tweak the amounts
    private static final int BASE_WEAK = 2;
    private static final int UPGRADED_WEAK = 2;
    private static final int BASE_VULNERABLE = 2;
    private static final int UPGRADED_VULNERABLE = 2;

    public DommyVibes() {
        super(ID, info);

        setCustomVar("SparkleDommyVibesWeakAmount", BASE_WEAK, UPGRADED_WEAK);
        setCustomVar("SparkleDommyVibesVulnerableAmount", BASE_VULNERABLE, UPGRADED_VULNERABLE);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, customVar("SparkleDommyVibesWeakAmount"),false), customVar("SparkleDommyVibesWeakAmount")));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, customVar("SparkleDommyVibesVulnerableAmount"),false), customVar("SparkleDommyVibesVulnerableAmount")));
    }
}
