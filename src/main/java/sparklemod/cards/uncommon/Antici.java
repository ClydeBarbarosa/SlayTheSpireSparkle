package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.SparkleAnticiPationPower;
import sparklemod.powers.SparkleUnexpectedPower;
import sparklemod.util.CardStats;

//Gain 1 unexpected.
//Hidden: Playing "Antici", "SAY IT!", and "PATION!" in the same turn deals 10 damage to all enemies.
public class Antici extends BaseCard {
    public static final String ID = makeID(Antici.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public Antici() {
        super(ID, info);

        setCostUpgrade(0);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        //Add unexpected
        addToBot(new ApplyPowerAction(p, p, new SparkleUnexpectedPower(p, 1)));
        //add anticipation

        if(p.hasPower(SparkleAnticiPationPower.POWER_ID)) {
            SparkleAnticiPationPower sp = (SparkleAnticiPationPower) p.getPower(SparkleAnticiPationPower.POWER_ID);
            sp.StackPower(SparkleAnticiPationPower.PARTS.ANTICI);
        }
        else {
            addToBot(new ApplyPowerAction(p, p, new SparkleAnticiPationPower(p, 1, SparkleAnticiPationPower.PARTS.ANTICI)));
        }
    }
}
