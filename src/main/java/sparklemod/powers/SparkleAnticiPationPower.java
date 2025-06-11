package sparklemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import sparklemod.vfx.combat.SparkleTextEffect;

import static sparklemod.SparkleMod.makeID;

public class SparkleAnticiPationPower extends BasePower {
    public static final String POWER_ID = makeID(SparkleAnticiPationPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private int [] collected = new int[] {0, 0, 0};
    private static final int ANTICIPATION_DAMAGE = 10;

    public enum PARTS {
        ANTICI,
        SAYIT,
        PATION
    }

    public SparkleAnticiPationPower(AbstractCreature owner, int amount, PARTS index) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        //collected = new int[]{0, 0, 0};

        if(index == PARTS.ANTICI) {
            this.collected[0] = 1; //amount
        }
        if(index == PARTS.SAYIT) {
            this.collected[1] = 1; //amount
        }
        if(index == PARTS.PATION) {
            this.collected[2] = 1; //amount
        }

        checkCollected();
        updateDescription();
    }

    @Override
    public void updateDescription() {
        StringBuilder tempDescription = new StringBuilder(DESCRIPTIONS[0]);

        //dumb check to make sure we're initialized
        //It's less dumb than I thought. updateDescription() is called from the constructor
        //before the collected[] array is initialized.
        if(collected == null) {
            tempDescription.append(DESCRIPTIONS[0]);
        }
        else {
            for (int i = 0; i <= 2; i++) {
                if (collected[i] != 0) {
                    tempDescription.append(DESCRIPTIONS[i + 1]);
                } else {
                    tempDescription.append(" ? ");
                }
            }
        }

        this.description = tempDescription.toString();
    }

    public void StackPower(PARTS index) {
        if(index == PARTS.ANTICI) {
            collected[0] = 1; //amount
        }
        if(index == PARTS.SAYIT) {
            collected[1] = 1; //amount
        }
        if(index == PARTS.PATION) {
            collected[2] = 1; //amount
        }

        checkCollected();
        updateDescription();
    }

    private void checkCollected() {
        boolean foundEmpty = false;

        for(int i: collected) {
            if (i == 0) {
                foundEmpty = true;
                break;
            }
        }

        if(!foundEmpty) {
            addToBot(new VFXAction(new SparkleTextEffect("ANTICIPATION!!!", owner.hb.cX, owner.hb.cY)));
            addToBot(new DamageAllEnemiesAction((AbstractPlayer) owner, ANTICIPATION_DAMAGE, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }
}
