package PaooGame.menu;

import PaooGame.Game;
import PaooGame.graphics.gameWindow.button.ButtonCollection;
import PaooGame.graphics.gameWindow.button.MyButton;
import PaooGame.graphics.gameWindow.button.buttonTypes.ReturnMainMenuButton;
import PaooGame.graphics.gameWindow.utils.FontUtils;
import PaooGame.graphics.gameWindow.utils.ScoreUtils;
import javafx.util.Pair;

import java.awt.*;

public class VictoryMenu extends Menu{
    private String totalScoreString = "";
    private boolean firstTimeAccessed = true;

    public VictoryMenu(){
        InitMenu();
    }

    public void InitMenu(){

        MyButton returnMenu = new ReturnMainMenuButton(
                new Rectangle(Game.GAME_WINDOW_WIDTH/2 - 75 , Game.GAME_WINDOW_HEIGHT - 100 , 145 , 40),
                "Back to Menu"
        );
        buttons.AddElement(returnMenu);
    }

    public void Draw(Graphics g){
        if(firstTimeAccessed)
        {
            firstTimeAccessed = false;
            int totalScore = Game.database.GetTotalScore(Game.userId);
            totalScoreString = ScoreUtils.scoreToString(totalScore);
        }

        Font usedFont = new Font("arial", Font.BOLD, 50);
        g.setFont(usedFont);
        Pair<Integer, Integer> fontSizePair = FontUtils.getFontSize(usedFont, g, "The End!");
        g.setColor(Color.white);
        g.drawString("The End!", Game.GAME_WINDOW_WIDTH/2 - fontSizePair.getKey()/2, 80 );

        final int rowStartY = 120;
        final int rowSpaces = 25;

        // the description of the game and the controlls
        usedFont = new Font("SansSerif", Font.PLAIN, 20);
        g.setFont(usedFont);
        g.setColor(Color.white);

        String[] rows = {
                "You have managed to escape the Laboratory!",
                "Dr. TimeBender can now catch up his father",
                "and stop him from destroying the universe.",
                "",
                "Congratulations!",
                "In your attempt to escape from Dr. VoidBender's evil laboratory",
                "You managed to score the time of:",
                "",
                totalScoreString,
                "",
                "In order to improve it and give Dr. TimeBender a significant bonus",
                "in his quest to stop his father, replay the levels and get the best completion time!"
        };

        for(int index = 0; index < rows.length; index ++){
            fontSizePair =  FontUtils.getFontSize(usedFont, g, rows[index]);
            g.drawString(rows[index],Game.GAME_WINDOW_WIDTH/2 - fontSizePair.getKey()/2, rowStartY + index * rowSpaces);
        }
        buttons.Draw(g);
    }

    public ButtonCollection getButtons() {
        return buttons;
    }


    public void setFirstTimeAccessed(boolean firstTimeAccessed) {
        this.firstTimeAccessed = firstTimeAccessed;
    }
}
