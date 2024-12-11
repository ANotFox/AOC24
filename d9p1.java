/* 
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class d9p1 {
    static String filename = "data/d9.txt";
    static long result = 0;

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
        
        List<String> fileblocks = new ArrayList<>();
        List<String> emptyblocks = new ArrayList<>();
        List<String> disc = new ArrayList<>();

        for (int i = 0; i < inputlines.get(0).length(); i++) {
            fileblocks.add(inputlines.get(0).substring(i, i+1));
            i++;
            if (i < inputlines.get(0).length()) {
                emptyblocks.add(inputlines.get(0).substring(i, i+1));
            }
        }

        // System.out.println(fileblocks);
        // System.out.println(emptyblocks);

        for (int i = 0; i < fileblocks.size(); i++) {
            for(int j = 0; j < Integer.parseInt(fileblocks.get(i)); j++) {
                disc.add(String.valueOf(i));
            }
            if (i < emptyblocks.size()) {
                for(int j = 0; j <Integer.parseInt(emptyblocks.get(i)); j++) {
                    disc.add(".");
                }
            }
        }
        
        // System.out.println(disc);

        twopointer(disc);
        
    }

    static void twopointer(List<String> disc) {
        int i = 0;
        int j = disc.size() - 1;

        while (i < j) {
            if (disc.get(i).equals(".")) {
                while (disc.get(j).equals(".")) {
                    j--;
                }
                disc.set(i, disc.get(j));
                disc.set(j, ".");
                j--;
                i++;
            }
            else {
                i++;
            }
        }

        // System.out.println(disc);
        checksum(disc);
    }

    static void checksum(List<String> disc) {
        disc.removeIf(c -> (c.equals(".")));
        for (int i = 0; i < disc.size(); i++) {
            result += i * Integer.parseInt(disc.get(i));
        }
    }

}
