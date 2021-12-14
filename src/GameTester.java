public class GameTester {
    public static void main(String[] args) {
        int seed = 48;
        BattleGame.playGame("player.txt", "monster.txt", "spells.txt", seed);
    }
}