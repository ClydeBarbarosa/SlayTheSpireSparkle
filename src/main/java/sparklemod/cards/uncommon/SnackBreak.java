package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Snack Break - skill, 1 energy - Gain 3(4) Regen. Exhaust
public class SnackBreak extends BaseCard {
    public static final String ID = makeID(SnackBreak.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int REGEN_AMOUNT = 3;
    private static final int UPGRADE_REGEN_AMOUNT = 1;
    public SnackBreak () {
        super(ID, info);

        setCustomVar("SnackBreakRegenAmount", REGEN_AMOUNT, UPGRADE_REGEN_AMOUNT);
        setExhaust(true);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RegenPower(p, customVar("SnackBreakRegenAmount"))));
    }
}
