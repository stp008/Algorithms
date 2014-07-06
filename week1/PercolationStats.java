package week1;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
	
	private int T;
	private int N;
	private double[] stats;
	private double stddev;
	private double mean;

	public PercolationStats(int N, int T) {
		if (N <= 0 || T <=0) throw new IllegalArgumentException(); 
		this.N = N;
		this.T = T;
		this.stats = new double[T];
		for(int i = 0; i < T; i++){
			Percolation model = new Percolation(this.N);			
			double count = 0;
			while (!model.percolates()){
				int row = StdRandom.uniform(1, N+1);
				int column = StdRandom.uniform(1, N+1);
				
				while (model.isOpen(row, column)) {
					row = StdRandom.uniform(1, N+1);
					column = StdRandom.uniform(1, N+1);
				}							
				model.open(row, column);
				count++;				
			}			
			stats[i] = (count/(N*N));
		}
		this.stddev = StdStats.stddevp(this.stats);
		this.mean = StdStats.mean(this.stats); 
	}
	
	public double mean() {
		return mean; 
	}
	
	public double stddev() {
		return stddev;
	}
	
	public double confidenceLo() { 
		return mean - (1.96*stddev/Math.sqrt(T));
	}
	
	public double confidenceHi() {
		return mean + (1.96*stddev/Math.sqrt(T));
	}
	
	public static void main(String[] args) { 
		//PercolationStats stats = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
		PercolationStats stats = new PercolationStats(200, 100);
		System.out.println("mean                    = " + stats.mean());
		System.out.println("stddev                  = " + stats.stddev());
		System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
	}
	
}
