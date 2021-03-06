import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
	 public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>(){
	    	public int compare(Point o1, Point o2) {
				if (slopeTo(o1) < slopeTo(o2)) return -1;
				if (slopeTo(o1) > slopeTo(o2)) return 1;
				return 0;
			}	
	    };         // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
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
    	if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
    	if (this.x == that.x) return Double.POSITIVE_INFINITY;
    	if (this.y == that.y) return 0.0d;
        
    	return ((double)(that.y - this.y))/((double)(that.x - this.x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y == that.y) return this.x - that.x;
        return this.y - that.y;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	System.out.println((double)10/4);
    }
}