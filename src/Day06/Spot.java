package Day06;

public class Spot {
    private final int x;
    private final int y;

    public Spot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public String toString() { return "(" + x + ", " + y + ")"; }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;

        if ((obj != null) && (obj.getClass() == this.getClass())) {
            final Spot cmp = (Spot)obj;
            return (this.x == cmp.getX()) && (this.y == cmp.getY());
        }

        return eq;
    }
}
