package sparklemod.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sparklemod.character.SparkleCharacter;

import static sparklemod.SparkleMod.makeID;

public class SparkleKendamaBall extends BaseRelic {
    private static final String NAME = "SparkleKendamaBall";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public SparkleKendamaBall () {
        super (ID, NAME, SparkleCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    //When you have this relic, playing a card that costs your maximum energy decreases the cost of all
    //other cards in your hand to zero for the rest of combat.
    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        AbstractPlayer p = AbstractDungeon.player;
        if(targetCard.cost >= p.energy.energy) {
            for(AbstractCard c : p.hand.group) {
                if(c.cost <= p.energy.energy && c.cost != 0 && c != targetCard) {
                    c.updateCost(-999);
                }
            }
        }
    }
}
