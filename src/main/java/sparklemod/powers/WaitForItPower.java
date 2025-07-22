package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class WaitForItPower extends BasePower {
    public static final String POWER_ID = makeID(WaitForItPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final int damage;
    private static int powerOffset = 0;

    public WaitForItPower(AbstractCreature owner, int amount, int damage) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        powerOffset++;
        this.ID = POWER_ID + powerOffset;
        this.damage = damage;
        updateDescription();
    }


    /*public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }*/
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + damage + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL)));
    }

    /*
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
    */
}
