package sparklemod.cards.common;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Talk to the hand - skill, 0(1) energy - gain 1-5 block (2-4 times, fixed..)
public class TalkToTheHand extends BaseCard {
    public static final String ID = makeID(TalkToTheHand.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int MINIMUM_BLOCK = 1;
    private static final int MAXIMUM_BLOCK = 5;
    private static final int MINIMUM_BLOCK_TIMES = 2;
    private static final int MAXIMUM_BLOCK_TIMES = 4;

    public TalkToTheHand() {
        super(ID, info);

        setCustomVar("TalkToTheHandMinimumBlock", MINIMUM_BLOCK);
        setCustomVar("TalkToTheHandMaximumBlock", MAXIMUM_BLOCK);
        setCustomVar("TalkToTheHandMinimumBlockTimes", MINIMUM_BLOCK_TIMES);
        setCustomVar("TalkToTheHandMaximumBlockTimes", MAXIMUM_BLOCK_TIMES);

        setCostUpgrade(1);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int numTimes = 1;
        if(this.upgraded) {
            numTimes = AbstractDungeon.cardRandomRng.random(MINIMUM_BLOCK_TIMES, MAXIMUM_BLOCK_TIMES);
        }

        for(int i=0; i < numTimes; i++) {
            int blockAmount = randomIntWithVariance(MINIMUM_BLOCK, MAXIMUM_BLOCK);
            addToBot(new GainBlockAction(p, blockAmount));
        }
    }
}
