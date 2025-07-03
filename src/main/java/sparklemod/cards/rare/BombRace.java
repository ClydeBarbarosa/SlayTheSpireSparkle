package sparklemod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.powers.BombRacePower;
import sparklemod.util.CardStats;

//Race to the bombs - power, 3 energy - Gain 5(8) bombs. Each time you take 1(2) or more unblocked damage, lose one bomb.
//In three turns, the bombs explode, dealing 8 each damage to all enemies.
public class BombRace extends BaseCard {
    public static final String ID = makeID(BombRace.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            3 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int BASE_BOMB_AMOUNT = 5;
    private static final int BOMB_UPGRADE_AMOUNT = 3;
    private static final int BOMB_DAMAGE = 8;

    public BombRace () {
        super(ID, info);
        setCustomVar("BombRaceAmount", BASE_BOMB_AMOUNT, BOMB_UPGRADE_AMOUNT);
        setCustomVar("BombRaceDamage", BOMB_DAMAGE);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BombRacePower(p, customVar("BombRaceAmount"), this.upgraded)));
    }

}
