package sparklemod.cards.starter;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;

import java.util.ArrayList;

public class SixGunRoulette extends BaseCard {
    public static final String ID = makeID(SixGunRoulette.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ALL, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 8;
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 20;

    public SixGunRoulette() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);

        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractMonster> monsterList = AbstractDungeon.getCurrRoom().monsters.monsters;

        monsterList.removeIf(mo -> mo.isDead);

        int numTargets = 1 + monsterList.size(); //player is a valid target, so add 1
        int selectedTarget = AbstractDungeon.miscRng.random(1, numTargets);

        if(selectedTarget==1) { //the player
            addToBot(new DamageAction(p, new DamageInfo(p, damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            addToBot(new GainBlockAction(p, p, block));
        }
        else { //a monster
            addToBot(new DamageAction(monsterList.get(selectedTarget-2), new DamageInfo(p, damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }
}
