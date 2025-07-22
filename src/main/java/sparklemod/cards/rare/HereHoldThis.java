package sparklemod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.HereHoldThisPower;
import sparklemod.util.CardStats;

//Here, hold this - skill, 0 energy - Give target enemy a Sparkle doll that explodes in 1-3 turns for 7(15) damage to all enemies. Exhaust.
public class HereHoldThis extends BaseCard {
    public static final String ID = makeID(HereHoldThis.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    private static final int BASE_DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 8;

    public HereHoldThis () {
        super(ID, info);
        setCustomVar("HereHoldThisDamage", BASE_DAMAGE, DAMAGE_UPGRADE);
        this.setExhaust(true);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new HereHoldThisPower(m, randomIntWithoutVariance(1, 3), customVar("HereHoldThisDamage"))));
    }
}
