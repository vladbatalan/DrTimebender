package PaooGame;

import PaooGame.database.DatabaseSingleton;
import PaooGame.gameObjects.ToBeUpdatedConstantly;
import PaooGame.gameObjects.mobileObjects.Player;
import PaooGame.graphics.gameWindow.GameWindow;
import PaooGame.graphics.Assets;
import PaooGame.input.KeyInput;
import PaooGame.input.MouseInput;
import PaooGame.levels.Level;
import PaooGame.menu.*;
import PaooGame.menu.Menu;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


/**
 * Class responsible for running the instance of the game.
 */
public class Game implements Runnable
{
    /**
     * The main window of the game.
     */
    private GameWindow gameWindow;

    /**
     * The variable that describes the state of the game.
     */
    private boolean        runState;

    /**
     * The main thread of the game
     */
    private Thread          gameThread;

    /**
     * This is the singleton database object used to store levels.
     */
    public static DatabaseSingleton database = DatabaseSingleton.getInstance();

    /**
     * The id of the current player
     */
    public static int userId;

    /**
     * The state of the game
     */
    public static GameStates gameState = GameStates.MENU;

    /**
     * The width of the screen
     */
    public static Integer GAME_WINDOW_WIDTH = 800;
    public static Integer GAME_WINDOW_HEIGHT = 600;


    /**
     * The flag that states if the game runs on no graphics mode
     */
    public static boolean GraphicsMode;

    // All the menus from the game are initialized as static instances
    public static MainGameMenu mainMenu = new MainGameMenu();
    public static MapCreationMenu mapCreation = new MapCreationMenu();
    public static LevelMenu levelMenu = new LevelMenu();
    public static WinMenu winMenu = new WinMenu();
    public static NewProfileMenu newProfileMenu = new NewProfileMenu();
    public static ProfileSelectionMenu profileSelectionMenu = new ProfileSelectionMenu();
    public static HelpMenu helpMenu = new HelpMenu();
    public static VictoryMenu victoryMenu = new VictoryMenu();

    // The current level of the game
    public static Level currentLevel;

    // These lists are used for updating the timers and delays
    public static ArrayList<ToBeUpdatedConstantly> updateList = new ArrayList<>();
    public static ArrayList<ToBeUpdatedConstantly> removeFromUpdateList = new ArrayList<>();

    public Game(boolean GraphicsMode)
    {
        Game.GraphicsMode = GraphicsMode;
        runState = false;
    }

    /**
     * Method responsable for configuring the initialisation parameters of the Game
     */
    private void InitGame()
    {
        // Only if the game runs on graphics mode
        if(GraphicsMode) {
            gameWindow = new GameWindow("Dr. TimeBender", GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
            gameWindow.BuildGameWindow();

            gameWindow.GetCanvas().addMouseListener(new MouseInput(this));
            gameWindow.GetCanvas().addMouseMotionListener(new MouseInput(this));
            gameWindow.GetJFrame().addKeyListener(new KeyInput(this));
        }

        Assets.Init();
    }

    public static float CURRENT_FRAME_TIME = 0;

    /**
     * Method responsible with the main loop of the game.
     */
    public void run()
    {
        // Initialise the game object
        InitGame();

        // The previous time
        long oldTime = System.nanoTime();

        // The current time
        long curentTime;

        final int framesPerSecond   = 60;
        final double timeFrame      = 1000000000.0 / framesPerSecond;

        // As long as the thread is running, do the Update() and Draw() methods
        while (runState)
        {

            curentTime = System.nanoTime();
            CURRENT_FRAME_TIME = (float)((curentTime - oldTime)/timeFrame);

            if((curentTime - oldTime) > timeFrame)
            {

                Update();
                // Use the draw command only if there are graphics
                if(GraphicsMode)
                    Draw();
                oldTime = curentTime;
            }

        }

    }



    /**
     * Method responsable for creating the main thread of the game and running it.
     */
    public synchronized void StartGame()
    {
        if(!runState)
        {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    /**
     * Method responsable for stopping the main thread.
     */
    public synchronized void StopGame()
    {
        if(runState)
        {
            runState = false;
            try
            {
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Method responsible for actualising the state of the game elements.
     */
    private void Update()
    {
        // Only if Graphics mode is enabled
        if(GraphicsMode) {
            gameWindow.GetJFrame().requestFocus();
        }


        // This instruction must not be changed due to concurrency problems
        // During the execution, the updateList changes it's size
        for(int index = 0; index < updateList.size(); index ++){
            ToBeUpdatedConstantly update = updateList.get(index);
            update.Update();
        }

        for(ToBeUpdatedConstantly update : removeFromUpdateList) {
            updateList.remove(update);
        }

        removeFromUpdateList.clear();

        if(gameState == GameStates.GAME)
            currentLevel.Update();
    }

    /**
     * Method responsible for rendering the graphic elements.
     */
    private void Draw()
    {
        BufferStrategy bufferStrategy = gameWindow.GetCanvas().getBufferStrategy();

        if(bufferStrategy == null)
        {
            try
            {
                gameWindow.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        assert bufferStrategy != null;
        Graphics g = bufferStrategy.getDrawGraphics();

        g.clearRect(0, 0, gameWindow.GetWndWidth(), gameWindow.GetWndHeight());
        g.setColor(new Color(30, 31, 41));
        g.fillRect(0, 0, gameWindow.GetWndWidth(), gameWindow.GetWndHeight());

        if(gameState != GameStates.VICTORY_MENU)
            victoryMenu.setFirstTimeAccessed(true);

        if(gameState == GameStates.GAME){
            currentLevel.Draw(g);
        }
        Menu currentMenu = getCurrentMenu();
        if(currentMenu != null) {
            currentMenu.Draw(g);
        }

        // This instruction must not be changed due to concurrency problems
        // During the execution, the updateList changes it's size
        for(int index = 0; index < updateList.size(); index ++){

            ToBeUpdatedConstantly update = updateList.get(index);
            update.Draw(g);
        }

        bufferStrategy.show();
        g.dispose();
    }

    public Player getPlayer() {
        return currentLevel.getPlayer();
    }

    public static void setCurrentLevel(Level newLevel){
        currentLevel = newLevel;
        currentLevel.InitLevel(); // this is going to start the level
    }

    public static Menu getCurrentMenu(){
        switch (gameState){
            case MENU:
                return mainMenu;
            case MAP_CREATION:
                return mapCreation;
            case LEVEL_MENU:
                return levelMenu;
            case WIN_MENU:
                return winMenu;
            case NEW_PROFILE_MENU:
                return newProfileMenu;
            case PROFILE_SELECTION_MENU:
                return profileSelectionMenu;
            case HELP_MENU:
                return helpMenu;
            case VICTORY_MENU:
                return victoryMenu;
        }
        return null;
    }

}

