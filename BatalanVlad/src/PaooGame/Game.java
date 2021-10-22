package PaooGame;

import PaooGame.database.DatabaseSingleton;
import PaooGame.gameObjects.ToBeUpdatedConstantly;
import PaooGame.gameObjects.mobileObjects.Player;
import PaooGame.gameWindow.GameWindow;
import PaooGame.graphics.Assets;
import PaooGame.input.KeyInput;
import PaooGame.input.MouseInput;
import PaooGame.levels.Level;
import PaooGame.menu.*;

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
    private boolean         runState;

    /**
     * The main thread of the game
     */
    private Thread          gameThread;

    /**
     * This is the singleton database object used to store levels.
     */
    public static DatabaseSingleton database = DatabaseSingleton.getInstance();

    /**
     * I got no idee what this is for ... ##########################################################################
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


    public static MainGameMenu mainMenu = new MainGameMenu();
    public static MapCreationMenu mapCreation = new MapCreationMenu();
    public static LevelMenu levelMenu = new LevelMenu();
    public static WinMenu winMenu = new WinMenu();
    public static NewProfileMenu newProfileMenu = new NewProfileMenu();
    public static ProfileSelectionMenu profileSelectionMenu = new ProfileSelectionMenu();
    public static HelpMenu helpMenu = new HelpMenu();
    public static VictoryMenu victoryMenu = new VictoryMenu();

    public static Level currentLevel;

    public static ArrayList<ToBeUpdatedConstantly> updateList = new ArrayList<>();
    public static ArrayList<ToBeUpdatedConstantly> removeFromUpdateList = new ArrayList<>();

    public Game(String title, int width, int height)
    {
        gameWindow = new GameWindow(title, width, height);
        runState = false;
    }

    /**
     * Method responsable for configuring the initialisation parameters of the Game
     */
    private void InitGame()
    {

        gameWindow = new GameWindow("Dr. TimeBender", GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        gameWindow.BuildGameWindow();
        Assets.Init();

        gameWindow.GetCanvas().addMouseListener(new MouseInput(this));
        gameWindow.GetCanvas().addMouseMotionListener(new MouseInput(this));
        gameWindow.GetJFrame().addKeyListener(new KeyInput(this));
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
        gameWindow.GetJFrame().requestFocus();

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

        switch (gameState) {
            case GAME:
                currentLevel.Draw(g);
                break;
            case MENU:
                mainMenu.Draw(g);
                break;
            case MAP_CREATION:
                mapCreation.Draw(g);
                break;
            case LEVEL_MENU:
                levelMenu.Draw(g);
                break;
            case WIN_MENU:
                winMenu.Draw(g);
                break;
            case NEW_PROFILE_MENU:
                newProfileMenu.Draw(g);
                break;
            case PROFILE_SELECTION_MENU:
                profileSelectionMenu.Draw(g);
                break;
            case HELP_MENU:
                helpMenu.Draw(g);
                break;
            case VICTORY_MENU:
                victoryMenu.Draw(g);
                break;
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

}

