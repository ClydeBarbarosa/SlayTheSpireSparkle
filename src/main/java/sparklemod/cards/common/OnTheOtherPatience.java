package sparklemod.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.SparkleOnTheOtherPatiencePower;
import sparklemod.util.CardStats;

//On the other, patience - skill, 1 energy - next turn, gain 1-4(1-6) temporary strength.
public class OnTheOtherPatience extends BaseCard {
    public static final String ID = makeID(OnTheOtherPatience.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int MIN_STRENGTH = 1;
    private static final int MAX_STRENGTH = 4;
    private static final int UPGRADED_MAX_STRENGTH = 2;

    public OnTheOtherPatience() {
        super(ID, info);

        setCustomVar("OnTheOtherPatienceMinStrength", MIN_STRENGTH);
        setCustomVar("OnTheOtherPatienceMaxStrength", MAX_STRENGTH, UPGRADED_MAX_STRENGTH);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SparkleOnTheOtherPatiencePower(p, 1, upgraded)));
    }
}
