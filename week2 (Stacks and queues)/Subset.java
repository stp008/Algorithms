package week2;

import edu.princeton.cs.introcs.StdIn;

public class Subset {
	public static void main(String[] args) {
		try {
				int k = Integer.parseInt(args[0]);
			//sub.k = Integer.parseInt(args[0]);
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			while(!StdIn.isEmpty()) { rq.enqueue(StdIn.readString()); }
			for( int i = 0; i < k ; i++) {
					System.out.println(rq.dequeue());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
