import java.util.Random;
// Michael Savard Gélinas - 261063031
public class Spell {
    private final String name;
    private final double minDmg;
    private final double maxDmg;
    private final double splLuck;


    public Spell(String name, double minDmg, double maxDmg, double splLuck) {
        if (minDmg <= 0 || minDmg > maxDmg || splLuck >= 1 || splLuck < 0) {
            throw new IllegalArgumentException("There seems to be a problem with your spellbook.");
        }
        this.name = name;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.splLuck = splLuck;

    }

    public String getName() {
        return name;
    }

    public double getMinDmg() {
        return minDmg;
    }

    public double getMaxDmg() {
        return maxDmg;
    }

    public double getMagicDamage(int seed) {
        double magDmg = new Random(seed).doubles(getMinDmg(), getMaxDmg()).findFirst().getAsDouble();
        double splFail = new Random().doubles(0, 1).findFirst().getAsDouble();
        if (getSplLuck() < splFail) {
            return 0;
        }
        return magDmg;
    }

    public double getSplLuck() {
        return splLuck;
    }

    @Override
    public String toString() {
        return "Spell Name: " + getName() + "\n\tDamage: " + getMinDmg() + " to " + getMaxDmg() + "\n\tSpell Chance of Success: " + getSplLuck()*100 +"% \n";
    }
}
