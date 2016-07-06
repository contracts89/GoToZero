package sample.output;

import java.io.*;
import java.util.ArrayList;

public class HighscoreManager {

    public void saveScore() {

    }

    private static ArrayList<String> readScoreFromFile() {
        File file = new File("src/sample/resources/highScore.txt");
        ArrayList<String> results = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String line = bf.readLine();
            while (line != null) {
                results.add(line);
                line = bf.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return results;
    }

    private static void writeScoreToFile(ArrayList<String> results) {
        File file = new File("src/sample/resources/highScore.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String result : results) {
                bw.write(result);
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  String getScores() {
        ArrayList<String> results = readScoreFromFile();
        int index = 1;
        StringBuilder sb = new StringBuilder();
        for (String result : results) {
            sb.append(String.format("%d - %s\n", index++, result));
        }
        return sb.toString();
    }
}
