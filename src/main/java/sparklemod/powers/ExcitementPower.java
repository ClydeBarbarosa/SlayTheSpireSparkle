package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import sparklemod.actions.ExcitementUpdateAction;

import static sparklemod.SparkleMod.makeID;

public class ExcitementPower extends BasePower {
    public static final String POWER_ID = makeID(ExcitementPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private static final int BLOCK_AMOUNT_PER_STACK = 1;
    private static final int UPGRADED_BLOCK_BONUS = 3;
    private final boolean upgraded;

    public ExcitementPower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.upgraded = upgraded;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + calculateBlock() + DESCRIPTIONS[1];
    }

    private int calculateBlock() {
        int number_of_stacks = this.amount +
                (owner.hasPower(VariancePower.POWER_ID) ? owner.getPower(VariancePower.POWER_ID).amount : 0) +
                (owner.hasPower(LoadedDicePower.POWER_ID) ? owner.getPower(LoadedDicePower.POWER_ID).amount : 0) +
                (owner.hasPower(SugarRushPower.POWER_ID) ? owner.getPower(SugarRushPower.POWER_ID).amount : 0) +
                (owner.hasPower(MaskPower.POWER_ID) ? owner.getPower(MaskPower.POWER_ID).amount : 0);

        return BLOCK_AMOUNT_PER_STACK * number_of_stacks + (this.upgraded ? UPGRADED_BLOCK_BONUS : 0);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        this.updateDescription();
        addToBot(new ExcitementUpdateAction());
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        addToBot(new ExcitementUpdateAction());
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new GainBlockAction(owner, calculateBlock()));
        //yeah, it looks redundant. It also works. Sometimes one triggers and the other doesn't. I don't know why.
        updateDescription();
        addToBot(new ExcitementUpdateAction());
    }
}
