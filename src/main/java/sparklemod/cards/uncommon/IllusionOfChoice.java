package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.cards.summoned.IOCGainDexterity;
import sparklemod.cards.summoned.IOCLoseStrength;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

import java.util.ArrayList;

public class IllusionOfChoice extends BaseCard {
    public static final String ID = makeID(IllusionOfChoice.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_STRENGTH = 1;
    private static final int UPGRADE_STRENGTH = 1;
    private static final int BASE_DEXTERITY = 1;
    private static final int UPGRADE_DEXTERITY = 1;

    public IllusionOfChoice() {
        super(ID, info);

        setCustomVar("IllusionOfChoiceStrengthAmount", BASE_STRENGTH, UPGRADE_STRENGTH);
        setCustomVar("IllusionOfChoiceDexterityAmount", BASE_DEXTERITY, UPGRADE_DEXTERITY);

        setCostUpgrade(1);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new IOCLoseStrength(this.upgraded));
        stanceChoices.add(new IOCGainDexterity(this.upgraded));

        addToBot(new ChooseOneAction(stanceChoices));
    }
}
