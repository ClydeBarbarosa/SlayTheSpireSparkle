package sparklemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MaskedFoolCardCostAction extends AbstractGameAction {

    private final AbstractPower power;

    public MaskedFoolCardCostAction(AbstractPower power) {
        this.power = power;
    }

    public void update() {
        isDone = true;

        if (AbstractDungeon.player.hand.isEmpty()) {
            return;
        }

        boolean cardCanBeReduced = false;

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.costForTurn > 0) {
                cardCanBeReduced = true;
                break;
            }
        }

        if (!cardCanBeReduced) {
            return;
        }

        AbstractCard c;
        do {
            c = AbstractDungeon.player.hand.getRandomCard(true);
        } while (c.costForTurn == 0);

        power.flash();
        c.setCostForTurn(0);
        c.flash();
    }
}
