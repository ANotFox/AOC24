/* 
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class d8p1 {
    static String filename = "data/d8.txt";
    static int result = 0;

    public static void main(String[] args) {
        List<String> inputlines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputlines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long starttime = System.nanoTime() / 1000;
        parsetoarray(inputlines);
        long endtime = System.nanoTime() / 1000;

        System.out.println("Result: " + result);
        System.out.println("Time: " + (endtime - starttime) + " microseconds");
    }

    static void parsetoarray(List<String> inputlines) {
        char[][] matrix = new char[inputlines.size()][inputlines.get(0).length()];

        for (int i = 0; i < inputlines.size(); i++) {
            inputlines.get(i).getChars(0, 0 + inputlines.get(0).length(), matrix[i], 0);
        }

        // for (int i = 0; i < matrix.length; i++) {
        //     for (int j = 0; j < matrix[0].length; j++) {
        //         System.out.print(matrix[i][j]);
        //     }
        //     System.out.println();
        // }

        antennafind(matrix);        
        
    }

    static void antennafind(char[][] matrix) {
        /* create a hashmap of char, position arraylist
         *  positions list has array of x,y as positions
         * check if the char already exists, if not add it and its position
         */ 
        
        HashMap<Character, List<Integer[]>> ants = new HashMap<>();
        char[][] antipodematrix = matrix.clone();

        for (int i  = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {


                if (matrix[i][j] != '.' && ants.containsKey(matrix[i][j])) {
                    List<Integer[]> positions = ants.get(matrix[i][j]);
                    positions.add(new Integer[]{i, j});
                    ants.put(matrix[i][j], positions);
                } else {
                    List<Integer[]> positions = new ArrayList<>();
                    positions.add(new Integer[]{i, j});
                    ants.put(matrix[i][j], positions);
                }
            }
        }

        // printmap(ants);

        antipodes(ants, antipodematrix);
        // printarr(matrix);
    }
    
    static void antipodes(HashMap<Character, List<Integer[]>> ants, char[][] antipodematrix) {
        int[] dist = new int[2];
        int x, y;
        List<Integer[]> allpositions = new ArrayList<>();
        
        ants.remove('.');
        

        for (Character key : ants.keySet()) {
            // System.out.print("for " + key + "    ");

            allpositions = ants.get(key);
            for (int i = 0; i < allpositions.size(); i++) {
                x = allpositions.get(i)[0];
                y = allpositions.get(i)[1];
                
                for (int j = i + 1; j < allpositions.size(); j++) {
                    dist[0] = (allpositions.get(j)[0] - x);
                    dist[1] = (allpositions.get(j)[1] - y);

                    try {
                        antipodematrix[x + 2*dist[0]][y + 2*dist[1]] = '#';
                    } catch (Exception e) {}

                    try {
                        antipodematrix[x - dist[0]][y - dist[1]] = '#';
                    } catch (Exception e) {}
                    
                }
            }
            // System.out.println();
        }
        // printarr(antipodematrix);

        for (int i = 0; i < antipodematrix.length; i++) {
            for (int j = 0; j < antipodematrix[0].length; j++) {
                if (antipodematrix[i][j] == '#') {
                    result++;
                }
            }
        }
    }

    static void printarr(char[][] matrix) {
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    static void printmap(HashMap<Character, List<Integer[]>> ants) {
        for (Character key : ants.keySet()) {
            System.out.print(key + ":" + ants.get(key).size() + "\t");
        }
        System.out.println();

        for (Character key : ants.keySet()) {
            System.out.print(key + "    ");
            for (int i = 0; i < ants.get(key).size(); i++) {
                System.out.print(ants.get(key).get(i)[0] + " " + ants.get(key).get(i)[1] + " | ");
            }
            System.out.println();
        }
    }

}
