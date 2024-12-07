/* Recursion
 * f(result, currenttotal, firstindex)
 * if currenttotal < result {only then continue with below}
 * currenttotal = currenttotal * firstindex
 *      if ==result, answer +=  result
 *          else if > result, stopflag = true
 *  else f(result, currenttotal, firstindex++)
 * first + second num
 *      if ==result, answer +=  result
 *          else if > result, stopflag = true
 *  else f(result, currenttotal, firstindex++)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class d7p1 {
    static String filename = "data/d7.txt";
    static long finalresult = 0;
    static Set<Long> finalset = new HashSet<>();
    static HashMap<Integer, Long> finalmap = new HashMap<>();

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
        parse(inputlines);
        long endtime = System.nanoTime() / 1000;

        // System.out.println("Result: " + finalresult);
        System.out.println("Map Result: " + finalmap.values().stream().reduce(0L, Long::sum));
        System.out.println("Set Result: " + finalset.stream().reduce(0L, Long::sum));
        System.out.println("Time: " + (endtime - starttime) + " microseconds");
    }

    static void parse(List<String> inputlines) {
        // have a set to hold all the final numbers of input in, ie the first of each
        // line
        List<Long> finalnumbers = new ArrayList<>();
        List<List<Long>> opnumbers = new ArrayList<>();
        Pattern pat = Pattern.compile("\\d{1,}");
        int lastarray = 0;

        for (String line : inputlines) {
            Matcher mat = pat.matcher(line);

            mat.find();
            finalnumbers.add(Long.parseLong(mat.group()));
            opnumbers.add(new ArrayList<>());
            lastarray = opnumbers.size() - 1;
            while (mat.find()) {
                opnumbers.get(lastarray).add(Long.parseLong(mat.group()));
            }

        }

        bruteforce(finalnumbers, opnumbers);

    }

    static void bruteforce(List<Long> finalnumbers, List<List<Long>> opnumbers) {
        for (int i = 0; i < finalnumbers.size(); i++) {
            f(opnumbers.get(i), finalnumbers.get(i), 0, 0, i);
            // System.out.println(finalset.stream().reduce(0L, Long::sum) + " " +
            // finalmap.values().stream().reduce(0L, Long::sum));
        }

        // int val = 0;
        // f(opnumbers.get(val), finalnumbers.get(val), 0, 0);

    }

    static void f(List<Long> numbers, long result, long currenttotal, int index, int check) {
        if (currenttotal == result) {
            // System.out.println("Matched! " + currenttotal + " " + result);
            finalresult += result;
            finalset.add(result);
            finalmap.put(check, result);

            result = 0;

        }

        if (currenttotal <= result) {
            // currenttotal += currenttotal + numbers.get(index);
            // System.out.println("currenttotal: " + currenttotal + " element addition: " +
            // numbers.get(index));

            // if (currenttotal == result) {
            // System.out.println("Matched! " + currenttotal + " " + result);
            // finalresult += result;
            // result = 0;
            // }

            if (index < numbers.size()) {
                f(numbers, result, currenttotal + numbers.get(index), index + 1, check);
            }
            // f(numbers, result, currenttotal += currenttotal + numbers.get(index), index +
            // 1);

            if (currenttotal < 1) {
                currenttotal = 1;
            }
            // currenttotal *= currenttotal * numbers.get(index);
            // if (currenttotal == result) {
            // finalresult += result;
            // System.out.println("Matched! " + currenttotal + " " + result);
            // result = 0;
            // }
            if (index < numbers.size()) {
                f(numbers, result, currenttotal * numbers.get(index), index + 1, check);
            }
            // f(numbers, result, currenttotal *= currenttotal * numbers.get(index), index +
            // 1);

        }
    }

}