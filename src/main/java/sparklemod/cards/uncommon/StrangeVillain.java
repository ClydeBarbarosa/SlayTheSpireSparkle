package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Strange Villain - attack, 0 energy - Randomly heal 3 or deal 5 damage to yourself and each enemy. (Heal 3. Deal 7 damage to all enemies. Exhaust.)
public class StrangeVillain extends BaseCard {
    public static final String ID = makeID(StrangeVillain.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 2;
    private static final int HEAL_AMOUNT = 3;

    public StrangeVillain () {
        super(ID, info);

        this.upgradesDescription = true;
        this.setExhaust(false, true);

        setMagic(HEAL_AMOUNT);
        setDamage(BASE_DAMAGE, DAMAGE_UPGRADE);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        if(this.upgraded) {
            addToBot(new HealAction(p, p, HEAL_AMOUNT));
            addToBot(new DamageAllEnemiesAction(p, this.damage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SMASH));
        }
        else {
            //handle player
            int choice = randomIntWithoutVariance(1, 2);
            if(choice == 1) {
                addToBot(new HealAction(p, p, HEAL_AMOUNT));
            }
            else {
                addToBot(new DamageAction(p, new DamageInfo(p, 5, DamageInfo.DamageType.NORMAL)));
            }
            //handle monsters
            for(AbstractMonster mo: AbstractDungeon.getMonsters().monsters) {
                if(!mo.isDeadOrEscaped()) {
                    choice = randomIntWithoutVariance(1, 2);
                    if(choice == 1) {
                        addToBot(new HealAction(mo, p, HEAL_AMOUNT));
                    }
                    else {
                        addToBot(new DamageAction(mo, new DamageInfo(p, (this.damage), DamageInfo.DamageType.NORMAL)));
                    }
                }
            }
        }

    }
}
