import java.io.*;
import java.util.ArrayList;

// Michael Savard Gélinas - 261063031
public class FileIO {

    public static Character readCharacter(String fileName) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("src/resources/" + fileName));
            String charName = reader.readLine();
            double attValue = Double.parseDouble(reader.readLine());
            double maxHealth = Double.parseDouble(reader.readLine());
            int numberOfWins = Integer.parseInt(reader.readLine());
            return new Character(charName, attValue, maxHealth, numberOfWins);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File not found.");
        } catch (IOException e) {
            System.err.println("File might be corrupted.");
        }

        return null;
    }

    public static ArrayList<Spell> readSpells(String spellFile) {
        ArrayList<Spell> tempArray = new ArrayList<>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/" + spellFile));
            while ((line = reader.readLine()) != null) {
                String[] spell = line.split("\t");
                String tempName = spell[0];
                double tempMin = Double.parseDouble(spell[1]);
                double tempMax = Double.parseDouble(spell[2]);
                double tempLuck = Double.parseDouble(spell[3]);
                tempArray.add(new Spell(tempName, tempMin, tempMax, tempLuck));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File not found.");
        } catch (IOException e) {
            System.err.println("File might be corrupted.");
        }
        return tempArray;
    }

    public static void writeCharacter(Character chrToWr, String fileName) {

        try {
            FileWriter writer = new FileWriter("src/resources/" + fileName);
            BufferedWriter bw = new BufferedWriter(writer);

            String name = chrToWr.getName();
            double attVal = chrToWr.getAttackValue();
            double maxHp = chrToWr.getMaxHealth();
            int numWins = chrToWr.getNumWins();

            // file name and path specified
            File file = new File("src/resources/" + fileName + ".txt");

            // file created if it is not present at the specified location.
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(name + "\n" + attVal + "\n" + maxHp + "\n" + numWins);
            bw.close();

        } catch (IOException e) {
            System.out.print("Can't write the file.");
            e.printStackTrace();
        }

    }

}