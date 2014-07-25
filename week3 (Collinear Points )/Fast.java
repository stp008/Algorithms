import java.util.Arrays;

public class Fast {

	private Point[] points;
	private Point[] slope;
	private int N;
	private Point p;
	private Point q;
	private Point r;
	private Point s;
	
	public static void main(String[] args) {
		Fast fast = new Fast();
		fast.pointsToArray(new In(args[0]));	
		fast.findLines();
	}
	
	private void pointsToArray(In in){
		N = in.readInt();
		points = new Point[N];
		for (int i = 0; i < N; i++){
			points[i] = new Point(in.readInt(), in.readInt());
			System.out.println(points[i]);
		}
		System.out.println("break");
		slope = points.clone();
		
	}
	
	private void findLines(){
		for (int i = 0; i < N; i++){
			p = points[i];
			Arrays.sort(slope, p.SLOPE_ORDER);	
			for (int j = 1; j < N; j = j+3){
				q = slope[j];
				if(j < N-1){
				r = slope[j+1];
				}
				if (j < N-2){
				s = slope[j+2];
				}
				if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
					System.out.println(p + " -> " + q + " -> " + r + " -> " + s);
					break;
				}
			}		
		}
	}

}
