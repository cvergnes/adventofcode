package Day08;

import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

class Point {
    int x;
    int y;
    int z;
    ArrayList<Point> neighbors = new ArrayList<Point>();

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean CanAddNeighbors(Point p) {
        return neighbors.size() < 2;
    }

    public void AddNeighbors(Point p) {
        if (!CanAddNeighbors(p))
            throw new IllegalArgumentException("Cannot add more neighbors to this point");
        neighbors.add(p);
    }

    public double GetDistance(Point other) {
        return Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) + Math.pow(this.z - other.z, 2);
    }

    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}

class Distance {
    Point p1;
    Point p2;
    double distance;

    public Distance(Point p1, Point p2, double distance) {
        this.p1 = p1;
        this.p2 = p2;
        this.distance = distance;
    }

    public int getDistance() {
        return (int) distance;
    }

    public Point getPoint1() {
        return p1;
    }

    public Point getPoint2() {
        return p2;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Distance distance1 = (Distance) o;
        return (Objects.equals(p1, distance1.p1) && Objects.equals(p2, distance1.p2))
                || (Objects.equals(p1, distance1.p2) && Objects.equals(p2, distance1.p1));
    }
}

public class Day08 {

    static List<Integer> pathLengths = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Part2();
    }

    private static void Part1() throws Exception {
        Path path = Path.of("Day08/Day08.input");
        List<Point> lines = Files.readAllLines(path, StandardCharsets.UTF_8).stream()
                .map(s -> new Point(Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1]),
                        Integer.parseInt(s.split(",")[2])))
                .toList();

        ArrayList<Distance> distances = new ArrayList<Distance>();
        for (int i = 0; i < lines.size(); i++) {
            Point p1 = lines.get(i);
            for (int j = i + 1; j < lines.size(); j++) {
                Point p2 = lines.get(j);
                double dist = p1.GetDistance(p2);
                distances.add(new Distance(p1, p2, dist));
            }
        }
        Collections.sort(distances, Comparator.comparingDouble(Distance::getDistance));


        ArrayList<Set<Point>> chains = new ArrayList<Set<Point>>();
        for (int i = 0; i < 1000; i++) {
            Distance d = distances.get(i);
            Point p1 = d.getPoint1();
            Point p2 = d.getPoint2();
            Set<Point> chain1 = GetChainByPoint(chains, p1);
            Set<Point> chain2 = GetChainByPoint(chains, p2);

            if (chain1 == chain2 && chain2 != null) {
                continue;
            } else if (chain1 == null && chain2 == null) {
                Set<Point> newChain = new HashSet<Point>();
                newChain.add(p1);
                newChain.add(p2);
                chains.add(newChain);
            } else if (chain1 != null && chain2 == null) {
                    chain1.add(p2);
            } else if (chain1 == null && chain2 != null) {
                    chain2.add(p1);
            } else {
                    chain1.addAll(chain2);
                    chains.remove(chain2);
            }
        }

        int result = 1;
        List<Integer> chainSizes = chains.stream().map(set -> set.size()).sorted().toList();
        for(int i = chainSizes.size() - 1; i >= chainSizes.size() - 3 ; i--) {
            result *= chainSizes.get(i);
        }
        System.out.println("Result is " + result);

    }

    private static Set<Point> GetChainByPoint(ArrayList<Set<Point>> chains, Point p) {
        for (Set<Point> chain : chains) {
            if (chain.contains(p)) {
                return chain;
            }
        }
        return null;
    }

    private static void Part2() throws Exception {
             Path path = Path.of("Day08/Day08.input");
        List<Point> lines = Files.readAllLines(path, StandardCharsets.UTF_8).stream()
                .map(s -> new Point(Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1]),
                        Integer.parseInt(s.split(",")[2])))
                .toList();

        ArrayList<Distance> distances = new ArrayList<Distance>();
        for (int i = 0; i < lines.size(); i++) {
            Point p1 = lines.get(i);
            for (int j = i + 1; j < lines.size(); j++) {
                Point p2 = lines.get(j);
                double dist = p1.GetDistance(p2);
                distances.add(new Distance(p1, p2, dist));
            }
        }
        Collections.sort(distances, Comparator.comparingDouble(Distance::getDistance));

        ArrayList<Set<Point>> chains = new ArrayList<Set<Point>>();
        int i = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        while(chains.size() != 1 || chains.size() == 0 || chains.get(0).size() != lines.size()) {
            Distance d = distances.get(i);
            Point p1 = d.getPoint1();
            Point p2 = d.getPoint2();
            Set<Point> chain1 = GetChainByPoint(chains, p1);
            Set<Point> chain2 = GetChainByPoint(chains, p2);

            if (chain1 == chain2 && chain2 != null) {
                i++;
                continue;
            } else if (chain1 == null && chain2 == null) {
                Set<Point> newChain = new HashSet<Point>();
                newChain.add(p1);
                newChain.add(p2);
                chains.add(newChain);
            } else if (chain1 != null && chain2 == null) {
                    chain1.add(p2);
            } else if (chain1 == null && chain2 != null) {
                    chain2.add(p1);
            } else {
                chain1.addAll(chain2);
                chains.remove(chain2);
            }
            queue.addLast(p1.x);
            queue.addLast(p2.x);
            i++;
        }
        int x1 = queue.removeLast();
        int x2 = queue.getLast();
        System.out.println("Result is " + x1*x2);
    }
}
