package PaooGame;

import PaooGame.Database.DatabaseSingleton;
import PaooGame.GameObjects.MobileObjects.Player;
import PaooGame.GameObjects.ToBeUpdatedConstantly;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Input.KeyInput;
import PaooGame.Input.MouseInput;
import PaooGame.Levels.Level;
import PaooGame.Menu.*;
import PaooGame.Menu.Menu;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game implements Runnable
{
    private GameWindow      wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean         runState;   /*!< Flag ce starea firului de executie.*/
    private Thread          gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy  bs;         /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/


    private Graphics g;          /*!< Referinta catre un context grafic.*/

    // the database of the game
    public static DatabaseSingleton database = DatabaseSingleton.getInstance();

    // profile of player
    public static int user_id;

    public static GameStates gameState = GameStates.MENU;
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
        wnd = new GameWindow(title, width, height);
        runState = false;
    }


    private void InitGame()
    {
        wnd = new GameWindow("Dr. TimeBender", GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
            /// Este construita fereastra grafica.
        wnd.BuildGameWindow();
            /// Se incarca toate elementele grafice (dale)
        Assets.Init();

        wnd.GetCanvas().addMouseListener(new MouseInput(this));
        wnd.GetCanvas().addMouseMotionListener(new MouseInput(this));
        wnd.GetJFrame().addKeyListener(new KeyInput(this));
    }

    public static float CURRENT_FRAME_TIME = 0;
    public void run()
    {
            /// Initializeaza obiectul game
        InitGame();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/


            /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
            /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

            /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState == true)
        {
                /// Se obtine timpul curent
            curentTime = System.nanoTime();
            CURRENT_FRAME_TIME = (float)((curentTime - oldTime)/timeFrame);
                /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {
                /// Actualizeaza pozitiile elementelor
                Update();
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                oldTime = curentTime;
            }
        }

    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame()
    {
        if(runState == false)
        {
                /// Se actualizeaza flagul de stare a threadului
            runState = true;
                /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
                /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
                /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }
        else
        {
                /// Thread-ul este creat si pornit deja
            return;
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StopGame()
    {
        if(runState == true)
        {
                /// Actualizare stare thread
            runState = false;
                /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try
            {
                    /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                    /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                    /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
        else
        {
                /// Thread-ul este oprit deja.
            return;
        }
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */

    private void Update()
    {
        wnd.GetJFrame().requestFocus();

        for(int index = 0; index < updateList.size(); index ++){
            ToBeUpdatedConstantly update = updateList.get(index);
            update.Update();
        }
        for(ToBeUpdatedConstantly update : removeFromUpdateList) {
            //System.out.println("Removing " + update.toString());
            updateList.remove(update);
        }
        removeFromUpdateList.clear();

        if(gameState == GameStates.GAME) {
            currentLevel.Update();
        }
    }

    private void Draw()
    {
        /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();
            /// Verific daca buffer strategy a fost construit sau nu
        if(bs == null)
        {
                /// Se executa doar la primul apel al metodei Draw()
            try
            {
                    /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                    /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
            /// Se obtine contextul grafic curent in care se poate desena.
        g = bs.getDrawGraphics();
            /// Se sterge ce era

        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());
        g.setColor(new Color(30, 31, 41));
        g.fillRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

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

        for(int index = 0; index < updateList.size(); index ++){
            ToBeUpdatedConstantly update = updateList.get(index);
            update.Draw(g);
        }

        bs.show();

            /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
            /// elementele grafice ce au fost desenate pe canvas).
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

