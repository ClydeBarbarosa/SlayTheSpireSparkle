package sparklemod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class WaitForItPower extends BasePower {
    public static final String POWER_ID = makeID("WaitForItPower");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public WaitForItPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    /*public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }*/
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);

        if(!AbstractDungeon.getMonsters().areMonstersBasicallyDead()){
            if(!AbstractDungeon.player.hand.isEmpty()) {
                AbstractCard c = AbstractDungeon.player.hand.getRandomCard(true);
                if (c.cost != 0 && c.cost < AbstractDungeon.player.energy.energy) {
                    c.flash();
                    //AbstractDungeon.player.hand.getRandomCard(true).updateCost(amount);
                    c.updateCost(amount);
                }
            }
        }
    }
}
