package Day06;

import Common.Core;
import Common.Day;

import java.util.Scanner;

public class Main {
    static final int DIM = 130;
    public static void main(String[] args) {
        Day<Integer, Integer> aocDay = new Day<>(6, "distinct positions", "");

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

        while (guard.inMap()) {
            guard.move();
        }

        aocDay.recordPart1(guard.uniqueVisited());
//        aocDay.recordPart2(0);

        aocDay.complete();
    }
}
