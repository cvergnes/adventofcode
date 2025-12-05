package Day03;
import java.util.*;
import java.math.BigInteger;
import java.nio.file.*;

public class Day03 {
    public static void main(String[] args) throws Exception {
        Part2();
        
    }

private static void Part2() throws Exception {
    List<String> lines =   Files.readAllLines(Path.of("Day03/Day03.input"));

    BigInteger count = BigInteger.ZERO;

    for (String line : lines) {
        String[] joltages = line.split("");
        int[] joltageInts =  Arrays.stream(joltages).mapToInt(Integer::parseInt).toArray();

        int[] maxIdx = new int[12];
        int index = 0;
        for(int j = 0; j < 12; j++) {
            maxIdx[j] = index;
            for (int i = index; i < joltageInts.length-11+j; i++) {
                
                if (joltageInts[i] > joltageInts[maxIdx[j]]) {
                    maxIdx[j] = i;
                }
            }
            index = maxIdx[j]+1;
        }
        long joltage = 0;
        for(int i = 0; i < 12; i++) {
            joltage += joltageInts[maxIdx[i]] * Math.pow(10, 12 - i -1);
        }
        System.out.println(" Joltages: " + line);
        System.out.println("Largest: " + joltage);
        count = count.add(BigInteger.valueOf(joltage));

    }
    System.out.println("Sum is " + count);
        
}


    private static void Part1() throws Exception {
        List<String> lines = Files.readAllLines(Path.of("Day03/Day03.input"));

        int count = 0;
        for (String line : lines) {
            String[] joltages = line.split("");
            int[] joltageInts =  Arrays.stream(joltages).mapToInt(Integer::parseInt).toArray();

            int maxIdx = 0;
            for (int i = 1; i < joltageInts.length-1; i++) {
                
                if (joltageInts[i] > joltageInts[maxIdx]) {
                    maxIdx = i;
                }
            }
            int maxIdx2 = maxIdx+1;
            for (int i = maxIdx+1; i < joltageInts.length; i++) {
                if (joltageInts[i] > joltageInts[maxIdx2]) {
                    maxIdx2 = i;
                }
            }
            System.out.println(" Joltages: " + line);
            System.out.println("Largest: " + joltageInts[maxIdx]*10 + joltageInts[maxIdx2]);
            count += joltageInts[maxIdx]*10 + joltageInts[maxIdx2];

        }
        System.out.println("Sum is " + count);
        
    }
}
