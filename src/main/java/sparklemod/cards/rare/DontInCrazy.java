package sparklemod.cards.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.actions.DontInCrazyDamageAction;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Don't In Crazy - attack, 3 energy - Attack 4 random targets for 3-6(4-8) damage. Each unblocked hit also gives you 3(5) block.
public class DontInCrazy extends BaseCard {
    public static final String ID = makeID(DontInCrazy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            3 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    private final static int BASE_MIN_DAMAGE = 3;
    private final static int BASE_MAX_DAMAGE = 6;
    private final static int UPGRADE_MIN_DAMAGE = 1;
    private final static int UPGRADE_MAX_DAMAGE = 2;
    private final static int NUMBER_OF_TARGETS = 4;
    private final static int BLOCK_MINIMUM = 3;
    private final static int UPGRADE_BLOCK = 2;

    public DontInCrazy () {
        super(ID, info);

        setCustomVar("DontInCrazyMinDamage", BASE_MIN_DAMAGE, UPGRADE_MIN_DAMAGE);
        setCustomVar("DontInCrazyMaxDamage", BASE_MAX_DAMAGE, UPGRADE_MAX_DAMAGE);
        setCustomVar("DontInCrazyBlockAmount", BLOCK_MINIMUM,  UPGRADE_BLOCK);
        setCustomVar("DontInCrazyNumHits", NUMBER_OF_TARGETS);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new DontInCrazyDamageAction(p, customVar("DontInCrazyMinDamage"), customVar("DontInCrazyMaxDamage"), customVar("DontInCrazyBlockAmount"), NUMBER_OF_TARGETS));
    }
}
