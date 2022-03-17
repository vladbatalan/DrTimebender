package PaooGame.gameWindow.utils;

public class ScoreUtils {
    public static String scoreToString(int theScore){
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
}
