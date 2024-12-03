/* 1. spend time learning regex for this
 * 2. parse for mul(digits, digits) | do | don't
 * 3. check for do, add that number, if dont', do not add it
 * 3. parse into digits, add to list
 * 4. multiply
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class d3p2 {
    final static String filename = "data/d3.txt";
    static int result = 0;

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
        regex(lines);
        long endtime = System.nanoTime() / 1000;

        System.out.println("Result: " + result);
        System.out.println("Time: " + (endtime - starttime) + " microseconds");
    }

    final static void regex(List<String> lines) {
        List<String> mullines = new ArrayList<>();
        List<String> enabledmullines = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        Pattern pat = Pattern.compile("don\\'t+|do|(mul\\(\\d{1,},\\d{1,}\\))");

        for (String line : lines) {
            Matcher mat = pat.matcher(line);
            while (mat.find()) {
                mullines.add(mat.group());
                // System.out.println(mat.group());
            }
        }
        // System.out.println("mullines are " + mullines);

        enabledmullines.add(mullines.get(0));
        int i = 1;
        boolean enabled = true;
        while(i < mullines.size() - 1) {
            switch (mullines.get(i)) {
                case "do":
                    enabled = true;
                    break;
                case "don't":
                    enabled = false;
                    break;
                default:
                    if (enabled) {
                        enabledmullines.add(mullines.get(i));
                    }
                    break;
            }
            i++;
        }

        // System.out.println("Enabled mullines are " + enabledmullines);
        
        Pattern numpat = Pattern.compile("(\\d{1,})");
        for (String mulline : enabledmullines) {
            Matcher nummat = numpat.matcher(mulline);
            while (nummat.find()) {
                numbers.add(Integer.parseInt(nummat.group()));
                // System.out.println(nummat.group());
            }
        }
        
        multiplication(numbers);
    }

    final static void multiplication(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i +=2) {
            result += numbers.get(i) * numbers.get(i + 1);
        }
    }
}