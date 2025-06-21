package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

//Break Dance - attack, 3 energy - Deal 3-5 damage and inflict vulnerable randomly 2(4) times.
//Note that this means the second hit will deal extra damage, justifying the cost.
public class BreakDance extends BaseCard {
    public static final String ID = makeID(BreakDance.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            3 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int MIN_DAMAGE = 3;
    private static final int MAX_DAMAGE = 5;
    private static final int VULNERABLE_AMOUNT = 1;
    private static final int BASE_NUM_HITS = 2;
    private static final int UPGRADE_NUM_HITS = 2;

    public BreakDance() {
        super(ID, info);

        setCustomVar("BreakDanceMinDamage", MIN_DAMAGE);
        setCustomVar("BreakDanceMaxDamage", MAX_DAMAGE);
        setCustomVar("BreakDanceVulnerableAmount", VULNERABLE_AMOUNT);
        setCustomVar("BreakDanceNumberHits", BASE_NUM_HITS, UPGRADE_NUM_HITS);

    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < customVar("BreakDanceNumberHits"); i++) {
            int finalDamage = AbstractDungeon.cardRandomRng.random(MIN_DAMAGE, MAX_DAMAGE);
            AbstractMonster mo = AbstractDungeon.getRandomMonster();
            addToBot(new DamageAction(mo, new DamageInfo(p, finalDamage, DamageInfo.DamageType.NORMAL)));
            addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, VULNERABLE_AMOUNT, false)));
        }
    }
}
