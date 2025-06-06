package sparklemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SparkleAnticipationIsKingAction extends AbstractGameAction {
    private final boolean upgrade;

    private static final int BASE_DAMAGE = 3;
    private static final int UPGRADED_DAMAGE = 6;

    public SparkleAnticipationIsKingAction (boolean upgraded) {
        this.upgrade = upgraded;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        int numCards = p.hand.size();

        int damage = (!this.upgrade ? BASE_DAMAGE : UPGRADED_DAMAGE);

        //discard the hand
        addToTop(new DiscardAction(p, p, numCards, false));
        //deal damage
        for(int i = 0; i < numCards; i++) {
            addToBot(new DamageAction(AbstractDungeon.getRandomMonster(), new DamageInfo(p, damage), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
        this.isDone = true;
    }
}
