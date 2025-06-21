package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static sparklemod.SparkleMod.makeID;

public class CaptivatingPerformancePower extends BasePower {
    private static final String POWER_ID = makeID(CaptivatingPerformancePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private int removeAmount = 0;

    public CaptivatingPerformancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atEndOfRound() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        if(removeAmount != 0) {
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, -removeAmount)));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(power.ID.equals(StrengthPower.POWER_ID)) {
            //addToBot(new ApplyPowerAction(target, source, new StrengthPower(target, -power.amount)));
            removeAmount += power.amount;
        }
    }
}
