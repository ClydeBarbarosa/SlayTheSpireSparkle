package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Keep up! - attack, 1 energy - Deal 5 damage and inflict 1 weak on an enemy (all enemies.)
public class KeepUp extends BaseCard {
    public static final String ID = makeID(KeepUp.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_DAMAGE = 5;
    private static final int BASE_WEAK = 1;

    public KeepUp() {
        super(ID, info);

        setCustomVar("KeepUpDamage", BASE_DAMAGE);
        setCustomVar("KeepUpWeak", BASE_WEAK);

        upgradesDescription = true;
    }

    public void upgrade() {
        super.upgrade();
        this.target = CardTarget.ALL_ENEMY;
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        if(!upgraded) {
            addToBot(new DamageAction(m, new DamageInfo(p, BASE_DAMAGE, DamageInfo.DamageType.NORMAL)));
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, BASE_WEAK, false)));
        }
        else {
            for (AbstractCreature c : AbstractDungeon.getMonsters().monsters) {
                if(!c.isDeadOrEscaped()) {
                    addToBot(new DamageAction(c, new DamageInfo(p, BASE_DAMAGE, DamageInfo.DamageType.NORMAL)));
                    addToBot(new ApplyPowerAction(c, p, new WeakPower(c, BASE_WEAK, false)));
                }
            }
        }
    }
}
