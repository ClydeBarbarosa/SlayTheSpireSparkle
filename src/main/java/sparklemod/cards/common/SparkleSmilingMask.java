package sparklemod.cards.common;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

public class SparkleSmilingMask extends BaseCard {
    public static final String ID = makeID(SparkleSmilingMask.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private final static int HEAL_MINIMUM = 1;
    private final static int HEAL_MAXIMUM = 3;

    public SparkleSmilingMask() {
        super(ID, info);

        upgradesDescription=true;
        setCustomVar("SparkleSmilingMaskHealMinimum", HEAL_MINIMUM);
        setCustomVar("SparkleSmilingMaskHealMaximum", HEAL_MAXIMUM);

        setExhaust(true);
    }

    @Override
    public void upgrade() {
        super.upgrade();

        this.setExhaust(false);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, AbstractDungeon.cardRandomRng.random(HEAL_MINIMUM, HEAL_MAXIMUM)));
    }
}
