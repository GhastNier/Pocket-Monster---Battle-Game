import java.util.Random;
import java.util.Scanner;

public class BattleGame {

    private static Random random = new Random();

    public static void playGame(String playerFileName, String monsterFileName, String spellFileName, int seed) {
        Scanner playInput = new Scanner(System.in);
        Character player = FileIO.readCharacter(playerFileName);
        Character monster = FileIO.readCharacter(monsterFileName);
        Character.setSpells(FileIO.readSpells(spellFileName));
        if (player == null || monster == null) {
            System.err.println("It is impossible to play the game at the moment, missing some data.");
            return;
        }
        if (spells == null) {
            System.err.println("It's dangerous to go alone, even more when you forgot your spellbook.");
            System.err.println("The exploration will continue without spells.");
            return;
        }
        System.out.println(player + "\nAttack Value: " + player.getAttackValue() + "\nNumber of Wins: " + player.getNumWins() + "\n");
        System.out.println(monster + "\nAttack Value: " + monster.getAttackValue() + "\nNumber of Wins: " + monster.getNumWins());

        while (player.getCurrHealth() > 0 && monster.getCurrHealth() > 0) {
            System.out.println("\nPlease enter an action. You can choose between Attack, Spell or Quit.");
            String playerMove = playInput.nextLine();

            int attVar = random.nextInt();
            double monsterHP = monster.getCurrHealth();
            double playerHP = player.getCurrHealth();
            double PlayerAtt = player.getAttackDamage(attVar);
            double MonsterAtt = monster.getAttackDamage(attVar);
            String patt = String.format("%1$.2f", PlayerAtt);
            String matt = String.format("%1$.2f", MonsterAtt);
            boolean Attack = "Attack".equalsIgnoreCase(playerMove);
            boolean Quit = "Quit".equalsIgnoreCase(playerMove);
            boolean Spell = "Spell".equalsIgnoreCase(playerMove);

            if (Attack) {
                monster.takeDamage(PlayerAtt);
                System.out.println(player.getName() + " does a total of " + patt + " points of damages to " + monster.getName());
                if (monster.getCurrHealth() > 0) {
                    player.takeDamage(MonsterAtt);
                    System.out.println(monster.getName() + " does a total of " + matt + " points of damages to " + player.getName());
                    System.out.println(player);
                    System.out.println(monster);
                } else {
                    player.increaseWins();
                    System.out.println(monster.getName() + " has been defeated by " + player.getName() + ". Granting him a win, for a total of " + player.getNumWins());
                }
                if (player.getCurrHealth() <= 0) {
                    monster.increaseWins();
                    System.out.println(player.getName() + " has fallen to combat.");
                }
            }
            if (Quit) {
                System.out.println("You've left the dungeon unscathed, but your ego took a hit.");
                return;
            }
            if (!Quit && !Attack && !Spell) {
                System.out.println("Please enter a valid command, which can be Attack, Spell or Quit.");
            }

        }

    }
}