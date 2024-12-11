/* 
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class d9p2 {
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
            fileblocks.add(inputlines.get(0).substring(i, i + 1));
            i++;
            if (i < inputlines.get(0).length()) {
                emptyblocks.add(inputlines.get(0).substring(i, i + 1));
            }
        }

        // System.out.println(fileblocks);
        // System.out.println(emptyblocks);

        for (int i = 0; i < fileblocks.size(); i++) {
            for (int j = 0; j < Integer.parseInt(fileblocks.get(i)); j++) {
                disc.add(String.valueOf(i));
            }
            if (i < emptyblocks.size()) {
                for (int j = 0; j < Integer.parseInt(emptyblocks.get(i)); j++) {
                    disc.add(".");
                }
            }
        }

        // System.out.println(disc);

        twopointer2(disc);

    }

    static void twopointer2(List<String> disc) {
        int i = 0;
        int i2 = 0;
        int j = disc.size() - 1;
        int j2;
        String tmp;
        Set<Integer> seen = new HashSet<>();

        while (j > 0 && !disc.get(j).equals("0")) {
            if (!disc.get(j).equals(".")) {
                tmp = disc.get(j);
                if (!seen.contains(Integer.parseInt(tmp))) {
                    seen.add(Integer.parseInt(tmp));

                    // find start of file block
                    j2 = j;
                    while (j2 > 0 && disc.get(j2).equals(tmp)) {
                        j2--;
                    }
                    j2++; 

                    // search from the start for first empty block with space
                    boolean found = false;
                    for (i = 0; i < j2 && !found; i++) {
                        if (disc.get(i).equals(".")) {
                            i2 = i;
                            while (i2 < disc.size() && disc.get(i2).equals(".")) {
                                i2++;
                            }
                            if (i2 - i >= j - j2 + 1) {
                                // shift
                                for (int k = 0; k < j - j2 + 1; k++) {
                                    disc.set(i + k, disc.get(j2 + k));
                                    disc.set(j2 + k, ".");
                                }
                                found = true;
                            }
                        }
                    }
                    j = j2 - 1;
                } else {
                    j--;
                }
            } else {
                j--;
            }
        }

        checksum(disc);
    }

    static void checksum(List<String> disc) {
        for (int i = 0; i < disc.size(); i++) {
            if (disc.get(i) != ".") {
                result += i * Integer.parseInt(disc.get(i));
            }

        }
    }

}
