package sparklemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

//mostly copied from DualWieldAction for how this works
public class FeignedMadnessAction extends AbstractGameAction {

    private ArrayList<AbstractCard> cannotReduce = new ArrayList<>();
    private AbstractPlayer p;

    public FeignedMadnessAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : this.p.hand.group) {
                //if the cost 1 or less, cannot reduce the cost to 1
                if (c.cost < 2) {
                    cannotReduce.add(c);
                }
            }
            //check if no cards can be reduced
            if (this.cannotReduce.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }
            //check if only one card is valid
            if (this.p.hand.group.size() - cannotReduce.size() == 1) {
                for (AbstractCard c : this.p.hand.group) {
                    if (c.cost > 1) {
                        c.cost = 1;
                    }
                    this.isDone = true;
                    return;
                }
            }
            //remove the non-reducible cards, then open a hand selection screen with the remainder.
            this.p.hand.group.removeAll(this.cannotReduce);
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open("Choose a Card:", 1, false, false, false, false);
                tickDuration();
                return;
            }
            if (this.p.hand.group.size() == 1) {
                this.p.hand.getTopCard().setCostForTurn(1);
                returnCards();
                this.isDone = true;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                c.setCostForTurn(1);
                this.p.hand.addToTop(c);
            }
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
        tickDuration();
    }

    private void returnCards() {
        for(AbstractCard c : cannotReduce) {
            this.p.hand.addToTop(c);
        }
        this.p.hand.refreshHandLayout();
    }
}