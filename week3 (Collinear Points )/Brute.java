import java.util.Arrays;

public class Brute {
	private Point[] points;
	private int N;
	private Point p;
	private Point q;
	private Point r;
	private Point s;
	
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		
		Brute brute = new Brute();
		brute.pointsToArray(new In(args[0]));
		brute.findLines();
	}
	
	private void pointsToArray(In in) {
		N = in.readInt();
		points = new Point[N];
		for (int i = 0; i < N; i++){
			points[i] = new Point(in.readInt(), in.readInt());	
			points[i].draw();
			//System.out.println(points[i]);
		}
	}
	
	private void findLines() {
		Arrays.sort(points);
		for (int i = 0; i < points.length; i++) {
			p = points[i];
			for (int j = i+1; j < points.length; j++) {
				q = points[j];
				for (int h = j+1; h < points.length; h++) {
					r = points[h];
					double slopeR = p.slopeTo(r);
					double slopeQ = p.slopeTo(q);						
					if(slopeR != slopeQ) continue;	
					for (int z = h+1; z < points.length; z++) {
						s = points[z];
						if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
							System.out.println(p + " -> " + q + " -> " + r + " -> " + s);
							p.drawTo(s);
						}
					}
				}
			}
		}
	}
}
