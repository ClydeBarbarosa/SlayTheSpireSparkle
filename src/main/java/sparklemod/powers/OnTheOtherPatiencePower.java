package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sparklemod.cards.BaseCard;

import static sparklemod.SparkleMod.makeID;

public class OnTheOtherPatiencePower extends BasePower {
    public static final String POWER_ID = makeID(OnTheOtherPatiencePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private final int minAmount;
    private final int maxAmount;
    //originally I had these amounts handed down from the card applying the power, but I couldn't get it to stack right. sigh.
    private static final int BASE_AMOUNT = 1;
    private static final int MAX_AMOUNT = 4;
    private static final int UPGRADED_MAX_AMOUNT = 6;

    public OnTheOtherPatiencePower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        minAmount = BASE_AMOUNT;
        maxAmount = (upgraded ? UPGRADED_MAX_AMOUNT : MAX_AMOUNT);

        updateDescription();
    }

    public void stackPower(int amount) {
        super.stackPower(amount);

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + minAmount + DESCRIPTIONS[1] + maxAmount + DESCRIPTIONS[2];
    }

    @Override
    public void atStartOfTurn() {
        int strengthAmount = BaseCard.randomIntWithVariance(minAmount, maxAmount);
        AbstractCreature p = AbstractDungeon.player;

        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, strengthAmount)));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, strengthAmount)));

        //Gotta make sure to remove this when we're done applying the power.

        addToBot(new RemoveSpecificPowerAction(p, p, POWER_ID));
    }
}
