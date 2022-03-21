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
    private boolean runState;

    /**
     * The main thread of the game
     */
    private Thread gameThread;

    /**
     * This is the singleton database object used to store levels.
     */
    public DatabaseSingleton database = DatabaseSingleton.getInstance();

    /**
     * The id of the current player
     */
    public int userId;

    /**
     * The state of the game
     */
    public GameStates gameState = GameStates.MENU;

    /**
     * The width of the screen
     */
    public static Integer GAME_WINDOW_WIDTH = 800;
    public static Integer GAME_WINDOW_HEIGHT = 600;

    public float CURRENT_FRAME_TIME = 0;


    public MainGameMenu mainMenu = new MainGameMenu();
    public MapCreationMenu mapCreation = new MapCreationMenu();
    public LevelMenu levelMenu = new LevelMenu(this);
    public WinMenu winMenu = new WinMenu();
    public NewProfileMenu newProfileMenu = new NewProfileMenu();
    public ProfileSelectionMenu profileSelectionMenu = new ProfileSelectionMenu();
    public HelpMenu helpMenu = new HelpMenu();
    public VictoryMenu victoryMenu = new VictoryMenu();

    public Level currentLevel;

    public ArrayList<ToBeUpdatedConstantly> updateList = new ArrayList<>();
    public ArrayList<ToBeUpdatedConstantly> removeFromUpdateList = new ArrayList<>();

    public Game(String title)
    {
        gameWindow = new GameWindow(title, GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        runState = false;
    }

    /**
     * Method responsable for configuring the initialisation parameters of the Game
     */
    private void InitGame()
    {

        // Create main window
        // TODO: If is the cli run type don't create window
        gameWindow = new GameWindow("Dr. TimeBender", GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        gameWindow.BuildGameWindow();
        Assets.Init();

        // Listen to mouse and keyboard
        // TODO: If is the cli, we don't need listeners like this
        gameWindow.GetCanvas().addMouseListener(new MouseInput(this));
        gameWindow.GetCanvas().addMouseMotionListener(new MouseInput(this));
        gameWindow.GetJFrame().addKeyListener(new KeyInput(this));
    }

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

        // Frames per second can be ajusted
        // TODO: each run can be processed at a step
        final int framesPerSecond   = 60;
        final double timeFrame      = 1000000000.0 / framesPerSecond;

        // As long as the thread is running, do the Update() and Draw() methods
        while (runState)
        {

            // Update the current frame time
            curentTime = System.nanoTime();
            CURRENT_FRAME_TIME = (float)((curentTime - oldTime)/timeFrame);


            // If a frame has passed, step into game
            if((curentTime - oldTime) > timeFrame)
            {
                Update();

                // TODO: If the game runs into cli mode, don't draw
                Draw();

                // Update the time
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

        // We delegate the update to the level
        // Update handler which deals with all interactions
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

    public void setCurrentLevel(Level newLevel){
        currentLevel = newLevel;
        currentLevel.InitLevel(); // this is going to start the level
    }

    public Menu getCurrentMenu(){
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

