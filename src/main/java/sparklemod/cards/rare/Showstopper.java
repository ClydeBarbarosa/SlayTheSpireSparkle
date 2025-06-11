package sparklemod.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

public class Showstopper extends BaseCard {
    public static final String ID = makeID(Showstopper.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private final static int BASE_DAMAGE = 10;
    private final static int NUM_HITS = 1;
    private final static int UPGRADED_NUM_HITS = 2;

    public Showstopper() {
        super(ID, info);

        setDamage(BASE_DAMAGE);
        upgradesDescription = true;
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int numHits;
        int numThreeCostCards = 0;

        //if upgraded, hit twice
        if(this.upgraded) {
            numHits = UPGRADED_NUM_HITS;
        }
        else {
            numHits = NUM_HITS;
        }

        //count number of cards with cost 3 or greater
        for(AbstractCard c: p.hand.group) {
            if(c.cost >= 3) {
                numThreeCostCards++;
            }
        }

        for(int i = 0; i < numHits; i++) {
            int finalDamage = damage * numThreeCostCards;
            addToBot(new DamageAction(m, new DamageInfo(p, finalDamage), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }
}
