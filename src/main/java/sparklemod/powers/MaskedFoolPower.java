package sparklemod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import sparklemod.actions.MaskedFoolCardCostAction;
import sparklemod.cards.rare.MaskedFool;

import static sparklemod.SparkleMod.makeID;

public class MaskedFoolPower extends BasePower {
    public static final String POWER_ID = makeID(MaskedFoolPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MaskedFoolPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new MaskedFoolCardCostAction(this));
    }
}