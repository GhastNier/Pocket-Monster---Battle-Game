import java.util.ArrayList;
import java.util.Random;

public class Character {
    public static void setSpells(ArrayList<Spell> spells) {
        Character.spells = spells;
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

    public Integer castSpell(String spellName, int dmg){

    }

    public static void displaySpells(Spell spell) {
        System.out.println(spell);
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
