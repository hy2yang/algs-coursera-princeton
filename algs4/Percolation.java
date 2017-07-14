import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[] grid;
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
		grid= new boolean[s*s+2];  // 0=top dummy, s*s+1=bottom dummy, grid index 1~s*s
		
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
		row-=1;
		int i=row*s+col;
		return (grid[i]);
	}
	
	public boolean isFull(int row, int col)
	{
		validate(row);
		validate(col);
		row-=1;
		int i=row*s+col;
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
		row-=1;
		int i=row*s+col;
		
		if (!grid[i])
		{
			grid[i]=true;
			opensites+=1;
			
			if (row==0) //top row
			{
				a.union(i,0);
				b.union(i,0);
			}
			
			if (row==s-1) //bottom row
			{
				a.union(i,s*s+1);
			}
			
			if((col>1) && grid[i-1])  //not left col
			{
				a.union(i,i-1);
				b.union(i,i-1);
			}
			
			if((col<s) & grid[i+1]) //not right col
			{
				a.union(i,i+1);
				b.union(i,i+1);
			}
			
			if((row>0) && grid[i-s])  //not top
			{
				a.union(i,i-s);
				b.union(i,i-s);
			}
			
			if((row<s-1) && grid[i+s])  //not bottom
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
	
	public static void main(String[] args)  
	{                                     // TODO Auto-generated method stub
	}
		
}
