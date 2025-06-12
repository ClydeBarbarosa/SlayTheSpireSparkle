package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Dance partner - skill, 1(2) energy - For each enemy, if it is attacking, inflict 1(2) Weak, otherwise inflict 1(2) vulnerable.
public class DancePartner extends BaseCard {
    public static final String ID = makeID(DancePartner.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_AMOUNT = 1;
    private static final int UPGRADED_ADDITIONAL_AMOUNT = 1;
    public DancePartner() {
        super(ID, info);

        setCustomVar("DancePartnerAmount", BASE_AMOUNT, UPGRADED_ADDITIONAL_AMOUNT);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if(!mo.isDeadOrEscaped()) {
                if(mo.intent == AbstractMonster.Intent.ATTACK ||
                    mo.intent == AbstractMonster.Intent.ATTACK_BUFF ||
                    mo.intent == AbstractMonster.Intent.ATTACK_DEBUFF ||
                    mo.intent == AbstractMonster.Intent.ATTACK_DEFEND ) {
                    addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, customVar("DancePartnerAmount"), false)));
                }
                else {
                    addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, customVar("DancePartnerAmount"), false)));
                }
            }
        }
    }
}
