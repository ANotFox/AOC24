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


public class d2p1 {
    static String filename = "data/d2.txt";

    static int safetycheck(int count) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // 1. normal parsing
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(" ");
                List<Integer> levels = new ArrayList<>();
                int change, prevchange = 0;
                boolean safe = true;

                // 2. feel that this can be improved
                for (String part : parts) {
                    levels.add(Integer.parseInt(part));
                }

                // System.out.println("List is " + levels);

                prevchange = levels.get(1) - levels.get(0);
                /* the mistake was not checking the first two elements since the comparisons 
                 * all happened from the second elements onwards in the change loop
                 * this had been done since I needed to have a prevchange to do a sign check
                 */
                if (Math.abs(prevchange) > 3 || Math.abs(prevchange) == 0) {
                    // System.out.println("Difference is " + change);
                    safe = false;
                    continue;
                }

                // 3. iterate over the list and calculate change
                for (int i = 1; i < levels.size() - 1; i++) {
                    change = levels.get(i + 1) - levels.get(i);

                    // 4. below | if not monotonic, their will be a sign difference, break
                    if (prevchange * change < 0) {
                        // System.out.println("Sign difference is " + prevchange + "*" + change);
                        safe = false;
                        break;
                    }
                    // 5. below | if difference is more than 3 or 0, break
                    if (Math.abs(change) > 3 || Math.abs(change) == 0) {
                        // System.out.println("Difference is " + change);
                        safe = false;
                        break;
                    }
                    prevchange = change;
                }

                if (safe) {
                    count++;
                    // System.out.println("List is safe" + levels);
                } 
                // else {
                //     System.out.println("List is not safe" + levels);
                // }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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