package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Strange Villain - attack, 0 energy - Randomly heal 3 or deal 5 damage to yourself and each enemy. (Heal 3. Randomly heal 3 or deal 7 damage to each enemy.)
public class StrangeVillain extends BaseCard {
    public static final String ID = makeID(StrangeVillain.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public StrangeVillain () {
        super(ID, info);

        this.upgradesDescription = true;
    }

    public void use (AbstractPlayer p, AbstractMonster m) {

        //handle the player first
        if(this.upgraded) {
            addToBot(new HealAction(p, p, 3));
        }
        else {
            int choice = randomIntWithoutVariance(1, 2);
            if(choice == 1) {
                addToBot(new HealAction(p, p, 3));
            }
            else {
                addToBot(new DamageAction(p, new DamageInfo(p, 5, DamageInfo.DamageType.NORMAL)));
            }
        }
        //handle monsters
        for(AbstractMonster mo: AbstractDungeon.getMonsters().monsters) {
            if(!mo.isDeadOrEscaped()) {
                int choice = randomIntWithoutVariance(1, 2);
                if(choice == 1) {
                    addToBot(new HealAction(mo, p, 3));
                }
                else {
                    addToBot(new DamageAction(mo, new DamageInfo(p, (this.upgraded ? 7 : 5), DamageInfo.DamageType.NORMAL)));
                }
            }
        }
    }
}
