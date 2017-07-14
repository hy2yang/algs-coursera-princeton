import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	public static void main(String[] args)  // TODO Auto-generated method stub
	{
	}
	
	
	private int[] grid;
	private int opensites;
	private int s;
	private WeightedQuickUnionUF a,b;
	
	public Percolation(int n)   // create n-by-n grid, with all sites blocked
	{  
		if (n<1) {
            throw new IllegalArgumentException("the grid can not be smaller than 1x1");  
        }
		s=n;
		a=new WeightedQuickUnionUF(s*s+2);  // s*s+2 sites, index from 0 to s*s+1
		b=new WeightedQuickUnionUF(s*s+1);  // no bottom dummy
		opensites=0;
		grid= new int[s*s+2];  // 0=top dummy, s*s+1=bottom dummy, grid index 1~s*s
		
	}
	
	private void validate(int p) {
        if (p <1 || p > s) {
            throw new IllegalArgumentException("index " + p + " is not between 1 and " + s);  
        }
    }
	
	public boolean isOpen(int row, int col)
	{
		validate(row);
		validate(col);
		int i=(row-1)*s+col;
		return (grid[i]==1);
	}
	
	public boolean isFull(int row, int col)
	{
		validate(row);
		validate(col);
		int i=(row-1)*s+col;
		return (b.connected(i,0));
	}
	
	public int numberOfOpenSites()
	{
		return opensites;
	}
	
	public  void open(int row, int col)
	{
		validate(row);
		validate(col);
		int i=(row-1)*s+col;
		
		if (grid[i]==0)
		{
			grid[i]=1;
			opensites+=1;
			
			if (i>0&i<=s) //top row
			{
				a.union(i,0);
				b.union(i,0);
			}
			
			if (i>s*s-s) //bottom row
			{
				a.union(i,s*s+1);
			}
			
			if((i%s!=1) & grid[i-1]==1)  //not left col
			{
				a.union(i,i-1);
				b.union(i,i-1);
			}
			
			if((i%s!=0) & grid[i+1]==1) //not right col
			{
				a.union(i,i+1);
				b.union(i,i+1);
			}
			
			if((i>s) && grid[i-s]==1)  //not top
			{
				a.union(i,i-s);
				b.union(i,i-s);
			}
			
			if((i<=s*s-s) && grid[i+s]==1)  //not bottom
			{
				a.union(i,i+s);
				b.union(i,i+s);
			}
		}
		
		
	}
	
	public boolean percolates()
	{
	 return (a.connected(0, s*s+1));
	}
	
}
