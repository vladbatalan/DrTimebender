package PaooGame.menu;

import PaooGame.Game;
import PaooGame.gameWindow.button.ButtonCollection;
import PaooGame.gameWindow.button.MyButton;
import PaooGame.gameWindow.button.buttonTypes.ReturnMainMenuButton;
import javafx.util.Pair;

import java.awt.*;

public class HelpMenu extends Menu{
    private int maxAvailableLevels = 0;

    public HelpMenu(){
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
        Font usedFont = new Font("arial", Font.BOLD, 50);
        g.setFont(usedFont);
        Pair<Integer, Integer> fontSizePair = this.getFontSize(g, usedFont, "Dr. TimeBender");
        g.setColor(Color.white);
        g.drawString("Dr. TimeBender", Game.GAME_WINDOW_WIDTH/2 - fontSizePair.getKey()/2, 80 );

        usedFont = new Font("arial", Font.PLAIN, 30);
        g.setFont(usedFont);
        fontSizePair = this.getFontSize(g, usedFont, "Help");
        g.setColor(Color.white);
        g.drawString("Help",Game.GAME_WINDOW_WIDTH/2 - fontSizePair.getKey()/2, 110 );


        final int rowStartY = 160;
        final int rowSpaces = 25;

        // the description of the game and the controlls
        usedFont = new Font("SansSerif", Font.PLAIN, 20);
        g.setFont(usedFont);
        g.setColor(Color.white);

        String[] rows = {
            "Dr. TimeBender has been taken prisoner by his worst enemy:",
                "his father — Dr. VoidBender —  an adept of the applications of dark science.",
                "Dr. VoidBender has locked his son in his most hidden and well-secured laboratory.",
                "",
                "Dr. TimeBender must escape from his father's laboratory using the power of time",
                "travel which grants him the ability to be in several places at the same time.",
                "He must overcome the lab's security and stop his father from conducting an",
                "experiment that threatens to destroy the universe.",
                "",
                "Time travel can be a dangerous tool though, and it certainly has its drawbacks. It can",
                "bring forth time paradoxes or create weird circumstances and behavior patterns.",
                "The more doc uses his power, the more time begins to pass slower and slower.",
                "So, Dr. TimeBender, beware! And good luck!"
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
}
