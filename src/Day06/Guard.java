package Day06;

import java.util.ArrayList;

import static Day06.Orientation.*;

public class Guard {
    private final int iniX;
    private final int iniY;
    private int curX;
    private int curY;

    private Orientation dir = North;

    private boolean exited = false;
    private boolean looping = false;

    private boolean[][] map;

    private final ArrayList<Position> route = new ArrayList<>();
    private final ArrayList<Spot> visited = new ArrayList<>();

    public Guard(int initialX, int initialY, boolean[][] map) {
        this.iniX = initialX;
        this.iniY = initialY;
        this.curX = initialX;
        this.curY = initialY;
        this.map = map.clone();
    }

    public void reset() {
        curX = iniX;
        curY = iniY;
        dir = North;

        exited = false;
        looping = false;

        route.clear();
        visited.clear();
    }

    public void move() {
        if (exited) return;

        int nextX = curX;
        int nextY = curY;

        switch (dir) {
            case North -> nextY--;
            case East -> nextX++;
            case South -> nextY++;
            case West -> nextX--;
        }

        exited = (nextY < 0 || nextY >= map.length) || (nextX < 0 || nextX >= map[0].length);

        if (!exited) {
            if (map[nextY][nextX]) {
                looping = route.contains(new Position(curX, curY, dir));
                route.add(new Position(curX, curY, dir));
                turn();
            } else {
                curX = nextX;
                curY = nextY;

                Spot s = new Spot(curX, curY);
                if (!visited.contains(s)) {
                    visited.add(s);
                }
            }
        }
    }

    public Position getPosition() { return new Position(curX, curY, dir); }

    public boolean inLoop() { return looping; }

    public boolean inMap() { return !exited; }

    public int uniqueVisited() { return visited.size(); }

    public ArrayList<Spot> getVisited() { return new ArrayList<>(visited); }

    public void addObstruction(int x, int y) {
        map[y][x] = true;
    }

    public void clearObstruction(int x, int y) {
        map[y][x] = false;
    }

    public void printMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (y == curY && x == curX) {
                    System.out.print("*");
                } else {
                    System.out.print(map[y][x] ? "#" : ".");
                }
            }

            System.out.println();
        }
    }

    private void turn() {
        switch (dir) {
            case North -> dir = East;
            case East  -> dir = South;
            case South -> dir = West;
            case West  -> dir = North;
        }
    }
}
