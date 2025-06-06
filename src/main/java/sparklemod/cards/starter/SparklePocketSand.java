package sparklemod.cards.starter;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

public class SparklePocketSand extends BaseCard {
    public static final String ID = makeID(SparklePocketSand.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //Pocket Sand - attack, 2 energy - Deal 5-8(6-10) damage, inflict 0-1(1-2) weak.

    private static final int POCKET_SAND_BASE_DAMAGE = 5;
    private static final int POCKET_SAND_UPGRADED_BASE_DAMAGE = 6;
    private static final int POCKET_SAND_MAX_DAMAGE = 3;
    private static final int POCKET_SAND_UPGRADED_MAX_DAMAGE = 4;
    private static final int POCKET_SAND_BASE_WEAK = 0;
    private static final int POCKET_SAND_UPGRADED_BASE_WEAK = 1;
    private static final int POCKET_SAND_MAX_WEAK = 1;
    private static final int POCKET_SAND_UPGRADED_MAX_WEAK = 1;

    public SparklePocketSand() {
        super(ID, info);

        setCustomVar("sparklePocketSandDamage", VariableType.DAMAGE, POCKET_SAND_BASE_DAMAGE, POCKET_SAND_UPGRADED_BASE_DAMAGE);
        setCustomVar("sparklePocketSandMaxDamage", VariableType.DAMAGE, POCKET_SAND_BASE_DAMAGE + POCKET_SAND_MAX_DAMAGE, POCKET_SAND_UPGRADED_BASE_DAMAGE + POCKET_SAND_UPGRADED_MAX_DAMAGE);
        setCustomVar("sparklePocketSandWeak", VariableType.DAMAGE, POCKET_SAND_BASE_WEAK, POCKET_SAND_UPGRADED_BASE_WEAK);
        setCustomVar("sparklePocketSandMaxWeak", VariableType.DAMAGE, POCKET_SAND_BASE_WEAK + POCKET_SAND_MAX_WEAK, POCKET_SAND_UPGRADED_BASE_WEAK + POCKET_SAND_UPGRADED_MAX_WEAK);

        setDamage(POCKET_SAND_BASE_DAMAGE, POCKET_SAND_UPGRADED_BASE_DAMAGE);
        setMagic(POCKET_SAND_BASE_WEAK, POCKET_SAND_UPGRADED_BASE_WEAK);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int addedDamage, addedWeak;

        if(!this.upgraded) {
            addedDamage = AbstractDungeon.miscRng.random(0, POCKET_SAND_MAX_DAMAGE);
            addedWeak = AbstractDungeon.miscRng.random(0, POCKET_SAND_MAX_WEAK);
        }
        else {
            addedDamage = AbstractDungeon.miscRng.random(0, POCKET_SAND_UPGRADED_MAX_DAMAGE);
            addedWeak = AbstractDungeon.miscRng.random(0, POCKET_SAND_UPGRADED_MAX_WEAK);
        }

        addToBot(new DamageAction(m, new DamageInfo(p, damage + addedDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 1,false),magicNumber + addedWeak));
    }
}
