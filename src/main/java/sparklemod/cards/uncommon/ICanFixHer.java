package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.ICanFixHerPower;
import sparklemod.util.CardStats;

//I can fix her - power, 3 energy - Each turn, randomly gain 1 Strength (high), Dexterity (high), Variance (med), Loaded Dice (low), Mask (very low), or nothing. Stacks reduce fail chance.
public class ICanFixHer extends BaseCard {
    public static final String ID = makeID(ICanFixHer.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            3 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static boolean maxStacks = false;

    public ICanFixHer() {
        super(ID, info);
    }

    @Override
    public void update() {
        super.update();

        if(!maxStacks) {
            if (AbstractDungeon.player.hasPower(ICanFixHerPower.POWER_ID)) {
                if (AbstractDungeon.player.getPower(ICanFixHerPower.POWER_ID).amount <= 0) {
                    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                    maxStacks = true;
                }
            }
        }
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ICanFixHerPower(p, 1)));
        if(upgraded) {
            addToBot(new ApplyPowerAction(p, p, new ICanFixHerPower(p, 1)));
        }
    }
}
