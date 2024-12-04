/* 1. Put everything in the lines as usual
 * 2. Create a matrix for this
 * 3. For every X, look for M A S horizontally, vertically, downwards, diagonally, etc
 * 4. If found, add to result
 * TODO: Optimise code and cleanup the try/catch abuse
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class d4p2 {
    static String filename = "data/d4.txt";
    static int result = 0;
    static char[][] wordmatrix;

    static void print(Object object) {
        System.out.println(object);
    }

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                // System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long starttime = System.nanoTime() / 1000;
        findxmas(lines);
        long endtime = System.nanoTime() / 1000;

        System.out.println("Result: " + result);
        System.out.println("Time: " + (endtime - starttime) + " microseconds");
    }

    final static void findxmas(List<String> lines) {
        wordmatrix = parsetoarray(lines);
        // for (int i = 0; i < wordmatrix.length; i++) {
        // for (int j = 0; j < wordmatrix[0].length; j++) {
        // System.out.print(wordmatrix[i][j]);
        // }
        // System.out.println();
        // }

        for (int i = 0; i < wordmatrix.length; i++) {
            for (int j = 0; j < wordmatrix[0].length; j++) {
                char letter = wordmatrix[i][j];
                if (letter == 'A') {
                    // System.out.println("X at " + i + "," + j);

                    try {
                        if (wordmatrix[i - 1][j - 1] == 'M' && wordmatrix[i + 1][j + 1] == 'S') {
                            try {
                                if (wordmatrix[i - 1][j + 1] == 'M' && wordmatrix[i + 1][j - 1] == 'S') {
                                    result++;}
                            } catch (Exception e) {}

                            try {
                                if (wordmatrix[i - 1][j + 1] == 'S' && wordmatrix[i + 1][j - 1] == 'M') {
                                    result++;}
                            } catch (Exception e) {}
                            
                        }
                    } catch (Exception e) {}

                    try {
                        if (wordmatrix[i - 1][j - 1] == 'S' && wordmatrix[i + 1][j + 1] == 'M') {
                            try {
                                if (wordmatrix[i - 1][j + 1] == 'M' && wordmatrix[i + 1][j - 1] == 'S') {
                                    result++;}
                            } catch (Exception e) {}

                            try {
                                if (wordmatrix[i - 1][j + 1] == 'S' && wordmatrix[i + 1][j - 1] == 'M') {
                                    result++;}
                            } catch (Exception e) {}
                            
                        }
                    } catch (Exception e) {}
                }
                // else {System.out.println("Letter at " + i + "," + j + " is " +
                // wordmatrix[i][j]);}

            }

        }

    }

    final static char[][] parsetoarray(List<String> lines) {
        /*
         * 1. create a word matrix with size of line length * num of words in that line
         * 2. the getChars method is incredible in helping me copy ArrayList to subarray
         * within the 2D array wordmatrix
         */

        int horizontallen = lines.get(0).length();
        char[][] wordmatrix = new char[lines.size()][horizontallen];

        for (int i = 0; i < lines.size(); i++) {
            lines.get(i).getChars(0, 0 + lines.get(0).length(), wordmatrix[i], 0);
        }

        return wordmatrix;
    }

}
