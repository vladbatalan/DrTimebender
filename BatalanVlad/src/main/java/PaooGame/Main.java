package PaooGame;

import PaooGame.levels.Level0;

public class Main
{
    private static final int OPTION = 1;

    public static void main(String[] args)
    {
        if(OPTION == 0) {
            // Start a game with no graphics
            NoGraphicsGame paooGame = new NoGraphicsGame();

            // Start level 0
            paooGame.StartLevel(new Level0());
        }
        else{
            Game paooGame = new Game(true);
            paooGame.StartGame();
        }

    }
}
