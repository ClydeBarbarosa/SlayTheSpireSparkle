package sparklemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sparklemod.powers.ExcitementPower;

public class ExcitementUpdateAction extends AbstractGameAction {
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if(p.hasPower(ExcitementPower.POWER_ID)) {
            p.getPower(ExcitementPower.POWER_ID).updateDescription();
        }
        this.isDone = true;
    }
}
