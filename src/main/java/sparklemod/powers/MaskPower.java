package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class MaskPower extends BasePower {
    public static final String POWER_ID = makeID(MaskPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static int countHits;

    public MaskPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        countHits = 0;
        updateDescription();
    }

    public void updateDescription() {
        StringBuilder temp = new StringBuilder(DESCRIPTIONS[0]);

        if(amount > 1) {
            temp.append(amount);
            temp.append(DESCRIPTIONS[2]);
        }
        else {
            temp.append(DESCRIPTIONS[1]);
        }

        temp.append(DESCRIPTIONS[3]);

        this.description = temp.toString();
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    //TODO: Low priority, clean up this code. Right now it's doing the same thing a couple places, but works.
    //Could be made FAR more readable.
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        //don't negate self damage, aka SoulGlad or poison
        if(info.owner == owner) {
            return damageAmount;
        }

        //no need to reduce zero damage.
        if(damageAmount < 1 || owner.currentBlock >= damageAmount) {
            return damageAmount;
        }

        //count the number of hits each turn. If the number of hits exceeds amount, remove the power. This
        //hopefully prevents blocking multihits with a single Mask.
        countHits++;
        if(countHits > amount) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
            //addToBot(new ApplyPowerAction(owner, owner, new MaskEnergyGainPower(owner, 1)));
            return damageAmount;
        }

        if(amount > 0) {
            //remove one stack, negate the damage, add energy gain action
            addToBot(new ReducePowerAction(owner, owner, POWER_ID, 1));
            addToBot(new ApplyPowerAction(owner, owner, new MaskEnergyGainPower(owner, 1)));
            if(amount <= 0) {
                addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
            }
            return 0;
        }
        else { //failsafe. this shouldn't happen.
            addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
            return damageAmount;
        }
    }
}
