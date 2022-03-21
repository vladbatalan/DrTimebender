package PaooGame.menu;

import PaooGame.Game;
import PaooGame.graphics.gameWindow.button.ButtonCollection;
import PaooGame.graphics.gameWindow.button.MyButton;
import PaooGame.graphics.gameWindow.button.buttonTypes.LevelButton;
import PaooGame.graphics.gameWindow.button.buttonTypes.ReturnMainMenuButton;
import PaooGame.graphics.gameWindow.utils.FontUtils;
import PaooGame.levels.*;
import javafx.util.Pair;

import java.awt.*;

public class LevelMenu extends Menu{
    private int maxAvailableLevels = 0;

    public LevelMenu(){
        InitMenu();
    }

    public void InitMenu(){
        int distanceToTop = 150;
        int distanceToLeft = 120;
        int buttonWidth = 150;
        int buttonHeight = 150;
        int widthUnit = 200;
        int heightUnit = 200;
        int elementsOnRow = 3;

        int numberOfLevels = 4;

        Boolean[] lockedState = new Boolean[numberOfLevels];
        Pair<Level, String>[] levelStringArray = new Pair[numberOfLevels];
        levelStringArray[0] = new Pair(new Level0(), "Level 0");
        levelStringArray[1] = new Pair(new Level1(), "Level 1");
        levelStringArray[2] = new Pair(new Level2(), "Level 2");
        levelStringArray[3] = new Pair(new Level3(), "Level 3");

        // System.out.println("The number of max available levels received from database is " + maxAvailableLevels);

        for(int index = 0; index < numberOfLevels; index ++){
            lockedState[index] = index > maxAvailableLevels;
        }

        for(int index = 0; index < levelStringArray.length; index ++){

            int crrWidth = distanceToLeft + widthUnit * (index % elementsOnRow);
            int crrHeight = distanceToTop + heightUnit * (index / elementsOnRow);

            Level level = levelStringArray[index].getKey();
            String levelTag = levelStringArray[index].getValue();

            LevelButton levelButton = new LevelButton(
                    new Rectangle(crrWidth, crrHeight, buttonWidth, buttonHeight),
                    levelTag,
                    level
            );
            levelButton.setLockedState(lockedState[index]);
            levelButton.setPadding(2);

            buttons.AddElement(levelButton);
        }


        MyButton returnMenu = new ReturnMainMenuButton(
                new Rectangle(Game.GAME_WINDOW_WIDTH/2 - 75 , Game.GAME_WINDOW_HEIGHT - 100 , 145 , 40),
                "Back to Menu"
        );
        buttons.AddElement(returnMenu);
    }

    public void Draw(Graphics g){
        Font usedFont = new Font("arial", Font.BOLD, 50);
        g.setFont(usedFont);
        Pair<Integer, Integer> fontSizePair = FontUtils.getFontSize(usedFont, g, "Dr. TimeBender");
        g.setColor(Color.white);
        g.drawString("Dr. TimeBender", Game.GAME_WINDOW_WIDTH/2 - fontSizePair.getKey()/2, 80 );

        usedFont = new Font("arial", Font.PLAIN, 20);
        g.setFont(usedFont);
        fontSizePair =  FontUtils.getFontSize(usedFont, g, "Level Selection Menu");
        g.setColor(Color.white);
        g.drawString("Level Selection Menu",Game.GAME_WINDOW_WIDTH/2 - fontSizePair.getKey()/2, 110 );

        buttons.Draw(g);
    }

    public ButtonCollection getButtons() {
        return buttons;
    }

    public void setMaxAvailableLevels(int maxAvailableLevels) {
        this.maxAvailableLevels = maxAvailableLevels;
        this.buttons.ClearButtons();
        this.InitMenu();
    }
}
