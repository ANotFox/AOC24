/* 1. take input and initialise an array with its dimensions
 * 2. copy list one at a time into array
 * 3. find the location of the guard
 * 4. do a movement check
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class d6p2 {
    static String filename = "data/d6.txt";
    static int result = 0;
    static char[][] map;

    static int guardx;
    static int guardy;

    static int doneonce = 0;

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
        bruteforce();
        long endtime = System.nanoTime() / 1000;

        System.out.println("Result: " + result);
        System.out.println("Time: " + (endtime - starttime) + " microseconds");
    }

    static void parsetoarray(List<String> inputlines) {

        int horizontallen = inputlines.get(0).length();
        map = new char[inputlines.size()][horizontallen];

        for (int i = 0; i < inputlines.size(); i++) {
            inputlines.get(i).getChars(0, 0 + inputlines.get(0).length(), map[i], 0);
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '^') {
                    guardx = i;
                    guardy = j;
                    System.out.println("Guard is at " + guardx + guardy);
                    break;
                }
            }
        }

        guardmovementmark(map);
        doneonce = 1;

    }

    static void guardmovementmark(char[][] map) {
        int x = guardx;
        int y = guardy;
        char direction = 'U';
        Set<String> seen = new HashSet<>();

        try {
            int loop = -1;
            while (loop < 1) {
                // if (doneonce == 1) {
                // TimeUnit.MILLISECONDS.sleep(250);
                // // mapprinter(map);
                // }
                if (doneonce == 0) {
                    map[x][y] = 'X';
                }
                
                switch (direction) {
                    case 'L':
                            if (map[x][y - 1] == '#' || map[x][y - 1] == 'O') {
                                direction = 'U';
                                if (seen.contains(x + " " + y + " " + direction)) {
                                    loop++;
                                }
                                else {
                                    seen.add(x + " " + y + " " + direction);
                                }
                            } else {
                                y--;
                            }
                        break;

                    case 'R':
                            if (map[x][y + 1] == '#' || map[x][y + 1] == 'O') {
                                direction = 'D';
                                if (seen.contains(x + " " + y + " " + direction)) {
                                    loop++;
                                }
                                else {
                                    seen.add(x + " " + y + " " + direction);
                                }
                            } else {
                                y++;
                            }
                        break;

                    case 'U':
                            if (map[x - 1][y] == '#' || map[x - 1][y] == 'O') {
                                direction = 'R';
                                if (seen.contains(x + " " + y + " " + direction)) {
                                    loop++;
                                }
                                else {
                                    seen.add(x + " " + y + " " + direction);
                                }
                            } else {
                                x--;
                            }
                        break;

                    case 'D':
                            if (map[x + 1][y] == '#' || map[x + 1][y] == 'O') {
                                direction = 'L';
                                if (seen.contains(x + " " + y + " " + direction)) {
                                    loop++;
                                }
                                else {
                                    seen.add(x + " " + y + " " + direction);
                                }
                            } else {
                                x++;
                            }
                        break;

                    default:
                        break;
                }
            }
            result++;
            // loop = -1;
            // System.out.printf("Loop detected, loop number: " + result);
        } catch (Exception e) {
            // for (char[] cs : map) {
            // for (char c : cs) {
            // System.out.printf(c + " ");
            // // if (c == 'X') { result++;}
            // }
            // System.out.println("\n");

            // }
        }

    }

    static void bruteforce() {
        for (int k = 0; k < map.length; k++) {
            for (int l = 0; l < map[0].length; l++) {
                if (map[k][l] == 'X') {
                    map[k][l] = 'O';
                    // System.out.println("Currently at " + k + " " + l);
                    // mapprinter(map);
                    guardmovementmark(map);
                    map[k][l] = 'X';
                }
            }
        }
    }

    // static void printer(char[][] map, int x, int y, char direction) {
    //     System.out.println("Current direction is " + direction);
    //     for (char[] cs : map) {
    //         for (char c : cs) {
    //             System.out.printf(c + " ");
    //         }
    //         System.out.println("\n");
    //     }
    //     System.out.println("\n");
    // }

    // static void mapprinter(char[][] map) {
    //     for (char[] cs : map) {
    //         for (char c : cs) {
    //             System.out.printf(c + "");
    //         }
    //         System.out.println("\n");
    //     }
    //     System.out.println("\n");
    // }
}