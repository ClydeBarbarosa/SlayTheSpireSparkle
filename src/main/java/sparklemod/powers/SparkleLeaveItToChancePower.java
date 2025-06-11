package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class SparkleLeaveItToChancePower extends BasePower {
    public static final String POWER_ID = makeID( "SparkleLeaveItToChancePower" );
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    //private int blockAmount;
    private int energyAmount;

    public SparkleLeaveItToChancePower(AbstractCreature owner, int amount, int energyAmount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        //this.blockAmount = blockAmount;
        this.energyAmount = energyAmount;
        this.updateDescription();
    }

    public void stackPower (int stackAmount) {
        super.stackPower(stackAmount);
        this.energyAmount++;
        //this.blockAmount += stackAmount;
    }

    @Override
    public void atStartOfTurn() {
        int choice = AbstractDungeon.cardRandomRng.random(1, 2);

        if(choice == 1) {
            //gain energy
            addToTop(new GainEnergyAction(energyAmount));
        }
        else {
            //gain block
            addToTop(new GainBlockAction(AbstractDungeon.player,amount));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + energyAmount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
