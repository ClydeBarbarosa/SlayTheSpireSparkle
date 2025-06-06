package sparklemod.cards.common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

import java.util.ArrayList;

//This is boring - skill, 0 energy - increase the cost of all cards in your hand by 1 for the rest of combat.
public class SparkleThisIsBoring extends BaseCard {
    public static final String ID = makeID(SparkleThisIsBoring.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public SparkleThisIsBoring () {
        super(ID, info);

        this.setCostUpgrade(0);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cannotIncrease = new ArrayList<>();

        for(AbstractCard c : p.hand.group) {
            if(c.cost < p.energy.energy && c.cost != 0 && c != this) {
                c.updateCost(1);
            }
        }
    }
}
