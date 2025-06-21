package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.SparkleUnexpectedPower;
import sparklemod.util.CardStats;

//Dodge this! - attack, 2 energy - Slap an enemy with a car for 7(10) damage. Gain 1 Unexpected.
public class DodgeThis extends BaseCard {
    public static final String ID = makeID(DodgeThis.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 3;

    public DodgeThis() {
        super(ID, info);

        setCustomVar("SparkleDodgeThisDamage", BASE_DAMAGE, DAMAGE_UPGRADE);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int amount;
        if (p.hasPower(SparkleUnexpectedPower.POWER_ID)) {
            AbstractPower pow = p.getPower(SparkleUnexpectedPower.POWER_ID);
            amount = pow.amount + 1;
        }
        else {
            amount = 1;
        }
        for (int i = 0; i < amount; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, customVar("DodgeThisDamage"), DamageInfo.DamageType.NORMAL)));
        }
        addToBot(new ApplyPowerAction(p, p, new SparkleUnexpectedPower(p, 1), 1));
    }
}
