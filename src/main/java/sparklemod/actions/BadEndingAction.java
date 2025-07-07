package sparklemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class BadEndingAction extends AbstractGameAction {

    private final AbstractPlayer p;
    private final boolean upgraded;

    private static final int BASE_DAMAGE = 5;
    private static final int UPGRADED_BASE_DAMAGE = 8;

    public BadEndingAction(AbstractPlayer p, boolean upgraded) {
        this.p = p;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        addToBot(new DiscardAction(p, p, 99, false));
        for(AbstractCard c : p.hand.group) {
            if(c != null) {
                addToBot(new DamageRandomEnemyAction(new DamageInfo(p,(this.upgraded ? UPGRADED_BASE_DAMAGE : BASE_DAMAGE) - c.cost, DamageInfo.DamageType.THORNS), AttackEffect.SMASH));
            }
        }
        this.isDone = true;
    }
}

