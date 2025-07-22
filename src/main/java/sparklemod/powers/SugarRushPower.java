package sparklemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class SugarRushPower extends BasePower {
    public static final String POWER_ID = makeID(SugarRushPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SugarRushPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new DamageAction(p, new DamageInfo(p, amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
        addToBot(new ReducePowerAction(p, p, POWER_ID, 1));
    }
}
