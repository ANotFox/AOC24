/* 1. Put everything in the lines as usual
 * 2. Create a matrix for this
 * 3. For every X, look for M A S horizontally, vertically, downwards, diagonally, etc
 * 4. If found, add to result
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class d4p1 {
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
                if (letter == 'X') {
                    // System.out.println("X at " + i + "," + j);
                    try {
                        if (wordmatrix[i][j + 1] == 'M' && wordmatrix[i][j + 2] == 'A'
                                && wordmatrix[i][j + 3] == 'S') {
                            result++;
                            // L to R
                            // System.out.println("XMAS at " + i + "," + j);
                        }
                    } catch (Exception e) {
                        // e.printStackTrace();
                    }

                    try {
                        if (wordmatrix[i][j - 1] == 'M' && wordmatrix[i][j - 2] == 'A'
                                && wordmatrix[i][j - 3] == 'S') {
                            result++;
                            // R to L
                            // System.out.println("XMAS R to L at " + i + "," + j);
                        }
                    } catch (Exception e) {
                    }

                    try {
                        if (wordmatrix[i + 1][j] == 'M' && wordmatrix[i + 2][j] == 'A'
                                && wordmatrix[i + 3][j] == 'S') {
                            result++;
                            // U to D
                            // System.out.println("XMAS U to D at " + i + "," + j);
                        }
                    } catch (Exception e) {
                    }
                    
                    try {
                        if (wordmatrix[i - 1][j] == 'M' && wordmatrix[i - 2][j] == 'A'
                        && wordmatrix[i - 3][j] == 'S') {
                        result++;
                        // D to U
                        // System.out.println("XMAS D to U at " + i + "," + j);
                        } 
                    } catch (Exception e) {
                    }

                    try {
                        if (wordmatrix[i + 1][j + 1] == 'M' && wordmatrix[i + 2][j + 2] == 'A'
                        && wordmatrix[i + 3][j + 3] == 'S') {
                        result++;
                        // SE
                        // System.out.println("XMAS SE at " + i + "," + j);
                        } 
                    } catch (Exception e) {
                    }

                    try {
                        if (wordmatrix[i + 1][j - 1] == 'M' && wordmatrix[i + 2][j - 2] == 'A'
                        && wordmatrix[i + 3][j - 3] == 'S') {
                        result++;
                        // SW
                        // System.out.println("XMAS SW at " + i + "," + j);
                        } 
                    } catch (Exception e) {
                    }

                    try {
                        if (wordmatrix[i - 1][j - 1] == 'M' && wordmatrix[i - 2][j - 2] == 'A'
                        && wordmatrix[i - 3][j - 3] == 'S') {
                        result++;
                        // NW
                        // System.out.println("XMAS NW at " + i + "," + j);
                        } 
                    } catch (Exception e) {
                    }

                    try {
                        if (wordmatrix[i - 1][j + 1] == 'M' && wordmatrix[i - 2][j + 2] == 'A'
                        && wordmatrix[i - 3][j + 3] == 'S') {
                        result++;
                        // NE
                        // System.out.println("XMAS NE at " + i + "," + j);
                        } 
                    } catch (Exception e) {
                    }

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
