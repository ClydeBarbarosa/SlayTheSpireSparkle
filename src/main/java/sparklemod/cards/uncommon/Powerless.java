package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

public class Powerless extends BaseCard {
    public static final String ID = makeID(Powerless.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public Powerless() {
        super(ID, info);

        setCostUpgrade(0);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(StrengthPower.POWER_ID)) {
            if (p.getPower(StrengthPower.POWER_ID).amount > 0) {
                addToBot(new RemoveSpecificPowerAction(p, p, StrengthPower.POWER_ID));
            }
            if(p.hasPower(LoseStrengthPower.POWER_ID)) {
                addToBot(new RemoveSpecificPowerAction(p, p, LoseStrengthPower.POWER_ID));
            }
        }
        for(AbstractCreature mo : AbstractDungeon.getMonsters().monsters) {
            if (mo.hasPower(StrengthPower.POWER_ID)) {
                if (mo.getPower(StrengthPower.POWER_ID).amount > 0) {
                    addToBot(new RemoveSpecificPowerAction(mo, mo, StrengthPower.POWER_ID));
                }
            }
        }
    }

}
