package PaooGame;

import PaooGame.levels.Level;

public class NoGraphicsGame {

    protected Game game;

    public NoGraphicsGame(){
        this.game = new Game(false);
        this.game.StartGame();
    }

    public NoGraphicsGame(Game game){
        this.game = game;
        this.game.StartGame();
    }

    public void StartLevel(Level level){
        // Set Game to start the level
        Game.setCurrentLevel(level);
        Game.gameState = GameStates.GAME;
    }
}
