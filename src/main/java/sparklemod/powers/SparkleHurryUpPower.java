package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sparklemod.SparkleMod.makeID;

public class SparkleHurryUpPower extends BasePower {

    public static final String POWER_ID = makeID("SparkleHurryUpPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static int energyGain = 0;

    public SparkleHurryUpPower(AbstractPlayer owner, int energy, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        energyGain = energy;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + energyGain + DESCRIPTIONS[1];
    }

    public void onEnergyRecharge() {
        this.flash();
        AbstractDungeon.player.gainEnergy(energyGain);
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (!c.isEthereal) {
                    c.retain = true;
                }
            }
        }
    }
}
