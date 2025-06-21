package sparklemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SparkleIFREUpgradeCardAction extends AbstractGameAction {
    public SparkleIFREUpgradeCardAction () {
    }

    public void update() {
        //is the hand empty
        if(!AbstractDungeon.player.hand.group.isEmpty()) {
            CardGroup cards = AbstractDungeon.player.hand.getUpgradableCards();
            //remove upgraded cards
            //cards.group.removeIf(c -> c.upgraded);
            //is the hand empty now?
            if(!cards.isEmpty()) {
                //upgrade a card
                AbstractCard c = cards.getRandomCard(true);
                    if (c != null) {
                        c.flash();
                        c.upgrade();
                    }
            }
        }
        this.isDone = true;
    }
}
