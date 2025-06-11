package sparklemod.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.SparkleUnexpectedPower;
import sparklemod.util.CardStats;

//Heel kick - attack, 1 energy - Deal 5(9) damage to a random enemy, then randomly inflict 1(2) weaken or 1 vulnerable. Gain 1 Unexpected.
public class HeelKick extends BaseCard {
    public static final String ID = makeID(HeelKick.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_DAMAGE = 5;
    private static final int UPGRADED_DAMAGE = 9;
    private static final int BASE_WEAK = 1;
    private static final int UPGRADED_WEAK = 2;
    private static final int BASE_VULNERABLE = 1;

    public HeelKick() {
        super(ID, info);

        setDamage(BASE_DAMAGE, UPGRADED_DAMAGE);
        setCustomVar("sparkleHeelKickWeak", VariableType.DAMAGE, BASE_WEAK, UPGRADED_WEAK);
        setCustomVar("sparkleHeelKickVulnerable", VariableType.DAMAGE, BASE_VULNERABLE, BASE_VULNERABLE);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int numRepeats;

        if(p.hasPower(SparkleUnexpectedPower.POWER_ID)) {
            numRepeats = p.getPower(SparkleUnexpectedPower.POWER_ID).amount;
        }
        else {
            numRepeats = 0;
        }

        for(int i=0;i <= numRepeats; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            int rnd = AbstractDungeon.cardRandomRng.random(0,1);
            if (rnd == 0) {
                addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 1, false), customVar("sparkleHeelKickWeak")));
            }
            else {
                addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false), customVar("sparkleHeelKickVulnerable")));
            }
        }

        //Add unexpected
        addToBot(new ApplyPowerAction(p, p, new SparkleUnexpectedPower(p, 1)));
    }
}
