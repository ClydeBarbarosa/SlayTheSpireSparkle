package sparklemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sparklemod.SparkleMod;
import sparklemod.cards.BaseCard;

import java.util.ArrayList;

import static sparklemod.SparkleMod.makeID;

public class ICanFixHerPower extends BasePower {
    public static final String POWER_ID = makeID(ICanFixHerPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    //80 buckets
    private static final int STRENGTH_BUCKETS = 24; // 3 in 10, 30%
    private static final int DEXTERITY_BUCKETS = 24; // 3 in 10, 30%
    private static final int VARIANCE_BUCKETS = 12; // 3 in 20, 15%
    private static final int LOADED_DICE_BUCKETS = 12; // 3 in 20, 15%
    private static final int MASK_BUCKETS = 8; //1 in 40, 10%

    private static boolean buckets_created = false;
    //fail chance starts at 20%, each additional stack reduces failure chance by 2. first stack fail_chance variable to 20
    private static int failChance = 22;
    private static final int FAIL_CHANCE_REDUCTION_AMOUNT = 2;

    private enum BUCKET_TYPE {
        STRENGTH,
        DEXTERITY,
        VARIANCE,
        LOADED_DICE,
        MASK
    }

    private static final ArrayList<BUCKET_TYPE> buckets = new ArrayList<>();

    public ICanFixHerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        if(!buckets_created) {
            buckets_created = createBuckets();
        }

        if(failChance > 0) {
            failChance -= FAIL_CHANCE_REDUCTION_AMOUNT;
        }
    }

    private boolean createBuckets() {
        //make buckets
        for (int i = 0; i < STRENGTH_BUCKETS; i++) {
            buckets.add(BUCKET_TYPE.STRENGTH);
        }
        for (int i = 0; i < DEXTERITY_BUCKETS; i++) {
            buckets.add(BUCKET_TYPE.DEXTERITY);
        }
        for (int i = 0; i < VARIANCE_BUCKETS; i++) {
            buckets.add(BUCKET_TYPE.VARIANCE);
        }
        for (int i = 0; i < LOADED_DICE_BUCKETS; i++) {
            buckets.add(BUCKET_TYPE.LOADED_DICE);
        }
        for(int i = 0; i < MASK_BUCKETS; i++) {
            buckets.add(BUCKET_TYPE.MASK);
        }
        SparkleMod.logger.info("Buckets created, size {}", buckets.size());
        return true;
    }

    @Override
    public void atStartOfTurn() {

        int failure_roll = BaseCard.randomIntWithoutVariance(0, 100);
        //boolean failed = failure_roll < fail_chance;

        SparkleMod.logger.info("Failure roll of {}, fail chance of {}/100", failure_roll, failChance);

        if (failure_roll > failChance) {
            int randomBucket = BaseCard.randomIntWithoutVariance(0, buckets.size()-1);
            SparkleMod.logger.info("Bucket roll of {}", randomBucket);
            SparkleMod.logger.info("Bucket result of {}", buckets.get(randomBucket));

            switch (buckets.get(randomBucket)) {
                case STRENGTH:
                    addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, 1)));
                    break;
                case DEXTERITY:
                    addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, 1)));
                    break;
                case VARIANCE:
                    addToBot(new ApplyPowerAction(owner, owner, new VariancePower(owner, 1)));
                    break;
                case LOADED_DICE:
                    addToBot(new ApplyPowerAction(owner, owner, new LoadedDicePower(owner, 1)));
                    break;
                case MASK:
                    addToBot(new ApplyPowerAction(owner, owner, new MaskPower(owner, 1)));
                    break;
                default:
                    break;
            }
        }
    }
}
