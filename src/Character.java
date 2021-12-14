import java.util.ArrayList;
import java.util.Random;
// Michael Savard Gélinas - 261063031
public class Character {
    public static String setSpells(ArrayList<Spell> spells) {
        Character.spells = spells;
        return null;
    }

    private static ArrayList<Spell> spells;
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
        double dmgDealth = 0.0;
        Random rand = new Random(seed);
        double ranSpellDmg = 0;

        for (int i = 0; i < Character.spells.size(); i++) {
            if (Character.spells.get(i).getName().equalsIgnoreCase(splName)) {
                ranSpellDmg = (Character.spells.get(i).getMaxDmg() - Character.spells.get(i).getMinDmg()) + Character.spells.get(i).getMinDmg() * rand.nextDouble();
                dmgDealth = ranSpellDmg;
                break;
            } else {
                dmgDealth = -1;
            }
        }
        return dmgDealth;
    }

    public static void displaySpells(Spell spells) {
            System.out.println(spells);
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
        return Name + "\n" + "His current Health is of " + String.format("%1$.2f", getMaxHealth()) + " HP.";
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
