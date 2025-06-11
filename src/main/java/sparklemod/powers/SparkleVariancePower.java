package sparklemod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class SparkleVariancePower extends BasePower {
    public static final String POWER_ID = makeID(SparkleVariancePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SparkleVariancePower (AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
}
