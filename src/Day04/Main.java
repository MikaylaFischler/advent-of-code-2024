package Day04;

import Common.Day;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Day<Integer, Integer> aocDay = new Day<>(4, "XMAS count", "X-MAS count");

        Scanner input = aocDay.start();

        ArrayList<String> lines = new ArrayList<>();

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (!line.isEmpty()) lines.add(line);
        }

        char[][] puzzle = new char[lines.size()][];

        for (int y = 0; y < lines.size(); y++) {
            puzzle[y] = lines.get(y).toCharArray();
        }

        int xmas_count = 0; // XMAS
        int cmas_count = 0; // cross-MAS

        // look for XMAS
        for (int y = 0; y < puzzle.length - 3; y++) {
            for (int x = 0; x < puzzle[y].length - 3; x++) {
                xmas_count += checkSectorXMAS(makeSector(puzzle, 4, x, y), x == 0, y == 0);
            }
        }

        // look for crossing MAS
        for (int y = 0; y < puzzle.length - 2; y++) {
            for (int x = 0; x < puzzle[y].length - 2; x++) {
                cmas_count += checkSectorCrossMAS(makeSector(puzzle, 3, x, y));
            }
        }

        aocDay.recordPart1(xmas_count);
        aocDay.recordPart2(cmas_count);

        aocDay.complete();
    }

    /**
     * create a size x size square sector to analyze
     * @param puzzle full input
     * @param size size length
     * @param iX starting x
     * @param iY starting y
     * @return size x size sector
     */
    public static char[][] makeSector(char[][] puzzle, int size, int iX, int iY) {
        char[][] sector = new char[size][size];

        for (int y = 0; y < size; y++) {
            System.arraycopy(puzzle[y + iY], iX, sector[y], 0, size);
        }

        return sector;
    }

    /**
     * check a 4x4 sector for XMAS occurrences, skipping first 3 rows and columns if not at edges
     * @param sector 4x4 input sector
     * @param xEdge if this sector is at an X edge
     * @param yEdge if this sector is at a Y edge
     * @return number of XMAS occurrences
     */
    public static int checkSectorXMAS(char[][] sector, boolean xEdge, boolean yEdge) {
        int count = 0;

        // check rows, only checking the newest one if not at an edge
        for (int y = yEdge ? 0 : 3; y < 4; y++) {
            count += checkXMAS(sector[y][0], sector[y][1], sector[y][2], sector[y][3]);
            count += checkXMAS(sector[y][3], sector[y][2], sector[y][1], sector[y][0]);
        }

        // check columns, only checking the newest one if not at an edge
        for (int x = xEdge ? 0 : 3; x < 4; x++) {
            count += checkXMAS(sector[0][x], sector[1][x], sector[2][x], sector[3][x]);
            count += checkXMAS(sector[3][x], sector[2][x], sector[1][x], sector[0][x]);
        }

        // check diagonals
        count += checkXMAS(sector[0][0], sector[1][1], sector[2][2], sector[3][3]);
        count += checkXMAS(sector[3][3], sector[2][2], sector[1][1], sector[0][0]);
        count += checkXMAS(sector[0][3], sector[1][2], sector[2][1], sector[3][0]);
        count += checkXMAS(sector[3][0], sector[2][1], sector[1][2], sector[0][3]);

        return count;
    }

    /**
     * check for MAS crossing occurrences
     * @param sector 3x3 input sector
     * @return 1 if there is a crossing MAS, 0 otherwise
     */
    public static int checkSectorCrossMAS(char[][] sector) {
        boolean match;

        // check 0,0 to 2,2 bidirectional
        match  = checkMAS(sector[0][0], sector[1][1], sector[2][2]) || checkMAS(sector[2][2], sector[1][1], sector[0][0]);
        // check 0,2 to 2,0 bidirectional
        match &= checkMAS(sector[0][2], sector[1][1], sector[2][0]) || checkMAS(sector[2][0], sector[1][1], sector[0][2]);

        return match ? 1 : 0;
    }

    private static int checkXMAS(char a, char b, char c, char d) {
        return (a == 'X' && b == 'M' && c == 'A' && d == 'S') ? 1 : 0;
    }

    private static boolean checkMAS(char a, char b, char c) {
        return a == 'M' && b == 'A' && c == 'S';
    }
}
