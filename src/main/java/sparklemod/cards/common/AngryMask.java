package sparklemod.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Angry mask - attack, 1 energy - deal between 2-7 damage twice. (three times)
public class AngryMask extends BaseCard {
    public static final String ID = makeID(AngryMask.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int MINIMUM_DAMAGE = 2;
    private static final int MAXIMUM_DAMAGE = 7;
    private static final int NUMBER_TIMES = 2;
    private static final int UPGRADED_NUMBER_TIMES = 3;

    public AngryMask() {
        super(ID, info);

        upgradesDescription = true;
        setCustomVar("AngryMaskMinimumDamage", MINIMUM_DAMAGE);
        setCustomVar("AngryMaskMaximumDamage", MAXIMUM_DAMAGE);

    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int numTimes;
        if(this.upgraded) {
            numTimes = UPGRADED_NUMBER_TIMES;
        }
        else {
            numTimes = NUMBER_TIMES;
        }


        for(int i = 0; i < numTimes; i++) {
            int dam = randomIntWithVariance(MINIMUM_DAMAGE, MAXIMUM_DAMAGE);

            addToBot(new DamageAction(m, new DamageInfo(p, dam, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }
}
