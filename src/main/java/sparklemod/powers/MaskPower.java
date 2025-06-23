package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class MaskPower extends BasePower {
    public static final String POWER_ID = makeID(MaskPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private static int negateAmount = 0;
    private static int energyAmount = 0;

    public MaskPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        negateAmount += amount;
        energyAmount += amount;
        updateDescription();
    }

    public void updateDescription() {
        StringBuilder temp = new StringBuilder(DESCRIPTIONS[0] + negateAmount);

        if(negateAmount > 1) {
            temp.append(DESCRIPTIONS[2]);
        }
        else {
            temp.append(DESCRIPTIONS[1]);
        }

        temp.append(DESCRIPTIONS[3]);
        temp.append(energyAmount);
        temp.append(DESCRIPTIONS[4]);

        this.description = temp.toString();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractCreature p = AbstractDungeon.player;
        if(negateAmount > 0) {
            //remove one stack, negate the damage, add energy gain action
            negateAmount--;
            addToBot(new ApplyPowerAction(p, p, new MaskEnergyGainPower(p, energyAmount)));
            if(negateAmount <= 0) {
                addToBot(new RemoveSpecificPowerAction(p, p, POWER_ID));
            }
            return 0;
        }
        else { //failsafe. this shouldn't happen.
            addToBot(new RemoveSpecificPowerAction(p, p, POWER_ID));
            return damageAmount;
        }
    }
}
