package Day04;

import java.util.*;
import java.nio.file.*;

public class Day04 {
    public static void main(String[] args) throws Exception {
        Part2();
    }

    private static void Part1() throws Exception {
        List<String> lines = Files.readAllLines(Path.of("Day04/Day04.input"));

        String[][] rolls = new String[lines.size()][];
        for (String line : lines) {
            String[] lineRolls = line.split("");
            rolls[lines.indexOf(line)] = lineRolls;
        }

        int count = 0;
        for (int i = 0; i < rolls.length; i++) {
            for (int j = 0; j < rolls[i].length; j++) {
                if (rolls[i][j].equals("@")) {
                    int countAdjacent = 0;
                    if (j > 0)
                        countAdjacent += rolls[i][j - 1].equals("@") ? 1 : 0;
                    if (j + 1 < rolls[i].length)
                        countAdjacent += rolls[i][j + 1].equals("@") ? 1 : 0;
                    if (i > 0 && j > 0)
                        countAdjacent += rolls[i - 1][j - 1].equals("@") ? 1 : 0;
                    if (i > 0 && j + 1 < rolls[i].length)
                        countAdjacent += rolls[i - 1][j + 1].equals("@") ? 1 : 0;
                    if (i > 0)
                        countAdjacent += rolls[i - 1][j].equals("@") ? 1 : 0;
                    if (i + 1 < rolls.length && j > 0)
                        countAdjacent += rolls[i + 1][j - 1].equals("@") ? 1 : 0;
                    if (i + 1 < rolls.length)
                        countAdjacent += rolls[i + 1][j].equals("@") ? 1 : 0;
                    if (i + 1 < rolls.length && j + 1 < rolls[i].length)
                        countAdjacent += rolls[i + 1][j + 1].equals("@") ? 1 : 0;
                    System.out.println("At position (" + i + "," + j + ") found " + countAdjacent + " adjacents.");
                    if (countAdjacent < 4) {
                        count++;
                        System.out.println("At position (" + i + "," + j + ") found less than 4 adjacents.");
                    }
                }
            }
        }
        System.out.println("Sum is " + count);
    }

    private static void Part2() throws Exception {
        List<String> lines = Files.readAllLines(Path.of("Day04/Day04.input"));

        String[][] rolls = new String[lines.size()][];
        for (String line : lines) {
            String[] lineRolls = line.split("");
            rolls[lines.indexOf(line)] = lineRolls;
        }

        int countRemoved = 0;

        int prevCountRemoved = -1;
        while (countRemoved > prevCountRemoved) {
            prevCountRemoved = countRemoved;
            for (int i = 0; i < rolls.length; i++) {
                for (int j = 0; j < rolls[i].length; j++) {
                    if (rolls[i][j].equals("@")) {
                        int countAdjacent = 0;
                        if (j > 0)
                            countAdjacent += rolls[i][j - 1].equals("@") ? 1 : 0;
                        if (j + 1 < rolls[i].length)
                            countAdjacent += rolls[i][j + 1].equals("@") ? 1 : 0;
                        if (i > 0 && j > 0)
                            countAdjacent += rolls[i - 1][j - 1].equals("@") ? 1 : 0;
                        if (i > 0 && j + 1 < rolls[i].length)
                            countAdjacent += rolls[i - 1][j + 1].equals("@") ? 1 : 0;
                        if (i > 0)
                            countAdjacent += rolls[i - 1][j].equals("@") ? 1 : 0;
                        if (i + 1 < rolls.length && j > 0)
                            countAdjacent += rolls[i + 1][j - 1].equals("@") ? 1 : 0;
                        if (i + 1 < rolls.length)
                            countAdjacent += rolls[i + 1][j].equals("@") ? 1 : 0;
                        if (i + 1 < rolls.length && j + 1 < rolls[i].length)
                            countAdjacent += rolls[i + 1][j + 1].equals("@") ? 1 : 0;
                        System.out.println("At position (" + i + "," + j + ") found " + countAdjacent + " adjacents.");
                        if (countAdjacent < 4) {
                            System.out.println("At position (" + i + "," + j + ") found less than 4 adjacents.");
                            rolls[i][j] = ".";
                            System.out.println("At position (" + i + "," + j + ") paper removed");
                            countRemoved++;
                        }
                    }
                }
            }

        }

        System.out.println("Total paper removed is " + countRemoved);
    }
}
