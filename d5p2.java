/* 
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class d5p2 {
    final static String filename = "data/d5.txt";
    static int result = 0;

    public static void main(String[] args) {
        List<String> rules = new ArrayList<>();
        List<String> updates = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    // System.out.println("Rules are over!");
                    break;
                } else {
                    rules.add(line);
                }
            }

            while ((line = reader.readLine()) != null) {
                updates.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(rules);
        // System.out.println(updates);
        long starttime = System.nanoTime() / 1000;
        parser(rules, updates);
        long endtime = System.nanoTime() / 1000;

        System.out.println("Result: " + result);
        System.out.println("Time: " + (endtime - starttime) + " microseconds");
    }

    static void parser(List<String> rules, List<String> updates) {
        int pagebefore, pageafter;
        HashMap<Integer, List<Integer>> rulemap = new HashMap<>();
        List<List<Integer>> pageupdates = new ArrayList<>();

        for (int i = 0; i < rules.size(); i++) {
            String[] rulesplit = rules.get(i).split("\\|");
            pagebefore = Integer.parseInt(rulesplit[0]);
            pageafter = Integer.parseInt(rulesplit[1]);
            // System.out.println(pagebefore + " " + pageafter);

            rulemap.putIfAbsent(pagebefore, new ArrayList<>());
            rulemap.get(pagebefore).add(pageafter);
        }

        // System.out.println(rulemap);

        for (int i = 0; i < updates.size(); i++) {
            String[] updatesplit = updates.get(i).split(",");
            pageupdates.add(new ArrayList<Integer>());

            for (String part : updatesplit) {
                pageupdates.get(i).add(Integer.parseInt(part));
            }

        }
        // System.out.println(pageupdates);

        checkordering(rulemap, pageupdates);

    }

    static void checkordering(HashMap<Integer, List<Integer>> rulemap, List<List<Integer>> pageupdates) {
        List<Integer> singleprint = new ArrayList<>(); 
        int currentpagenum, otherpagenum;
        boolean rightorder = true;
        for (int i = 0; i < pageupdates.size(); i++) {
            singleprint = pageupdates.get(i);
            rightorder = true;

            for (int j =  0; j < singleprint.size(); j++) {
                currentpagenum = singleprint.get(j);

                for (int k = j + 1; k < singleprint.size(); k++) {
                    otherpagenum = singleprint.get(k);

                    if (!(rulemap.getOrDefault(currentpagenum, new ArrayList<>()).contains(otherpagenum))) {
                        rightorder = false;
                        // System.out.println(singleprint + " is in the wrong order");
                        break;
                    }
                }
            }

            if (rightorder) {
                // System.out.println(singleprint + " is in the right order and mid value is " + singleprint.get((singleprint.size()) / 2));
                result += singleprint.get(singleprint.size() / 2);
            }
        }
    }

}