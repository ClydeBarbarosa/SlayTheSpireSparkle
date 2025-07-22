package sparklemod.cards.common;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Smiling mask - skill, 1 energy - heal between 1-3(5) hp. Fixed. Exhaust.
public class SmilingMask extends BaseCard {
    public static final String ID = makeID(SmilingMask.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private final static int HEAL_MINIMUM = 1;
    private final static int UPGRADE_HEAL_MINIMUM = 0;
    private final static int HEAL_MAXIMUM = 3;
    private final static int UPGRADE_HEAL_MAXIMUM = 2;

    public SmilingMask() {
        super(ID, info);

        setCustomVar("SmilingMaskHealMinimum", HEAL_MINIMUM, UPGRADE_HEAL_MINIMUM);
        setCustomVar("SmilingMaskHealMaximum", HEAL_MAXIMUM, UPGRADE_HEAL_MAXIMUM);

        setExhaust(true);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, randomIntWithoutVariance(HEAL_MINIMUM, HEAL_MAXIMUM)));
    }
}
