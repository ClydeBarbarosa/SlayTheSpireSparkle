package sparklemod.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.WaitForItPower;
import sparklemod.util.CardStats;

//Wait for it - Attack, 2(1) energy. Apply 1 vulnerable. At the start of your next turn, deal 5(8) damage.
public class WaitForIt extends BaseCard {
    public static final String ID = makeID(WaitForIt.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int VULNERABLE_AMOUNT = 1;
    private static final int BASE_DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 3;

    public WaitForIt() {
        super(ID, info);

        setDamage(BASE_DAMAGE, DAMAGE_UPGRADE);
        setCostUpgrade(1);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false)));
        addToBot(new ApplyPowerAction(m, p, new WaitForItPower(p, 1, this.damage)));
    }
}
