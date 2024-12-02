/* Day 2, Puzzle 1
 * 1. take input from file, split at space
 * 2. parse as int and add to list
 * 3. subtract each i+1 from i
 * 4. must be all decreasing or increasing
 * 5. difference is atleast 1 or more but less than 3 i.e. [1,3]
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;


public class d2p2 {
    static final String filename = "data/d2.txt";

    static boolean removeelementandsendtocheck(List<Integer> levels, int i) {
        List<Integer> removeleft = new ArrayList<>(levels);
        List<Integer> removeright = new ArrayList<>(levels);
        removeleft.remove(i);
        removeright.remove(i + 1);

        boolean x = subcheck(removeleft);
        boolean y = subcheck(removeright);
        // System.out.println("Checking left " + removeleft + " " + x);
        // System.out.println("Checking right " + removeright + " " + y);

        // System.out.println(levels + " is out of updates");
        if (x || y) { return true; } else { return false; }
    }
    static boolean subcheck(List<Integer>levels) {
        int change, prevchange = 0;
        prevchange = levels.get(1) - levels.get(0);
        
        for (int i = 0; i < levels.size() - 1; i++) {
            change = levels.get(i + 1) - levels.get(i);

            if (prevchange * change < 0 || Math.abs(change) > 3 || Math.abs(change) == 0) { 
                // System.out.println("Sign difference and deltas are " + prevchange + "*" + change);
                return false;
            }

            prevchange = change;
        }

        return true;
    }
    
    static boolean check(List<Integer> levels) {
        int change, prevchange = 0;
        // int update = 0;
        boolean safe = true;

        prevchange = levels.get(1) - levels.get(0);
        if (Math.abs(prevchange) > 3 || Math.abs(prevchange) == 0) {
            // System.out.println("Difference is " + change);
            safe = removeelementandsendtocheck(levels, 0);
            }

        for (int i = 1; i < levels.size() - 1; i++) {
            change = levels.get(i + 1) - levels.get(i);

            if (prevchange * change < 0) {
                    List<Integer> removeleft = new ArrayList<>(levels);
                    List<Integer> removecentre = new ArrayList<>(levels);
                    List<Integer> removeright = new ArrayList<>(levels);
                    removeleft.remove(i - 1);
                    removecentre.remove(i);
                    removeright.remove(i + 1);

                    boolean xx = subcheck(removeleft);
                    // System.out.println("Checking left " + removeleft + " " + xx);
                    boolean zz = subcheck(removecentre);
                    // System.out.println("Checking centre " + removecentre + " " + zz);
                    boolean yy = subcheck(removeright);
                    // System.out.println("Checking right " + removeright + " " + yy);
                    if (xx || yy || zz) { return true; } else { return false; }
                    
                    // System.out.println(levels + " is out of updates at sign checkpoint " + safe);
            }

            if (Math.abs(change) > 3 || Math.abs(change) == 0) {
                   safe = removeelementandsendtocheck(levels, i); 
            }

            prevchange = change;
        }
        
        return safe;
    }

    static int safetycheck(int count) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(" ");
                List<Integer> levels = new ArrayList<>();

                for (String part : parts) {
                    levels.add(Integer.parseInt(part));
                }

                if (check(levels)) { count++; 
                    // System.out.println("List is safe" + levels + "\n");
                }
                // else {System.out.println("List is not safe" + levels + "");}

            }
        } catch (IOException e) { e.printStackTrace(); }

        return count;
    }

    public static void main(String[] args) {
        int count = 0;

        long starttime = System.nanoTime() / 1000;
        count = safetycheck(count);
        long endtime = System.nanoTime() / 1000;

        System.out.println("Number of safe reports is " + count);
        System.out.println("Total running time: " + (endtime - starttime) + " microseconds");
        
    }
}