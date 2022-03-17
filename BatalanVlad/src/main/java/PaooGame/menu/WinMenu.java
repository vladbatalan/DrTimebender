package PaooGame.menu;

import PaooGame.Game;
import PaooGame.gameWindow.button.ButtonCollection;
import PaooGame.gameWindow.button.MyButton;
import PaooGame.gameWindow.button.buttonTypes.NextLevelButton;
import PaooGame.gameWindow.button.buttonTypes.ResetLevelOnWinButton;
import PaooGame.gameWindow.button.buttonTypes.ReturnMainMenuButton;
import PaooGame.gameWindow.utils.FontUtils;
import PaooGame.gameWindow.utils.ScoreUtils;
import PaooGame.graphics.ImageLoader;
import javafx.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WinMenu extends Menu {
    private BufferedImage backgroundImage;
    private int score = 0;
    private int highScore = -1;

    public WinMenu() {
        InitMenu();
    }


    public void InitMenu() {
        int distanceToTop = Game.GAME_WINDOW_HEIGHT / 2 + 100;
        int distanceToLeft = 200;
        int buttonWidth = 150;
        int buttonHeight = 35;
        int widthUnit = 250;

        backgroundImage = ImageLoader.LoadImage("/backgrounds/timeTravelBackground.jpg");

        MyButton restartLevelButton = new ResetLevelOnWinButton(
                new Rectangle(distanceToLeft, distanceToTop, buttonWidth, buttonHeight),
                "Restart Level"
        );
        //restartLevelButton.setBackgroundColor(new Color(0x090D58));
        restartLevelButton.setBackgroundColor(new Color(0x0056D6));
        restartLevelButton.setEdgeColor(new Color(0, true));

        restartLevelButton.setTextColor(new Color(0xDCD134));//new Color(0x00D679));

        MyButton nextLevelButton = new NextLevelButton(
                new Rectangle(distanceToLeft + widthUnit, distanceToTop, buttonWidth, buttonHeight),
                "Next Level"
        );
        //nextLevelButton.setBackgroundColor(new Color(0x090D58));
        nextLevelButton.setBackgroundColor(new Color(0x0056D6));
        nextLevelButton.setTextColor(new Color(0xDCD134));//new Color(0x00D679));
        nextLevelButton.setEdgeColor(new Color(0, true));

        buttons.AddElement(restartLevelButton);
        buttons.AddElement(nextLevelButton);


        MyButton returnMenu = new ReturnMainMenuButton(
                new Rectangle(Game.GAME_WINDOW_WIDTH / 2 - 75, Game.GAME_WINDOW_HEIGHT - 100, 145, 40),
                "Back to Menu"
        );
        buttons.AddElement(returnMenu);
    }

    public void Draw(Graphics g) {
        //g.drawImage(backgroundImage, 0,0, Game.GAME_WINDOW_WIDTH, Game.GAME_WINDOW_HEIGHT, null);
        //g.setColor(Color.black);
        //g.fillRect(0,0, Game.GAME_WINDOW_WIDTH, Game.GAME_WINDOW_HEIGHT);

        //g.drawImage(backgroundImage, Game.GAME_WINDOW_WIDTH/2 - 300,100,600, Game.GAME_WINDOW_HEIGHT - 230, null);
        //g.setColor(new Color(0, 100, 100, 100));
        //g.fillRect( Game.GAME_WINDOW_WIDTH/2 - 300, 100, 500, Game.GAME_WINDOW_HEIGHT - 230);

        Font usedFont = new Font("arial", Font.BOLD, 50);
        g.setFont(usedFont);
        Pair<Integer, Integer> fontSizePair =  FontUtils.getFontSize(usedFont, g, "You did it!");
        g.setColor(Color.black);
        g.drawString("You did it!", Game.GAME_WINDOW_WIDTH / 2 - fontSizePair.getKey() / 2 + 2, 170 + 2);
        g.setColor(new Color(0xFFFFFF));
        g.drawString("You did it!", Game.GAME_WINDOW_WIDTH / 2 - fontSizePair.getKey() / 2, 170);


        usedFont = new Font("arial", Font.PLAIN, 30);
        g.setFont(usedFont);
        fontSizePair =  FontUtils.getFontSize(usedFont, g, "Time: " + ScoreUtils.scoreToString(score));
        g.setColor(Color.black);
        g.drawString("Time: " +  ScoreUtils.scoreToString(score), Game.GAME_WINDOW_WIDTH / 2 - fontSizePair.getKey() / 2 + 2, 270 + 2);
        g.setColor(new Color(0xFFFFFF));
        g.drawString("Time: " +  ScoreUtils.scoreToString(score), Game.GAME_WINDOW_WIDTH / 2 - fontSizePair.getKey() / 2, 270);

        // a new highscore has been settled!
        if (highScore == -1 || highScore > score) {
            usedFont = new Font("arial", Font.PLAIN, 35);
            g.setFont(usedFont);
            fontSizePair =  FontUtils.getFontSize(usedFont, g, "New Highscore: " +  ScoreUtils.scoreToString(score));
            g.setColor(Color.black);
            g.drawString("New Highscore: " +  ScoreUtils.scoreToString(score), Game.GAME_WINDOW_WIDTH / 2 - fontSizePair.getKey() / 2 + 1, 350 + 1);
            g.setColor(new Color(0x79D632));
            g.drawString("New Highscore: " +  ScoreUtils.scoreToString(score), Game.GAME_WINDOW_WIDTH / 2 - fontSizePair.getKey() / 2, 350);

        } else {

            usedFont = new Font("arial", Font.PLAIN, 35);
            g.setFont(usedFont);
            fontSizePair =  FontUtils.getFontSize(usedFont, g, "Best Time: " +  ScoreUtils.scoreToString(score));
            g.setColor(Color.black);
            g.drawString("Best Time: " +  ScoreUtils.scoreToString(highScore), Game.GAME_WINDOW_WIDTH / 2 - fontSizePair.getKey() / 2 + 1, 350 + 1);
            g.setColor(new Color(0xFFFFFF));
            g.drawString("Best Time: " +  ScoreUtils.scoreToString(highScore), Game.GAME_WINDOW_WIDTH / 2 - fontSizePair.getKey() / 2, 350);
        }

        buttons.Draw(g);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ButtonCollection getButtons() {
        return buttons;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
