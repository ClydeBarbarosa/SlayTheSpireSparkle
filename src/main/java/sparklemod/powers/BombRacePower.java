package sparklemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class BombRacePower extends BasePower {
    public static final String POWER_ID = makeID(BombRacePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private static int bombOffset = 0;
    private static final int BOMB_DAMAGE = 8;
    //why 1/2 damage? the upgraded version lets the Torii save your stacks.
    private static final int BOMB_DAMAGE_MINIMUM = 1;
    private static final int BOMB_DAMAGE_UPGRADED_MINIMUM = 2;
    private final int damageMinimum;
    private static final int BASE_DURATION = 3;
    private int duration;

    public BombRacePower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.ID = POWER_ID + bombOffset;
        bombOffset++;
        damageMinimum = (upgraded ? BOMB_DAMAGE_UPGRADED_MINIMUM : BOMB_DAMAGE_MINIMUM);
        this.duration = BASE_DURATION;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.damageMinimum + DESCRIPTIONS[1] + BOMB_DAMAGE + DESCRIPTIONS[2];
        switch(duration) {
            case 3:
                description += DESCRIPTIONS[3];
                break;
            case 2:
                description += DESCRIPTIONS[4];
                break;
            case 1:
                description += DESCRIPTIONS[5];
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.duration--;
        this.flash();
        updateDescription();
        if(duration == 0) {
            addToBot(new DamageAllEnemiesAction((AbstractPlayer) owner, this.amount * BOMB_DAMAGE, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
        }
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if(!(info.owner == this.owner)) {
            if(damageAmount > this.damageMinimum) {
                addToBot(new ReducePowerAction(owner, owner, this.ID, 1));
            }
        }
    }
}
