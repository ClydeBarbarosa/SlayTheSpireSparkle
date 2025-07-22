package sparklemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;

public class DontInCrazyDamageAction extends AbstractGameAction {

    private final int minDamage;
    private final int maxDamage;
    private final int numTargets;
    private final int blockAmount;

    public DontInCrazyDamageAction(AbstractCreature owner, int minDamage, int maxDamage, int blockAmount, int numTargets) {
        this.source = owner;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.blockAmount = blockAmount;
        this.numTargets = numTargets;
    }

    //TODO: Give this thing something flashy for a damage animation.
    @Override
    public void update() {

        int numUnblockedHits = 0;
        for(int i = 0; i < this.numTargets; i++) {
            AbstractMonster m = AbstractDungeon.getRandomMonster();
            if(m != null) {
                int damage = BaseCard.randomIntWithoutVariance(this.minDamage, this.maxDamage);
                m.damage(new DamageInfo(this.source, damage, DamageInfo.DamageType.NORMAL));
                if(m.lastDamageTaken > 0) {
                    numUnblockedHits++;
                }
            }
        }
        if(numUnblockedHits > 0) {
            addToBot(new GainBlockAction(this.source, numUnblockedHits * blockAmount));
        }

        this.isDone = true;
    }
}
