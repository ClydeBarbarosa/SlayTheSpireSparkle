package sparklemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class KendamaBallReduceCostAction extends AbstractGameAction {
    private final AbstractRelic owner;

    public KendamaBallReduceCostAction(AbstractRelic owner) {
        this.owner = owner;
    }

    @Override
    public void update() {
        AbstractCard c = AbstractDungeon.player.hand.getRandomCard(true);
        if (c.cost > 0) {
            owner.flash();
            c.flash();
            c.updateCost(-1);
        }
    }
}
