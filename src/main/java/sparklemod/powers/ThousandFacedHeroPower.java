package sparklemod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;

import static sparklemod.SparkleMod.makeID;

public class ThousandFacedHeroPower extends BasePower {
    public static final String POWER_ID = makeID(ThousandFacedHeroPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ThousandFacedHeroPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        //complicated, but: if you have Red Herring, and then if you have Mask, return 1.0F + 0.1F * Mask amount, otherwise return 1.0F
        float multiplier = 0.1F;

        if(owner.hasPower(RedHerringPower.POWER_ID)) {
            if (owner.hasPower((MaskPower.POWER_ID))) {
                multiplier *= owner.getPower(MaskPower.POWER_ID).amount;
            }
        }

        multiplier += 1.0F;

        return damage * multiplier;
        //return (owner.hasPower(RedHerringPower.POWER_ID) ? (owner.hasPower(MaskPower.POWER_ID)? owner.getPower(MaskPower.POWER_ID).amount * 0.1F : 1.0F) : 1.0F);
    }
}
