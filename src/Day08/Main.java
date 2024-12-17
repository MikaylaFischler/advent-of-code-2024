package Day08;

import Common.Day;
import Common.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Day<Integer, Integer> aocDay = new Day<>(8, "unique locations", "unique locations (resonant)");

        Scanner input = aocDay.start();

        HashMap<String, Network> networks = new HashMap<>();

        int x = 0;
        int y = 0;

        while (input.hasNextLine()) {
            String line = input.nextLine();

            if (!line.isEmpty()) {
                char[] lineC = line.toCharArray();

                for (x = 0; x < lineC.length; x++) {
                    if (lineC[x] != '.') {
                        String id = String.valueOf(lineC[x]);

                        if (!networks.containsKey(id)) {
                            networks.put(id, new Network(lineC[x]));
                        }

                        networks.get(id).addAntenna(new Point(x, y));
                    }
                }

                y++;
            }
        }

        Network.MAX_Y = y - 1;
        Network.MAX_X = x - 1;

        HashMap<Point, Integer> points1 = new HashMap<>();
        HashMap<Point, Integer> points2 = new HashMap<>();

        for (Network net : networks.values()) {
            ArrayList<Point> antiNodes = net.getAntiNodes();

            for (Point antiNode : antiNodes) {
                if (!points1.containsKey(antiNode)) {
                    points1.put(antiNode, 1);
                }
            }
        }

        aocDay.recordPart1(points1.size());

        for (Network net : networks.values()) {
            ArrayList<Point> antiNodes = net.getResonantAntiNodes();

            for (Point antiNode : antiNodes) {
                if (!points2.containsKey(antiNode)) {
                    points2.put(antiNode, 1);
                }
            }
        }

        aocDay.recordPart2(points2.size());

        aocDay.complete();
    }
}
