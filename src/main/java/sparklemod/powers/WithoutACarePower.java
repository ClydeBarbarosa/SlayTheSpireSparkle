package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

//what's the difference between this and NoBlockPower? This doesn't stack. All stacks are removed at the
//start of your turn regardless of how many there are.
public class WithoutACarePower extends BasePower {
    public static final String POWER_ID = makeID(WithoutACarePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public WithoutACarePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public float modifyBlockLast(float blockAmount) {
        return 0.0f;
    }
}
