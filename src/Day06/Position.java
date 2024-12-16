package Day06;

public class Position {
    private final int x;
    private final int y;
    private final Orientation dir;

    public Position(int x, int y, Orientation dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Orientation getOrientation() { return dir; }

    @Override
    public String toString() { return "(" + x + ", " + y + ") facing " + dir.name(); }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;

        if ((obj != null) && (obj.getClass() == this.getClass())) {
            final Position cmp = (Position)obj;
            return (this.x == cmp.getX()) && (this.y == cmp.getY()) && (this.dir == cmp.getOrientation());
        }

        return eq;
    }
}
