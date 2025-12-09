package Day07;

import java.util.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Day07 {
    public static void main(String[] args) throws Exception {
        Part2();
    }

    private static void Part1() throws Exception {
        Path path = Path.of("Day07/Day07.input");
        List<char[]> lines = Files.readAllLines(path, StandardCharsets.UTF_8).stream().map(s -> s.toCharArray())
                .toList();

        int split = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length; j++) {
                char c = lines.get(i)[j];
                if (c == 'S')
                    lines.get(i + 1)[j] = '|';
                else if (c == '|' && i + 1 < lines.size())
                    if (lines.get(i + 1)[j] == '^') {
                        split++;
                        if (j - 1 >= 0)
                            lines.get(i + 1)[j - 1] = '|';
                        if (j + 1 < lines.get(i).length)
                            lines.get(i + 1)[j + 1] = '|';
                    } else
                        lines.get(i + 1)[j] = '|';
            }
        }

        System.out.println("Nb split : " + split);
    }

    private static void Part2() throws Exception {
        Path path = Path.of("Day07/Day07.input");
        List<char[]> lines = Files.readAllLines(path, StandardCharsets.UTF_8).stream().map(s -> s.toCharArray())
                .toList();

        int m = lines.size();        // nombre de lignes
        int n = lines.get(0).length;     // nombre de colonnes

        // DP table
        long[][] dp = new long[m][n];

        for (int j = 0; j < lines.get(0).length; j++) {
                char c = lines.get(0)[j];
                if (c == 'S') {
                    dp[0][j] = 1;
                }
            }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < lines.get(i).length; j++) {
                if (lines.get(i)[j] == '^') 
                    dp[i][j] = 0;
                else 
                    dp[i][j] = dp[i-1][j];
                if (j+1 < n && lines.get(i)[j+1] == '^') 
                    dp[i][j] += dp[i-1][j+1];
                if(j-1 >= 0 && lines.get(i)[j-1] == '^') 
                    dp[i][j] += dp[i-1][j-1];   
            }                 
                
        }

        BigInteger total = BigInteger.ZERO;
        for (int j = 0; j < n; j++) {
                total = total.add(BigInteger.valueOf(dp[m-1][j]));
        }

        System.out.println("Nb timeline : " + total);
    }
}
