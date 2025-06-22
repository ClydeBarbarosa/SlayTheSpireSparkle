package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.WithoutACarePower;
import sparklemod.util.CardStats;

//Without a Care - attack, 0 energy - You cannot gain any more block this turn. Deal 3-7(5-10) damage randomly.
public class WithoutACare extends BaseCard {
    public static final String ID = makeID(WithoutACare.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_MIN_DAMAGE = 3;
    private static final int BASE_MAX_DAMAGE = 7;
    private static final int UPGRADED_MIN_DAMAGE = 2;
    private static final int UPGRADED_MAX_DAMAGE = 3;

    public WithoutACare() {
        super(ID, info);
        setCustomVar("WithoutACareMinDamage", BASE_MIN_DAMAGE, UPGRADED_MIN_DAMAGE);
        setCustomVar("WithoutACareMaxDamage", BASE_MAX_DAMAGE, UPGRADED_MAX_DAMAGE);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int actualDamage = AbstractDungeon.cardRandomRng.random(customVar("WithoutACareMinDamage"), customVar("WithoutACareMaxDamage"));
        addToBot(new DamageRandomEnemyAction(new DamageInfo(p, actualDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new ApplyPowerAction(p, p, new WithoutACarePower(p, 1)));
    }
}
