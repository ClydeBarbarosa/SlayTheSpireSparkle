package sparklemod.cards.rare;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.actions.GoodEndingAction;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Good Ending - skill, 1(2) energy - Discard your hand, then draw up to 3(4) cards(, and reduce the cost of all cards in your hand by 1 this turn).
public class GoodEnding extends BaseCard {
    public static final String ID = makeID(GoodEnding.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_CARD_DRAW = 3;
    private static final int UPGRADED_CARD_DRAW = 1;

    public GoodEnding() {
        super(ID, info);

        setCustomVar("GoodEndingCardDraw", BASE_CARD_DRAW, UPGRADED_CARD_DRAW);
        this.upgradesDescription = true;
        setCostUpgrade(2);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardAction(p, p, p.hand.size(),false));
        addToBot(new DrawCardAction(p, customVar("GoodEndingCardDraw")));
        if(this.upgraded) {
            addToBot(new GoodEndingAction());
        }
    }
}
