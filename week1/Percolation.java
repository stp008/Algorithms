package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[] grid;
	private WeightedQuickUnionUF model;
	private WeightedQuickUnionUF fullCheckModel; //solving backwash
	private int N;
	
	public Percolation(int N) {
		if (N <= 0) throw new IllegalArgumentException(); 
		this.N = N;
		grid = new boolean[N*N+2];
		model = new WeightedQuickUnionUF(N*N+2);
		fullCheckModel = new WeightedQuickUnionUF(N*N+2);				
	}

	public void open(int i, int j) { 
		validateIndex(i, j);
		if (isOpen(i, j)) return;
		grid[convertIndex(i, j)] = true; 
		if (i == 1) { model.union(0, convertIndex(i, j)); fullCheckModel.union(0, convertIndex(i, j)); }
		if (i == N) { model.union(N*N+1, convertIndex(i, j)); }
		if (j != 1 && isOpen(i, j-1)) { model.union(convertIndex(i, j), convertIndex(i, j-1)); fullCheckModel.union(convertIndex(i, j), convertIndex(i, j-1)); }	//left cell
		if (j != N && isOpen(i, j+1)) { model.union(convertIndex(i, j), convertIndex(i, j+1)); fullCheckModel.union(convertIndex(i, j), convertIndex(i, j+1)); }	//right cell
		if (i != 1 && isOpen(i-1, j)) { model.union(convertIndex(i, j), convertIndex(i-1, j)); fullCheckModel.union(convertIndex(i, j), convertIndex(i-1, j)); }	//up cell
		if (i != N && isOpen(i+1, j)) { model.union(convertIndex(i, j), convertIndex(i+1, j)); fullCheckModel.union(convertIndex(i, j), convertIndex(i+1, j)); }	//down cell
	}
	
	public boolean isOpen(int i, int j) { 
		validateIndex(i, j);
		return grid[convertIndex(i, j)]; 
	}
	
	public boolean isFull(int i, int j) { 
		validateIndex(i, j);
		return fullCheckModel.connected(0, convertIndex(i, j)); 
	}
	
	public boolean percolates() { 	
		return  model.connected(0, N*N+1);
	}
	
	/*public static void main(String[] args) { 
		Percolation mod = new Percolation(20);
		//mod.open(1,1);
		mod.open(1,2);
		//mod.open(2,2);
		System.out.println(mod.model.connected(0, 1));
		System.out.println(mod.isFull(1, 1));
		System.out.println(mod.percolates());
		System.out.println(mod.convertIndex(9, 1));
		printTable(20);
	}
	
	private static void printTable(int N) { 
		for (int i = 1; i <= N; i++) {
			System.out.println();
			for (int j = 1; j <= N; j++) {
				System.out.print((i-1) * N + j + " ");
			}
		}
	}*/
	
	private void validateIndex(int i, int j) {
		if (i <= 0 || j <= 0 || i > N || j > N) throw new IndexOutOfBoundsException();
	}
	
	private int convertIndex(int i, int j) {
		validateIndex(i, j);
		return (i-1) * N + j;
		//return	(i - 1) * N + (j - 1);
	}
	
}
