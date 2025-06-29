package sparklemod.powers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class RedHerringPower extends BasePower {
    public static final String POWER_ID = makeID(RedHerringPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public RedHerringPower(AbstractPlayer owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        AbstractDungeon.player.energy.energy++;
    }

    public void updateDescription () {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onVictory() {
        AbstractDungeon.player.energy.energy -= this.amount;
    }
}
