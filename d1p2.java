/* Day 1 Puzzle 1
 * 1. take input from file, need to clean this up later
 * 2. split into two, put each in its own left and right hashmap
 * 3. if it exists, add it, else put it in for the first time, update key after each operation
 * 4. for every value in leftmap, if it exists in rightmap, multiply it with the value of the key in rightmap
 * 5. add this value to similarity
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;

public class d1p2 {
    static String filename = "data/d1.txt";

    static int liststyle(int similarity) {
        Map<Integer, Integer> leftmap = new HashMap<>();
        Map<Integer, Integer> rightmap = new HashMap<>();
        int leftint, rightint = 0;

        // 1 & 2. below
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("   ");

                leftint = Integer.parseInt(parts[0]);
                rightint = Integer.parseInt(parts[1]); 

                // 3. below
                if (leftmap.containsKey(leftint)) {
                    leftmap.put(leftint, leftmap.get(leftint) + 1);
                } else {
                    leftmap.put(leftint, 1);
                }

                if (rightmap.containsKey(rightint)) {
                    rightmap.put(rightint, rightmap.get(rightint) + 1);
                } else {
                    rightmap.put(rightint, 1);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(leftmap);
        // System.out.println();
        // System.out.println(rightmap);

        // 4 & 5. below
        for (int key : leftmap.keySet()) {
            if (rightmap.containsKey(key)) { //if there is a 2 in left, check if there is a 2 in right
                similarity += key * leftmap.get(key) * rightmap.get(key);
                /* if 4 occurs 3 times in left and 5 times in right, then it is 4 * 3 * 5 = 60
                 * essentially 4 is multiplied by its similarity from right map
                 * this is needed for every 4 in left, ie 3 times in this example
                */

            }
        }

        return similarity;

    }

    public static void main(String[] args) {
        int similarity = 0;

        long starttime = System.nanoTime() / 1000;

        similarity = liststyle(similarity);

        long endtime = System.nanoTime() / 1000;
        System.out.println("Total distance is " + similarity);
        System.out.println("Total running time: " + (endtime - starttime) + " microseconds");
    }
}