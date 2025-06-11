package sparklemod.cards.starter;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Strike - attack, 1(2) energy - Deal 5(9) damage to a random enemy, then deal 2(4) damage to another random enemy.
public class Strike extends BaseCard {
    public static final String ID = makeID(Strike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int DAMAGE = 5;
    private static final int SECOND_DAMAGE = 2;
    private static final int UPG_DAMAGE = 4;
    private static final int UPG_SECOND_DAMAGE = 2;

    public Strike() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setCustomVar("sparkleStrikeSecondHitDamage", VariableType.DAMAGE, SECOND_DAMAGE, UPG_SECOND_DAMAGE);

        setCostUpgrade(2);

        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageRandomEnemyAction(new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new DamageRandomEnemyAction(new DamageInfo(p, customVar("sparkleStrikeSecondHitDamage"), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }
}
