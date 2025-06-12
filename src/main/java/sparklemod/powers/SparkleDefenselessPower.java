package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class SparkleDefenselessPower extends BasePower {
    public static final String POWER_ID = makeID(SparkleDefenselessPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public SparkleDefenselessPower (AbstractCreature owner, int amount, AbstractCreature target) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        this.source = target;
    }

    public void atStartOfTurn() {
        if(source.currentBlock>0) {
            addToBot(new LoseBlockAction(source, source, source.currentBlock));
        }
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
