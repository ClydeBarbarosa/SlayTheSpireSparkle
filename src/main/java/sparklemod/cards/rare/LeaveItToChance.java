package sparklemod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.SparkleLeaveItToChancePower;
import sparklemod.util.CardStats;

//Leave it to chance - Power, 1 energy - at the start of your turn, flip a coin. Heads, gain one energy, tails, gain 5(10) block.
public class LeaveItToChance extends BaseCard {
    public static final String ID = makeID(LeaveItToChance.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BLOCK_AMOUNT = 5;
    private static final int UPGRADED_BLOCK_AMOUNT = 5;

    public LeaveItToChance() {
        super(ID, info);

        setCustomVar("LeaveItToChanceBlockAmount", BLOCK_AMOUNT, UPGRADED_BLOCK_AMOUNT);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SparkleLeaveItToChancePower(p, customVar("LeaveItToChanceBlockAmount"), 1), customVar("SparkleLeaveItToChanceBlockAmount")));
    }
}
