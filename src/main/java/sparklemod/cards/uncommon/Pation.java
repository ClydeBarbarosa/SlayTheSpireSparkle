package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.AnticiPationPower;
import sparklemod.powers.UnexpectedPower;
import sparklemod.util.CardStats;

//Pation - skill, 0(1) energy - Lose 1 Unexpected. (Gain 1 energy on your next turn.)
//Hidden: Playing "Antici", "SAY IT!", and "PATION!" in the same turn deals 10 damage to all enemies.
public class Pation extends BaseCard {
    public static final String ID = makeID(Pation.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public Pation() {
        super(ID, info);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(AnticiPationPower.POWER_ID)) {
            AnticiPationPower sp = (AnticiPationPower) p.getPower(AnticiPationPower.POWER_ID);
            sp.StackPower(AnticiPationPower.PARTS.PATION);
        }
        else {
            addToBot(new ApplyPowerAction(p, p, new AnticiPationPower(p, 1, AnticiPationPower.PARTS.PATION)));
        }
        //addToBot(new ApplyPowerAction(p, p, new AnticiPationPower(p, 1, AnticiPationPower.PARTS.PATION)));

        //Remove unexpected
        addToBot(new RemoveSpecificPowerAction(p, p, UnexpectedPower.POWER_ID));
    }
}
