package sample.output;

import java.io.*;
import java.util.TreeSet;

public class HighscoreManager {

    public static void saveScore(Integer score) {
        TreeSet<Integer> toWrite = HighscoreManager.readScoreFromFile();
        toWrite.add(score);
        writeScoreToFile(toWrite);
    }

    private static TreeSet<Integer> readScoreFromFile() {
        File file = new File("src/sample/resources/highScore.txt");
        TreeSet<Integer> results = new TreeSet<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String line = bf.readLine();
            while (line != null) {
                results.add(Integer.valueOf(line));
                line = bf.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return results;
    }

    private static void writeScoreToFile(TreeSet<Integer> results) {
        File file = new File("src/sample/resources/highScore.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Integer result : results) {
                bw.write(String.valueOf(result) + "\n");

            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static String getScores() {
        TreeSet<Integer> results = readScoreFromFile();
        int index = 1;
        StringBuilder sb = new StringBuilder();
        for (Integer result : results) {
            sb.append(String.format("%d. Pesho -  %d\n", index++, result));
        }
        return sb.toString();
    }
}
