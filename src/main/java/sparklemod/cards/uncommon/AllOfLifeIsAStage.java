package sparklemod.cards.uncommon;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sparklemod.cards.BaseCard;
import sparklemod.character.SparkleCharacter;
import sparklemod.util.CardStats;
import sparklemod.vfx.combat.SparkleTextEffect;

//All of life is a stage - power, 2 energy - randomly gain from 0-2(1-3) strength and 0-2(1-3) dexterity. Fixed.
public class AllOfLifeIsAStage extends BaseCard {

    public static final String ID = makeID(AllOfLifeIsAStage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SparkleCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings( ID );

    private static final int MIN_STRENGTH = 0;
    private static final int UPGRADED_MIN_STRENGTH = 1;
    private static final int MIN_DEXTERITY = 0;
    private static final int UPGRADED_MIN_DEXTERITY = 1;
    private static final int MAX_STRENGTH = 2;
    private static final int UPGRADED_MAX_STRENGTH = 1;
    private static final int MAX_DEXTERITY = 2;
    private static final int UPGRADED_MAX_DEXTERITY = 1;

    public AllOfLifeIsAStage() {
        super(ID, info);

        setCustomVar("AoLiaSStrengthMinimum", MIN_STRENGTH, UPGRADED_MIN_STRENGTH);
        setCustomVar("AoLiaSStrengthMaximum", MAX_STRENGTH, UPGRADED_MAX_STRENGTH);
        setCustomVar("AoLiaSDexterityMinimum", MIN_DEXTERITY, UPGRADED_MIN_DEXTERITY);
        setCustomVar("AoLiaSDexterityMaximum", MAX_DEXTERITY, UPGRADED_MAX_DEXTERITY);
    }

    public void use (AbstractPlayer p, AbstractMonster m) {
        int strengthAmount = AbstractDungeon.cardRandomRng.random(customVar("AoLiaSStrengthMinimum"), customVar("AoLiaSStrengthMaximum"));
        int dexterityAmount = AbstractDungeon.cardRandomRng.random(customVar("AoLiaSDexterityMaximum"), customVar("AoLiaSDexterityMaximum"));

        //for testing purposes
        //strengthAmount = 0;
        //dexterityAmount = 0;

        if(strengthAmount==0 && dexterityAmount ==0) {
            addToBot(new VFXAction(new SparkleTextEffect(this.cardStrings.EXTENDED_DESCRIPTION[0], p.hb.cX, p.hb.cY)));
        }
        else if(strengthAmount > 0) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, strengthAmount), strengthAmount));
        }
        else if(dexterityAmount > 0) {
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, dexterityAmount), dexterityAmount));
        }
    }
}
