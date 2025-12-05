package Day02;
import java.util.*;
import java.nio.file.*;

public class Day02 {
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of("Day02.input"));
        String[] ranges = lines.get(0).split(",");  
        long counter = 0;
        for (String range : ranges) {
            String[] bounds = range.split("-");
            System.out.println("Range: " + bounds[0] + " to " + bounds[1]);
            long lower = Long.parseLong(bounds[0]);
            long upper = Long.parseLong(bounds[1]);
            System.out.println("Numbers in range:");
            for (long i = lower; i <= upper; i++) {
                String numStr = Long.toString(i);
                boolean invalidId = false;
                for (int j = 1; j <= numStr.length() / 2; j++) {
                    String key = numStr.substring(0, j);
                    invalidId = invalidId || key.repeat(numStr.length() / j).equals(numStr);
                }
                if(invalidId) {
                    System.out.println("  Invalid ID: " + numStr);
                    counter += i;
                } else {
                    // System.out.println("  Valid ID: " + numStr);
                }
            }
        }
        System.out.println("Adding up all the invalid IDs  : " + counter);
    }
}
