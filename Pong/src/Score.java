import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Score {
    private int playerscore = 0;
    private int player1score = 0;
    private int rally = 0;
    private int highscore;
    public Score() {
        highscore = getHighscore();
    }
    public int getPlayerscore() {
        return playerscore;
    }
    public int gethighScore(){
        return highscore;
    }
    public void resetScore(){
        player1score = 0;
        playerscore = 0;
    }
    public int getPlayer1score() {
        return player1score;
    }
    public int getRally() {
        return rally;
    }
    public void setRally(int newRally) {
        if(rally > highscore) {
            highscore = rally;
            writeNewHighscore(highscore);
        }
        rally = newRally;
    }
    public void incrementRally() {
        rally++;
    }
    public void incrementPlayerscore() {
        playerscore++;
    }
    public void incrementPlayer1score() {
        player1score++;
    }
    private int getHighscore() {
        try {
            File highscoreFile = new File("resources/highscore.txt");
            Scanner fileScanner = new Scanner(highscoreFile);
            int highscore = fileScanner.nextInt();
            fileScanner.close();
            return highscore;
        } catch (IOException e) {
            System.err.println("Error getting highscore.");
            return 0;
        }
    }

    public void writeNewHighscore(int newHighscore) {
        try {
            System.out.println("Updating highscore to: "+ newHighscore);
            FileWriter fileWriter = new FileWriter("resources/highscore.txt");
            fileWriter.write(Integer.toString(newHighscore));
            fileWriter.close();
            System.out.println("Finished updating highscore");
        } catch (IOException e) {
            System.err.println("Error writing highscore.");
        }
    }
}