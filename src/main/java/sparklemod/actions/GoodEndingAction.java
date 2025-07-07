package sparklemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GoodEndingAction extends AbstractGameAction {

    @Override
    public void update() {
        for(AbstractCard c: AbstractDungeon.player.hand.group) {
            if(c != null) {
                c.setCostForTurn(c.cost - 1);
            }
        }
        this.isDone = true;
    }
}
