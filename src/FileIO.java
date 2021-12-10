import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
// Michael Savard Gélinas - 261063031
public class FileIO {

    public static Character readCharacter(String fileName) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
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
            BufferedReader reader = new BufferedReader(new FileReader(spellFile));
            while ((line = reader.readLine()) != null) {
                String[] spell = line.split("\t");
                String tempName = spell[0];
                double tempMin = Double.parseDouble(spell[1]);
                double tempMax = Double.parseDouble(spell[2]);
                double tempLuck = Double.parseDouble(spell[3]);
                tempArray.add(new Spell(tempName, tempMin, tempMax, tempLuck));
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            System.err.println("File not found.");
        } catch(IOException e){
            System.err.println("File might be corrupted.");
        }
        return tempArray;
    }
}
