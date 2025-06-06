package sparklemod.cards.starter;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

public class SparkleDefend extends BaseCard {
    public static final String ID = makeID(SparkleDefend.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BLOCK = 3;
    private static final int UPGRADED_BLOCK = 5;
    private static final int BLOCK_MAX = 4;
    private static final int UPGRADED_BLOCK_MAX = 6;

    public SparkleDefend() {
        super(ID, info);

        setBlock(BLOCK, UPGRADED_BLOCK);
        setCustomVar("sparkleDefendMax",BLOCK + BLOCK_MAX,UPGRADED_BLOCK+ UPGRADED_BLOCK_MAX);

        setCostUpgrade(2);

        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int addedBlock = (this.upgraded ? AbstractDungeon.miscRng.random(0, BLOCK_MAX) : AbstractDungeon.miscRng.random(0, UPGRADED_BLOCK_MAX));

        addToBot(new GainBlockAction(p, p, block + addedBlock));
    }
}