package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import sparklemod.cards.BaseCard;

import static sparklemod.SparkleMod.makeID;

public class SheCanBreakMePower extends BasePower {
    public static final String POWER_ID = makeID(SheCanBreakMePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private static final int HEADS_BLOCK_AMOUNT = 5;
    private static final int TAILS_FRAIL_AMOUNT = 1;
    private static final int TAILS_BLOCK_AMOUNT = 3;

    private static boolean lastCoinFlipWasTails = false;

    public SheCanBreakMePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

    }

    @Override
    public void atStartOfTurn() {
        int choice = BaseCard.randomIntWithoutVariance(1, 2);
        if(choice == 1) {
            addToBot(new GainBlockAction(owner, HEADS_BLOCK_AMOUNT * amount));
            lastCoinFlipWasTails = false;
        }
        else {
            addToBot(new GainBlockAction(owner, TAILS_BLOCK_AMOUNT * amount));
            addToBot(new ApplyPowerAction(owner, owner, new FrailPower(owner, TAILS_FRAIL_AMOUNT, true)));
            lastCoinFlipWasTails = true;
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(lastCoinFlipWasTails) {
            addToBot(new ReducePowerAction(owner, owner, FrailPower.POWER_ID, 1));
        }
    }
}
