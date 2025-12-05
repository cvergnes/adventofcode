package Day01;

import java.util.*;
import java.nio.file.*;

public class Day01 {
    public static void main(String[] args) throws Exception {
        // Part1();
        Part2();
    }

    public static void Part1() throws Exception {
        List<String> lines = Files.readAllLines(Path.of("Day01.input"));
        int dial = 50;
        int counter = 0;
        for (String line : lines) {
            System.out.println("Instruction: " + line);
            System.out.println("Dial: " + dial);
            System.out.println("Counter: " + counter);
            String direction = line.substring(0, 1);
            int value = Integer.parseInt(line.substring(1));
            if (direction.equals("L")) {
                dial -= value;
            } else if (direction.equals("R")) {
                dial += value;
            }
            dial = dial % 100;
            if (dial < 0)
                dial += 100;
            if (dial == 0)
                counter++;
        }
        System.out.println("Le nombre de fois où le cadran est revenu à 0 est : " + counter);

    }

    public static void Part1bis() throws Exception {
        List<String> lines = Files.readAllLines(Path.of("Day01.input"));
        int dial = 50;
        int counter = 0;
        for (String line : lines) {
            System.out.println("Instruction: " + line);
            System.out.println("Dial: " + dial);
            System.out.println("Counter: " + counter);
            String direction = line.substring(0, 1);
            int value = Integer.parseInt(line.substring(1));
            if (direction.equals("L")) {
                dial -= value;
            } else if (direction.equals("R")) {
                dial += value;
            }
            if (dial >= 100) {
                dial = dial % 100;
            } else if (dial < 0) {
                dial = (dial % 100);
            }
            if (dial == 0)
                counter++;
        }
        System.out.println("Le nombre de fois où le cadran est passé à 0 est : " + counter);

    }

    public static void Part2() throws Exception {
        List<String> lines = Files.readAllLines(Path.of("Day01/Day01.input"));
        int dial = 50;
        int counter = 0;
        for (String line : lines) {
            System.out.println("Instruction: " + line);
            System.out.println("Dial: " + dial);
            System.out.println("Counter: " + counter);
            String direction = line.substring(0, 1);
            int value = Integer.parseInt(line.substring(1));
            int prevVal = dial;
            if (direction.equals("L")) {
                dial -= value;
            } else if (direction.equals("R")) {
                dial += value;
            }
            if (dial >= 100) {
                counter += (dial / 100);
                dial = dial % 100;
            } else if (dial < 0) {
                counter += (Math.abs(dial) / 100) + (prevVal == 0 ? 0 : 1);
                dial = Math.floorMod(dial, 100);
            } else if (dial == 0)
                counter++;
        }
        System.out.println("Le nombre de fois où le cadran est passé ou revenu à 0 est : " + counter);

    }

    public static void Part3() throws Exception {
        List<String> lines = Files.readAllLines(Path.of("Day01.input"));
        int dial = 50;
        int counter = 0;
        for (String line : lines) {
            System.out.println("Instruction: " + line);
            System.out.println("Dial: " + dial);
            System.out.println("Counter: " + counter);
            String direction = line.substring(0, 1);
            int value = Integer.parseInt(line.substring(1));
            int prev = dial;
            int prevNorm = Math.floorMod(prev, 100);

            int crosses = 0;
            if (direction.equals("R")) {
                // count multiples of 100 crossed when moving from prevNorm to prevNorm+value
                // (exclusive of start, inclusive of end)
                crosses = Math.floorDiv(prevNorm + value, 100) - Math.floorDiv(prevNorm, 100);
                dial = prev + value;
            } else if (direction.equals("L")) {
                // moving left: count multiples of 100 crossed when moving from prevNorm-value
                // to prevNorm (exclusive of start, inclusive of end)
                crosses = Math.floorDiv(prevNorm, 100) - Math.floorDiv(prevNorm - value, 100);
                dial = prev - value;
            }

            counter += crosses;
            dial = Math.floorMod(dial, 100);
            System.out.println("Crosses this move: " + crosses);
        }
        System.out.println("Le nombre de fois où le cadran est passé ou revenu à 0 est : " + counter);

    }
}
