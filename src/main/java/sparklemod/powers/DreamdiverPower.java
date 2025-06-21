package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

//Dreamdiver - skill, 1(0) energy - increase the damage of your next attack this turn by 25%.
public class DreamdiverPower extends BasePower {
    public static final String POWER_ID = makeID(DreamdiverPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private static final float BASE_DAMAGE_INCREASE = 0.25F;
    private static final float DAMAGE_PER_AMOUNT = BASE_DAMAGE_INCREASE * 100;

    public DreamdiverPower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        if(upgraded) {
            //add an extra stack if upgraded
            this.amount++;
        }
    }

    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + (DAMAGE_PER_AMOUNT * amount) + DESCRIPTIONS[1];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            if(this.amount == 0) {
                addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
            }
            else {
                addToBot(new ReducePowerAction(owner, owner, POWER_ID, 1));
            }
        }
    }

    //increase damage by 25% per stack.
    public float atDamageGive(float damage, DamageInfo.DamageType type) {

        return (type == DamageInfo.DamageType.NORMAL ? damage * (1.0F + (BASE_DAMAGE_INCREASE * amount)) : damage);
    }

}
