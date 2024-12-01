/* Day 1 Puzzle 1
 * 1. take input from file, need to clean this up later
 * 2. split into two, put each in its own left and right list
 * 3. sort each list
 * 4. compare element wise, find absolute value
 * 5. add this value to count
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class d1p2 {
    static String filename = "data/d1.txt";

    static int liststyle(int totaldistance) {
        List<Integer> leftlist = new ArrayList<>();
        List<Integer> rightlist = new ArrayList<>();
        // int x = 0;

        // 1 & 2. below
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("   ");
                // for (String part : parts) {
                //     System.out.println("Part number is " + x + " " + part);
                //     x++;
                // }
                leftlist.add(Integer.parseInt(parts[0]));
                rightlist.add(Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. below
        Collections.sort(leftlist);
        Collections.sort(rightlist);

        // 4 & 5. below
        for (int i = 0; i < leftlist.size(); i++) {
            totaldistance += Math.abs(leftlist.get(i) - rightlist.get(i));
        }

        return totaldistance;

    }

    static int arraystyle(int totaldistance) {
        List<Integer> leftlist = new ArrayList<>();
        List<Integer> rightlist = new ArrayList<>();
        // int x = 0;

        // 1 & 2. below
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("   ");
                // for (String part : parts) {
                //     System.out.println("Part number is " + x + " " + part);
                //     x++;
                // }
                leftlist.add(Integer.parseInt(parts[0]));
                rightlist.add(Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer[] leftarray = leftlist.toArray(Integer[]::new);
        Integer[] rightarray = rightlist.toArray(Integer[]::new);

        // 3. below
        Arrays.parallelSort(leftarray);
        Arrays.parallelSort(rightarray);

        // 4 & 5. below
        for (int i = 0; i < leftlist.size(); i++) {
            totaldistance += Math.abs(leftarray[i] - rightarray[i]);
        }
        return totaldistance;
    }
    public static void main(String[] args) {
        int totaldistance = 0;

        long starttime = System.nanoTime() / 1000;

        // totaldistance = liststyle(totaldistance);
        totaldistance = arraystyle(totaldistance);

        long endtime = System.nanoTime() / 1000;
        System.out.println("Total distance is " + totaldistance);
        System.out.println("Total running time: " + (endtime - starttime) + " microseconds");
    }
}