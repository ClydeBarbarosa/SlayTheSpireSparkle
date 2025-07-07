package sparklemod.cards.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.actions.BadEndingAction;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Bad Ending - skill, 2 energy - Discard your hand, then deal 5(8) - the card's cost damage to a random enemy for each card discarded.
public class BadEnding extends BaseCard {
    public static final String ID = makeID(BadEnding.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_DAMAGE = 5;
    private static final int BASE_DAMAGE_UPGRADE = 3;

    public BadEnding () {
        super(ID, info);
        setCustomVar("BadEndingDamage", BASE_DAMAGE, BASE_DAMAGE_UPGRADE);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new BadEndingAction(p, this.upgraded));
    }

}
