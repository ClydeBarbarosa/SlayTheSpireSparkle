package sparklemod.cards.starter;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Pocket Sand - attack, 2 energy - Deal 5-8(6-10) damage, inflict 0-1(1-2) weak.
public class PocketSand extends BaseCard {
    public static final String ID = makeID(PocketSand.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_DAMAGE = 5;
    private static final int UPGRADED_BASE_DAMAGE_INCREASE = 1;
    private static final int MAX_DAMAGE = 8;
    private static final int UPGRADED_MAX_DAMAGE_INCREASE = 2;
    private static final int BASE_WEAK = 0;
    private static final int UPGRADED_BASE_WEAK_INCREASE = 1;
    private static final int MAX_WEAK = 1;
    private static final int UPGRADED_MAX_WEAK_INCREASE = 1;

    public PocketSand() {
        super(ID, info);

        setCustomVar("PocketSandDamage", VariableType.DAMAGE, BASE_DAMAGE, UPGRADED_BASE_DAMAGE_INCREASE);
        setCustomVar("PocketSandMaxDamage", VariableType.DAMAGE, MAX_DAMAGE, UPGRADED_MAX_DAMAGE_INCREASE);
        setCustomVar("PocketSandWeak", BASE_WEAK, UPGRADED_BASE_WEAK_INCREASE);
        setCustomVar("PocketSandMaxWeak", MAX_WEAK, UPGRADED_MAX_WEAK_INCREASE);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int addedDamage, addedWeak;

        addedDamage = randomIntWithVariance(customVar("PocketSandDamage"),customVar("PocketSandMaxDamage"));
        addedWeak = randomIntWithVariance(customVar("PocketSandWeak"), customVar("PocketSandMaxWeak"));

        addToBot(new DamageAction(m, new DamageInfo(p, addedDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, addedWeak,false)));
    }
}
