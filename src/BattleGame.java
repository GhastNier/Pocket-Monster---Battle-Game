import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Michael Savard Gélinas - 261063031
public class BattleGame {

    private static Random random = new Random();

    public static void playGame(String playerFileName, String monsterFileName, String spellFileName, int seed) {
        Scanner playInput = new Scanner(System.in);
        random = new Random(seed);
        Character player = FileIO.readCharacter(playerFileName);
        Character monster = FileIO.readCharacter(monsterFileName);
        ArrayList<Spell> spells = FileIO.readSpells(spellFileName);


        if (player == null || monster == null) {
            System.err.println("It is impossible to play the game at the moment, missing some data.");
            return;
        }
        if (spells != null) {
            Character.setSpells(spells);
        } else {
            System.err.println("It's dangerous to go alone, even more when you forgot your spellbook.");
            System.err.println("The exploration will continue without spells.");
            return;
        }
        System.out.println(player + "\nAttack Value: " + player.getAttackValue() + "\nNumber of Wins: " + player.getNumWins() + "\n");
        System.out.println(monster + "\nAttack Value: " + monster.getAttackValue() + "\nNumber of Wins: " + monster.getNumWins());

        while (player.getCurrHealth() > 0 && monster.getCurrHealth() > 0) {
            System.out.println("\nPlease enter an action. You can choose between Attack, Spell or Quit.");
            String playerMove = playInput.nextLine();
            double monsterHP = monster.getCurrHealth();
            double playerHP = player.getCurrHealth();
            int attVar = random.nextInt();

            double PlayerAtt = player.getAttackDamage(attVar);
            double MonsterAtt = monster.getAttackDamage(attVar);
            String patt = String.format("%1$.2f", PlayerAtt);
            String matt = String.format("%1$.2f", MonsterAtt);
            boolean Attack = "Attack".equalsIgnoreCase(playerMove);
            boolean Quit = "Quit".equalsIgnoreCase(playerMove);


            if (Attack) {
                monster.takeDamage(PlayerAtt);
                System.out.println(player.getName() + " does a total of " + patt + " points of damages to " + monster.getName());
                if (monster.getCurrHealth() <= 0) {
                    player.increaseWins();
                    System.out.println(monster.getName() + " has been defeated by " + player.getName() + ". Granting him a win, for a total of " + player.getNumWins());
                    break;
                } else {
                    player.takeDamage(MonsterAtt);
                    System.out.println(monster.getName() + " does a total of " + matt + " points of damages to " + player.getName());
                    System.out.println(player);
                    System.out.println(monster);

                    if (player.getCurrHealth() <= 0) {
                        monster.increaseWins();
                        System.out.println(player.getName() + " has fallen to combat.");
                        break;
                    }
                }
                continue;
            }
            if (!Quit && !Attack) {
                System.out.print("\nYou open your spell book and remember your incantation. Choose wisely, the arcane are a difficult art to master. \n \n");
                Character.displaySpells(spells);
                System.out.println("Which spell will you cast? Please enter your selection:");
                String spellMove = playInput.nextLine();

                boolean Fire = "Fireball".equalsIgnoreCase(spellMove);
                boolean Ice = "Icestorm".equalsIgnoreCase(spellMove);
                boolean Meteor = "Meteorstrike".equalsIgnoreCase(spellMove);
                boolean Surge = "Surge of Frostfire".equalsIgnoreCase(spellMove);

                if (Fire || Ice || Meteor || Surge) {
                    String castStr = String.format("%1$.2f", player.castSpell(spellMove, attVar));
                    Double splDmg = player.castSpell(spellMove, attVar);
                    if (splDmg <= 0) {
                        System.out.println("Klaatu Verata N... Necktie! Nectar! Nickel! Noodle! Oh noes \n" + player.getName() + " used the wrong incantation the spell missed.");
                    } else {
                        System.out.println(player.getName() + " casted " + spellMove + " and they did a total of " + castStr + " points of damages to " + monster.getName());
                        monster.takeDamage(splDmg);
                        if (monster.getCurrHealth() <= 0) {
                            player.increaseWins();
                            System.out.println(monster.getName() + " has been defeated by " + player.getName() + ". Granting him a win, for a total of " + player.getNumWins());
                            break;
                        } else {
                            player.takeDamage(MonsterAtt);
                            System.out.println(monster.getName() + " does a total of " + matt + " points of damages to " + player.getName());
                            System.out.println(player);
                            System.out.println(monster);
                            if (player.getCurrHealth() <= 0) {
                                monster.increaseWins();
                                System.out.println(player.getName() + " has fallen to combat.");
                                break;
                            }
                        }
                    }
                    continue;
                }
                if (Quit) {
                    System.out.println("You've left the dungeon unscathed, but your ego took a hit.");
                    break;
                }
            }
        }
    }
}
