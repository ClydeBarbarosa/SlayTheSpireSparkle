package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.SparkleATallFrostyGlassPower;
import sparklemod.util.CardStats;

//A tall frosty glass - skill, 1 energy - Gain 1 thorns each time you are attacked this turn.
public class ATallFrostyGlass extends BaseCard {
//of Just Ice
    public static final String ID = makeID(ATallFrostyGlass.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int THORNS_AMOUNT = 1;

    public ATallFrostyGlass() {
        super(ID, info);

        setCustomVar("ATallFrostyGlassThornsAmount", THORNS_AMOUNT);

        setCostUpgrade(1);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SparkleATallFrostyGlassPower(p, THORNS_AMOUNT)));
    }
}
