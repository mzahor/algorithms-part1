/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        double slope = getSlopeTo(that);
        //StdOut.println("Slope from" + this + " to " + that + " is " + slope);
        return slope;
    }

    private double getSlopeTo(Point that) {
        if (that == null) throw new NullPointerException();
        if (that.compareTo(this) == 0) return Double.NEGATIVE_INFINITY;
        if (that.x == this.x) return Double.POSITIVE_INFINITY;
        if (that.y == this.y) return +0.0;
        return (double) (that.y - this.y) / (double) (that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y > that.y) return 1;
        if (this.y < that.y) return -1;
        // this.y == that.y
        if (this.x > that.x) return 1;
        if (this.x < that.x) return -1;
        // both x and y are equal (the same point)
        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    private class SlopeOrder implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            if (o1 == null || o2 == null) throw new NullPointerException();
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);

            if (slope1 == slope2) return 0;
            if (slope1 > slope2) return 1;
            return -1;
        }
    }
}
