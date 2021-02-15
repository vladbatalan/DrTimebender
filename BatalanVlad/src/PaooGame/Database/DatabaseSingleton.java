package PaooGame.Database;
import PaooGame.Game;
import javafx.util.Pair;

import java.security.spec.RSAOtherPrimeInfo;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseSingleton {
    private volatile static DatabaseSingleton instance = null;
    private final int NUMBER_OF_LEVELS = 4;

    private Connection connection = null;
    private Statement statement = null;


    private DatabaseSingleton(){
        // Establish the connection to the database
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:GameData.db");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            System.out.println("Connection to the database has been established!");

            // Create the tables if they don't exist
            this.CreateTables();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public static DatabaseSingleton getInstance(){
        if(instance == null)
            instance = new DatabaseSingleton();
        return instance;
    }

    private void CreateTables(){
        if(connection != null){
            try{
                String playerTableSql = "CREATE TABLE players " +
                        "(id_player INT  PRIMARY KEY NOT NULL, " +
                        "name       TEXT             NOT NULL);";

                String completedLevelsSql = "CREATE TABLE completed_levels " +
                        "(id_completed INT  PRIMARY KEY NOT NULL, " +
                        "level_code    INT              NOT NULL, " +
                        "id_player     INT              NOT NULL, " +
                        "score         INT              NOT NULL);";

                statement.executeUpdate(playerTableSql);
                statement.executeUpdate(completedLevelsSql);
                connection.commit();

                System.out.println("Database tables if didn't exist before, have been created successfully!");

            }catch (Exception e){
                System.out.println( e.getMessage() );
            }
        }
    }

    public int InsertNewProfile(String profileName){
        int id_player;

        try{
            // cautam in baza de date stringul
            String sql = "SELECT * FROM players WHERE name = '" + profileName + "';";
            ResultSet firstResult = statement.executeQuery(sql);
            connection.commit();

            if(firstResult.next()){
                throw new Exception("Name already exists in database!");
            }

            id_player = 1;

            sql = "SELECT id_player FROM players ORDER BY id_player DESC";
            ResultSet idResult = statement.executeQuery(sql);
            if(idResult.next()){
                id_player = idResult.getInt("id_player") + 1;
            }

            sql = "INSERT INTO players (id_player ,name) VALUES ("+id_player+",'"+ profileName +"');";
            statement.executeUpdate(sql);
            connection.commit();
        }
        catch(Exception e){
            System.out.println( e.getMessage() );
            return -1;
        }
        return id_player;
    }

    public void InsertCompletedLevel(int level_code, int user_id, int score){
        try{
            int id_completed = 1;
            String sql;
            sql = "SELECT id_completed FROM completed_levels ORDER BY id_completed DESC";
            ResultSet idResult = statement.executeQuery(sql);
            if(idResult.next()){
                id_completed = idResult.getInt("id_completed") + 1;
            }

            sql = "INSERT INTO completed_levels (id_completed , level_code, id_player, score) VALUES ("+
                    id_completed + ", " + level_code +", " + user_id + ", " + score +");";
            statement.executeUpdate(sql);
            connection.commit();

            System.out.println("Level completion details were added into the database!");
        }
        catch(Exception e){
            System.out.println( e.getMessage() );
        }
    }


    public int CheckLevelsCompleted(int user_id){

        try {
            String sql = "SELECT level_code FROM completed_levels WHERE id_player = "+ user_id +" ORDER BY level_code DESC;";
            ResultSet myResult = statement.executeQuery(sql);
            connection.commit();

            if(!myResult.next()){
                return 0;
            }
            else{
                return myResult.getInt("level_code") + 1;
            }
        }
        catch (Exception e){
            System.out.println( e.getMessage() );
            return 0;
        }

    }

    public int GetHighScore(int level_code, int user_id){

        try {
            String sql = "SELECT score FROM completed_levels WHERE id_player = "+ user_id +" and level_code = "+ level_code +" ORDER BY score ASC;";
            ResultSet myResult = statement.executeQuery(sql);
            connection.commit();

            if(!myResult.next()){
                return -1;
            }
            else{
                return myResult.getInt("score");
            }
        }
        catch (Exception e){
            System.out.println( e.getMessage() );
        }

        return -1;
    }

    public int GetTotalScore(int user_id){
        int final_score = 0;
        try{
            for(int level_code = 0; level_code < NUMBER_OF_LEVELS; level_code ++){
                int current_level_score = GetHighScore(level_code, user_id);

                if(current_level_score == -1){
                    throw new Exception("A problem with the computation of the total score occoured!");
                }

                final_score += current_level_score;
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            return -1;
        }

        return final_score;
    }


    public ArrayList<Pair<Integer, String>> GetAllUsers(){
        ArrayList<Pair<Integer, String>> listOfSaves = new ArrayList<>();

        try {
            String sql = "SELECT id_player, name FROM players ORDER BY id_player ASC;";
            ResultSet myResult = statement.executeQuery(sql);
            connection.commit();

            while(myResult.next()){
                listOfSaves.add(new Pair<Integer, String>(myResult.getInt("id_player"), myResult.getString("name")));
            }
            
        }catch (Exception e){
            System.out.println( e.getMessage() );
        }

        return listOfSaves;
    }



    public void Close(){
        if(connection != null){
            try {
                statement.close();
                connection.close();
                System.out.println("The connection has successfully been closed!");
            }
            catch(Exception e){
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
        }
    }
}
