package sparklemod.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sparklemod.SparkleMod;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.UnexpectedPower;
import sparklemod.util.CardStats;

//Spanish Inquisition - skill, 2 energy - Randomly either gain 7(12) block, deal 7(10) damage to a random enemy,
//gain 1 strength, or cause a random enemy to lose 1 strength. Gain 1 Unexpected.
public class SpanishInquisition extends BaseCard {
    public static final String ID = makeID(SpanishInquisition.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_BLOCK = 7;
    private static final int BLOCK_UPGRADE = 5;
    private static final int BASE_DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 3;
    private static final int STRENGTH_GAIN_AMOUNT = 1;
    private static final int STRENGTH_LOSS_AMOUNT = 1;

    public SpanishInquisition () {
        super(ID, info);

        setCustomVar("SpanishInquisitionBlockAmount", BASE_BLOCK, BLOCK_UPGRADE);
        setCustomVar("SpanishInquisitionDamageAmount", BASE_DAMAGE, DAMAGE_UPGRADE);
        //setCustomVar("SpanishInquisitionStrengthGain", STRENGTH_GAIN_AMOUNT);
        //setCustomVar("SpanishInquisitionStrengthLoss", STRENGTH_LOSS_AMOUNT);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int numTimes = (p.hasPower(UnexpectedPower.POWER_ID) ? p.getPower(UnexpectedPower.POWER_ID).amount + 1 : 1);
        for(int i = 0; i < numTimes; i++) {
            int choice = randomIntWithoutVariance(1, 4);
            AbstractMonster target = AbstractDungeon.getRandomMonster();

            switch (choice) {
                case 1:
                    addToBot(new TextAboveCreatureAction(p, "Block!"));
                    addToBot(new GainBlockAction(p, customVar("SpanishInquisitionBlockAmount")));
                    break;
                case 2:
                    addToBot(new TextAboveCreatureAction(target, "Damage!"));
                    addToBot(new DamageAction(target, new DamageInfo(p, customVar("SpanishInquisitionDamageAmount")), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                    break;
                case 3:
                    addToBot(new TextAboveCreatureAction(p, "Empower!"));
                    addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, STRENGTH_GAIN_AMOUNT)));
                    break;
                case 4:
                    addToBot(new TextAboveCreatureAction(target, "Hamper!"));
                    addToBot(new ApplyPowerAction(target, p, new StrengthPower(target, -STRENGTH_LOSS_AMOUNT)));
                    break;
                default:
                    SparkleMod.logger.info("Error in Spanish Inquisition: expected 1-4 for choice, got {}.", choice);
            }
        }
        addToBot(new ApplyPowerAction(p, p, new UnexpectedPower(p, 1)));
    }
}
