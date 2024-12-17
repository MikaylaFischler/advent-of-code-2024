package Day06;

import Common.Core;
import Common.Day;
import Common.Point;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final int DIM = 130;
    public static void main(String[] args) {
        Day<Integer, Integer> aocDay = new Day<>(6, "distinct positions", "obstruction positions");

        Scanner input = aocDay.start();

        boolean[][] room = new boolean[DIM][DIM];

        int iX, iY = 0;
        int gX = 0, gY = 0;

        while (input.hasNextLine()) {
            String line = input.nextLine();

            if (!line.isEmpty()) {
                char[] lineC = line.toCharArray();

                for (iX = 0; iX < lineC.length; iX++) {
                    room[iY][iX] = lineC[iX] == '#';

                    if (lineC[iX] == '^') {
                        // record location of guard
                        gX = iX;
                        gY = iY;
                    }
                }

                iY++;
            }
        }

        System.out.printf(Core.WHITE + "found guard at %d, %d\n" + Core.RESET, gX, gY);
        Guard guard = new Guard(gX, gY, room);

        // part 1; simply run the route

        while (guard.inMap()) guard.move();

        aocDay.recordPart1(guard.uniqueVisited());

        // part 2; try adding obstructions on the route (only one per run) to find loops

        int loopCount = 0;
        int progress = 0;

        ArrayList<Point> visitedSpots = guard.getVisited();

        for (Point spot : visitedSpots) {
            if (progress % 100 == 0) {
                System.out.printf("%.1f%% complete...\n", ((double)progress / visitedSpots.size()) * 100);
            }

            guard.reset();
            guard.addObstruction(spot.x(), spot.y());

            while (guard.inMap() && !guard.inLoop()) guard.move();

            guard.clearObstruction(spot.x(), spot.y());

            loopCount += guard.inLoop() ? 1 : 0;
            progress++;
        }

        aocDay.recordPart2(loopCount);

        aocDay.complete();
    }
}
