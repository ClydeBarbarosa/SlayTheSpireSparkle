package sparklemod.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//On one hand, violence - skill, 1 energy - gain 1-4(1-6) temporary strength.
public class OnOneHandViolence extends BaseCard {
    public static final String ID = makeID(OnOneHandViolence.class.getSimpleName());
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

    public OnOneHandViolence() {
        super(ID, info);

        setCustomVar("OnOneHandViolenceMinStrength", MIN_STRENGTH);
        setCustomVar("OnOneHandViolenceMaxStrength", MAX_STRENGTH, UPGRADED_MAX_STRENGTH);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int strengthAmount = randomIntWithVariance(customVar("OnOneHandViolenceMinStrength"), customVar("OnOneHandViolenceMaxStrength"));

        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, strengthAmount)));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, strengthAmount)));
    }
}
