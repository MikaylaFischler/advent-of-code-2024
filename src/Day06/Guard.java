package Day06;

import java.util.ArrayList;

import static Day06.Orientation.*;

public class Guard {
    private int iniX;
    private int iniY;
    private int curX;
    private int curY;

    private Orientation dir = North;

    private boolean exited = false;

    boolean[][] map;

    ArrayList<Spot> visited = new ArrayList<>();

    public Guard(int initialX, int initialY, boolean[][] map) {
        this.iniX = initialX;
        this.iniY = initialY;
        this.curX = initialX;
        this.curY = initialY;
        this.map = map.clone();
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

    public boolean inMap() { return !exited; }

    public int uniqueVisited() { return visited.size(); }

    public void reset() {
        curX = iniX;
        curY = iniY;
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
