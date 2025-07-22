package sparklemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class HereHoldThisPower extends BasePower {
    public static final String POWER_ID = makeID(HereHoldThisPower.class.getSimpleName());
    private static int bombOffsetID = 0;
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final int damage;

    public HereHoldThisPower(AbstractCreature owner, int amount, int damage) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.ID = POWER_ID + bombOffsetID;
        bombOffsetID++;
        this.damage = damage;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if(this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[3] + this.damage + DESCRIPTIONS[4];
        }
        else {
            this.description = DESCRIPTIONS[2] + DESCRIPTIONS[3] + this.damage + DESCRIPTIONS[4];
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount == 1) {
                addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SMASH));
            }
        }
    }
}
