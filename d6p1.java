/* 1. take input and initialise an array with its dimensions
 * 2. copy list one at a time into array
 * 3. find the location of the guard
 * 4. do a movement check
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class d6p1 {
    static String filename = "data/d6.txt";
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

        // System.out.println(inputlines);

        long starttime = System.nanoTime() / 1000;
        parsetoarray(inputlines);
        long endtime = System.nanoTime() / 1000;

        System.out.println("Result: " + result);
        System.out.println("Time: " + (endtime - starttime) + " microseconds");
    }

    static void parsetoarray(List<String> inputlines) {

        int horizontallen = inputlines.get(0).length();
        char[][] map = new char[inputlines.size()][horizontallen];

        for (int i = 0; i < inputlines.size(); i++) {
            inputlines.get(i).getChars(0, 0 + inputlines.get(0).length(), map[i], 0);
        }

        guardmovementmark(map);

    }

    static void guardmovementmark(char[][] map) {
        int x = 0;
        int y = 0;
        char direction = 'U';

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '^') {
                    x = i;
                    y = j;
                    // System.out.println("Guard is at " + x + y);
                    break;
                }
            }
        }

        try {
            while (true) {

                switch (direction) {
                    case 'L':
                        while (true) {
                            // System.out.println("Pos " + x + ' ' + y);
                            map[x][y] = 'X';
                            // printer(map, x, y, direction);
                            if (map[x][y - 1] == '#') {
                                direction = 'U';
                                break;
                            } else {
                                y--;
                            }

                        }
                        break;

                    case 'R':
                        while (true) {
                            // System.out.println("Pos " + x + ' ' + y);
                            map[x][y] = 'X';
                            // printer(map, x, y, direction);
                            if (map[x][y + 1] == '#') {
                                direction = 'D';
                                break;
                            } else {
                                y++;
                            }

                        }
                        break;

                    case 'U':
                        while (true) {
                            // System.out.println("Pos " + x + ' ' + y);
                            map[x][y] = 'X';
                            // printer(map, x, y, direction);
                            if (map[x - 1][y] == '#') {
                                direction = 'R';
                                break;
                            } else {
                                x--;
                            }

                        }

                        break;

                    case 'D':
                        while (true) {
                            // System.out.println("Pos " + x + ' ' + y);
                            map[x][y] = 'X';
                            // printer(map, x, y, direction);
                            if (map[x + 1][y] == '#') {
                                direction = 'L';
                                break;
                            } else {
                                x++;
                            }

                        }
                        break;

                    default:
                        break;
                }
            }
        } catch (Exception e) {
            for (char[] cs : map) {
                for (char c : cs) {
                    // System.out.printf(c + " ");
                    if (c == 'X') { result++;}
                }
                // System.out.println("\n");

            }
        }
    }

    static void printer(char[][] map, int x, int y, char direction) {
        System.out.println("Current direction is " + direction);
        for (char[] cs : map) {
            for (char c : cs) {
                System.out.printf(c + " ");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }
}