package Day08;

import Common.Point;

import java.util.ArrayList;

public class Network {
    ArrayList<Point> antennas = new ArrayList<>();
    private final char id;

    public static int MAX_X = 0;
    public static int MAX_Y = 0;

    public Network(char id) {
        this.id = id;
    }

    public char getID() { return id; }

    public void addAntenna(Point antenna) {
        antennas.add(antenna);
    }

    public ArrayList<Point> getAntiNodes() {
        ArrayList<Point> nodes = new ArrayList<>();

        for (int i = 0; i < antennas.size(); i++) {
            for (int j = i + 1; j < antennas.size(); j++) {
                nodes.addAll(findAntiNodes(antennas.get(i), antennas.get(j)));
            }
        }

        return nodes;
    }

    public ArrayList<Point> getResonantAntiNodes() {
        ArrayList<Point> nodes = new ArrayList<>();

        for (int i = 0; i < antennas.size(); i++) {
            nodes.add(antennas.get(i));

            for (int j = i + 1; j < antennas.size(); j++) {
                nodes.addAll(findResonantAntiNodes(antennas.get(i), antennas.get(j)));
            }
        }

        return nodes;
    }

    public void printMap(ArrayList<Point> antiNodes) {
        for (int y = 0; y <= MAX_Y; y++) {
            for (int x = 0; x <= MAX_X; x++) {
                Point test = new Point(x, y);

                if (antennas.contains(test)) {
                    System.out.print(this.id);
                } else if (antiNodes.contains(test)) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }

            System.out.println();
        }
    }

    private static ArrayList<Point> findAntiNodes(Point a, Point b) {
        int dX = a.x() - b.x();
        int dY = a.y() - b.y();

        ArrayList<Point> nodes = new ArrayList<>();

        if (isNodeInMap(a, dX, dY)) {
            nodes.add(new Point(a.x() + dX, a.y() + dY));
        }

        if (isNodeInMap(b, -dX, -dY)) {
            nodes.add(new Point(b.x() - dX, b.y() - dY));
        }

        return nodes;
    }

    private static ArrayList<Point> findResonantAntiNodes(Point a, Point b) {
        int dX = a.x() - b.x();
        int dY = a.y() - b.y();

        ArrayList<Point> nodes = new ArrayList<>();

        int rX = dX;
        int rY = dY;

        while (isNodeInMap(a, rX, rY)) {
            nodes.add(new Point(a.x() + rX, a.y() + rY));
            rX += dX;
            rY += dY;
        }

        rX = dX;
        rY = dY;

        while (isNodeInMap(b, -rX, -rY)) {
            nodes.add(new Point(b.x() - rX, b.y() - rY));
            rX += dX;
            rY += dY;
        }

        return nodes;
    }

    private static boolean isNodeInMap(Point p, int dX, int dY) {
        return (((p.x() + dX) <= MAX_X) && ((p.x() + dX) >= 0) && ((p.y() + dY) <= MAX_Y) && ((p.y() + dY) >= 0));
    }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;

        if ((obj != null) && (obj.getClass() == this.getClass())) {
            final Network cmp = (Network)obj;
            eq = this.id == cmp.getID();
        }

        return eq;
    }
}
