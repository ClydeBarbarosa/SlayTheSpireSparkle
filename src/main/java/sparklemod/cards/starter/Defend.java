package sparklemod.cards.starter;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.WithoutACarePower;
import sparklemod.util.CardStats;

//Defend - skill, 1(2) energy - Gain between 3(5) and 7(11) block randomly.
public class Defend extends BaseCard {
    public static final String ID = makeID(Defend.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BLOCK = 3;
    private static final int UPGRADED_BLOCK = 4;
    private static final int BLOCK_MAX = 7;
    private static final int UPGRADED_BLOCK_MAX = 4;

    private static final int FIXED_BLOCK = 5;
    private static final int UPGRADED_FIXED_BLOCK = 3;

    public Defend() {
        super(ID, info);

        setCustomVar("DefendMin", BLOCK, UPGRADED_BLOCK);
        setCustomVar("DefendMax",BLOCK_MAX, UPGRADED_BLOCK_MAX);
        setBlock(FIXED_BLOCK, UPGRADED_FIXED_BLOCK);

        setCostUpgrade(2);

        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean hasNoBlockPower = p.hasPower(NoBlockPower.POWER_ID) || p.hasPower(WithoutACarePower.POWER_ID);
        //int blockAmount = (hasNoBlockPower ? 0 : randomIntWithVariance(customVar("DefendMin"), customVar("DefendMax")));
        //int blockAmount = (this.upgraded ? FIXED_BLOCK : UPGRADED_FIXED_BLOCK);

        addToBot(new GainBlockAction(p, p, block)); //blockAmount
    }
}