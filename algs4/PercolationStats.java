import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private int n,T;
	private double[] x;
	
	public PercolationStats(int a, int trials)
	{
		if (a <1 || trials <1) {
            throw new IllegalArgumentException();  
        }
		n=a;
		T=trials;
		x=new double[T];
		
		for (int i=0;i<T;i++)
		{
			Percolation perc = new Percolation(n);			
			while (perc.percolates()==false)
			{
				int row,col;
				do{
					row= StdRandom.uniform(n)+1;
					col= StdRandom.uniform(n)+1;
				}while (perc.isOpen(row, col));
				
				perc.open(row, col);				
			}
			x[i]=((double)perc.numberOfOpenSites()/(n*n));
		}
	}
	
	public double mean()
	{
	    return StdStats.mean(x);
	}
	
	public double stddev()
	{
		if(T==1)
		{return Double.NaN;}
		return StdStats.stddev(x);
	}
	
	public double confidenceLo()
	{
		
		return (StdStats.mean(x)-1.96*StdStats.stddev(x)/Math.sqrt(T));
	}
	
	public double confidenceHi()
    {
		
		return (StdStats.mean(x)+1.96*StdStats.stddev(x)/Math.sqrt(T));
	}
	
	public static void main(String[] args)
	{
		int a=Integer.parseInt(args[0]);
		int trials =Integer.parseInt(args[1]);
		PercolationStats sample = new PercolationStats(a, trials);
		double lo=sample.confidenceLo();
		double hi=sample.confidenceHi();
		StdOut.printf("%-23s = %.15f\n","mean",sample.mean());
		StdOut.printf("%-23s = %.15f\n","stddev",sample.stddev());
		StdOut.printf("%-23s = [%.15f,%.15f]\n","95% confidence interval",lo,hi);
	}
	
}
