import java.util.ArrayList;
import java.util.Random;
// Michael Savard Gélinas - 261063031
public class Character {

    private static ArrayList<Spell> spells;

    public static void setSpells(ArrayList<Spell> spells) {
        Character.spells = spells;
    }

    private final String Name;
    private final double AttackValue;
    private final double MaxHealth;
    private double CurrHealth;
    private int NumWins;



    public Character(String Name, double AttackValue, double MaxHealth, int NumWins) {
        this.Name = Name;
        this.AttackValue = AttackValue;
        this.MaxHealth = MaxHealth;
        this.CurrHealth = MaxHealth;
        this.NumWins = NumWins;

    }

    public double castSpell(String splName, int seed) {
        double dmgDealth = 0;
        Random rand = new Random(seed);
        double ranSpellDmg;

        for (Spell spell : Character.spells) {
            if (spell.getName().equalsIgnoreCase(splName)) {
                ranSpellDmg =  new Random(seed).doubles(spell.getMinDmg(), spell.getMaxDmg()).findFirst().getAsDouble();
                dmgDealth = ranSpellDmg * rand.nextDouble() + 0.00001;
                break;
            } else {
                dmgDealth = -1;
            }
        }
        return dmgDealth;
    }

    public static void displaySpells(ArrayList<Spell> spellsList){
        for (Object splLine : spellsList) {
            System.out.println(splLine);
        }
    }

    public String getName() {
        return Name;
    }

    public double getAttackValue() {
        return AttackValue;
    }

    public double getMaxHealth() {
        return MaxHealth;
    }

    public double getCurrHealth() {
        return CurrHealth;
    }

    public int getNumWins() {
        return NumWins;
    }

    @Override
    public String toString() {
        return Name + "\n" + "His current Health is of " + String.format("%1$.2f", getCurrHealth()) + " HP.";
    }

    public double getAttackDamage(int seed) {
        //noinspection OptionalGetWithoutIsPresent
        double AttMult = new Random(seed).doubles(0.7, 1).findFirst().getAsDouble();
        return getAttackValue() * AttMult;
    }

    public double takeDamage(double dmg) {
        CurrHealth -= dmg;
        return getCurrHealth();
    }

    public void increaseWins() {
        NumWins++;
    }
}
