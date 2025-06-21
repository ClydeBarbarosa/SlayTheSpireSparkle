package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class MaskEnergyGainPower extends BasePower {
    public static final String POWER_ID = makeID(MaskEnergyGainPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private int energyGain = 0;

    public MaskEnergyGainPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        energyGain += amount;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + energyGain + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        int choice = AbstractDungeon.cardRandomRng.random(1, 2);
        if(choice == 1) {
            addToBot(new GainEnergyAction(energyGain));
        }
        else {
            addToBot(new LoseEnergyAction(energyGain));
        }
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }
}
