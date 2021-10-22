package PaooGame.menu;

import PaooGame.Game;
import PaooGame.gameWindow.button.ButtonCollection;
import PaooGame.gameWindow.button.MyButton;
import PaooGame.gameWindow.button.buttonTypes.ReturnMainMenuButton;
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
                "Back to Menu",
                new Point()
        );
        buttons.AddElement(returnMenu);
    }

    public void Draw(Graphics g){
        if(firstTimeAccessed)
        {
            firstTimeAccessed = false;
            int totalScore = Game.database.GetTotalScore(Game.userId);
            totalScoreString = scoreToString(totalScore);
        }

        Font usedFont = new Font("arial", Font.BOLD, 50);
        g.setFont(usedFont);
        Pair<Integer, Integer> fontSizePair = this.getFontSize(g, usedFont, "The End!");
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
            fontSizePair = this.getFontSize(g, usedFont, rows[index]);
            g.drawString(rows[index],Game.GAME_WINDOW_WIDTH/2 - fontSizePair.getKey()/2, rowStartY + index * rowSpaces);
        }
        buttons.Draw(g);
    }

    public ButtonCollection getButtons() {
        return buttons;
    }

    private String scoreToString(int theScore){
        int miliseconds = (int)(((theScore%60)/60.0)*100);
        int seconds = theScore/60;
        int minutes = seconds/60;
        seconds = seconds % 60;
        String milisecondsString = "" + miliseconds;
        String secondsString = "" + seconds;
        String minutesString = "" + minutes;
        if(milisecondsString.length() == 1)
            milisecondsString = "00" + milisecondsString;
        else{
            if(milisecondsString.length() == 2)
                milisecondsString = "0" + milisecondsString;
        }
        if(secondsString.length() < 2)
            secondsString = "0" + secondsString;
        if(minutesString.length() < 2)
            minutesString = "0" + minutesString;

        return minutesString + ":" + secondsString + ":" + milisecondsString;
    }

    public void setFirstTimeAccessed(boolean firstTimeAccessed) {
        this.firstTimeAccessed = firstTimeAccessed;
    }
}
