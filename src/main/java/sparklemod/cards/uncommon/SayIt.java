package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.AnticiPationPower;
import sparklemod.powers.UnexpectedPower;
import sparklemod.util.CardStats;

import java.util.ArrayList;

//SAY IT! - skill, 1(0) energy - If you have Unexpected, inflict 0-2 weak on a random enemy. Fixed.
//Hidden: Playing "Antici", "SAY IT!", and "PATION!" in the same turn deals 10 damage to all enemies.
public class SayIt extends BaseCard {
    public static final String ID = makeID(SayIt.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int MINIMUM_WEAK = 0;
    private static final int MAXIMUM_WEAK = 2;

    public SayIt() {
        super(ID, info);

        setCustomVar("SayItMinimumWeak", MINIMUM_WEAK);
        setCustomVar("SayItMaximumWeak", MAXIMUM_WEAK);

        setCostUpgrade(0);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(UnexpectedPower.POWER_ID)){
            ArrayList<AbstractMonster> monsterList = AbstractDungeon.getCurrRoom().monsters.monsters;

            monsterList.removeIf(mo -> mo.isDead);
            monsterList.removeIf(mo -> mo.escaped);

            //int numTargets = monsterList.size();
            //int selectedTarget = AbstractDungeon.miscRng.random(0, numTargets);
            AbstractCreature target = AbstractDungeon.getRandomMonster();
            int weakAmount = AbstractDungeon.miscRng.random(MINIMUM_WEAK, MAXIMUM_WEAK);

            addToBot(new ApplyPowerAction(target, p, new WeakPower(target, weakAmount, false), weakAmount));
        }
        if(p.hasPower(AnticiPationPower.POWER_ID)) {
            AnticiPationPower sp = (AnticiPationPower) p.getPower(AnticiPationPower.POWER_ID);
            sp.StackPower(AnticiPationPower.PARTS.SAYIT);
        }
        else {
            addToBot(new ApplyPowerAction(p, p, new AnticiPationPower(p, 1, AnticiPationPower.PARTS.SAYIT)));
        }

        //addToBot(new ApplyPowerAction(p, p, new AnticiPationPower(p, 1, AnticiPationPower.PARTS.SAYIT)));
    }
}
