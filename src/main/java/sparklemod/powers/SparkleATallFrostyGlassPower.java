package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static sparklemod.SparkleMod.makeID;

public class SparkleATallFrostyGlassPower extends BasePower {
    public static final String POWER_ID = makeID(SparkleATallFrostyGlassPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SparkleATallFrostyGlassPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        addToBot(new ApplyPowerAction(owner, owner, new ThornsPower(owner, amount)));
        return super.onAttacked(info, damageAmount);
    }
}
