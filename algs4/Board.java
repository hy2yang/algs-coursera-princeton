import edu.princeton.cs.algs4.Stack;

public class Board {
    
    private char[] board;
    private int n;
    
    public Board(int[][] blocks){
        n=blocks.length;
        board= new char[n*n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                board[n*i+j]= (char)(blocks[i][j]+48) ;
            }
        }
    }
    
    public int dimension(){
        return n;
    }
    
    public int hamming(){
        int h=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if (board[n*i+j]=='0') continue;
                if (board[n*i+j]!=(char)(n*i+j+49)) h++;
            }
        }
        return h;
    }
    
    public int manhattan(){
        int m=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if (board[n*i+j]=='0') continue;
                if (board[n*i+j]!=(char)(n*i+j+49)){                    
                    m+=Math.abs(i-(board[n*i+j]-49)/n)+Math.abs(j-(board[n*i+j]-49)%n);
                }
            }
        }
        return m;
    }
    
    public boolean isGoal(){
        return (this.hamming()==0);
    }
    
    public Board twin(){
        
        int tblocks[][]=this.to2Dint();
        
        if (tblocks[0][0]==0){
            exchange(tblocks,1,2);
            Board tboard= new Board(tblocks);
            return tboard;
        }
        if (tblocks[0][1]==0){
            exchange(tblocks,0,2);
            Board tboard= new Board(tblocks);
            return tboard;
        }
        
        exchange(tblocks,0,1);
        Board tboard= new Board(tblocks);              
        return tboard;
    }
    
    public Iterable<Board> neighbors(){
        int t[][]=this.to2Dint();
        int pos=-1;
        Stack<Board> nstack = new Stack<Board>();
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if (board[n*i+j]=='0'){
                    pos=n*i+j;
                    break;
                }
            }
        }
        
        if (pos/n>0){
            exchange(t,pos,pos-n);
            Board up= new Board(t);
            nstack.push(up);
            t=this.to2Dint();
        }
            
        if (pos/n<n-1){
            exchange(t,pos,pos+n);
            Board down= new Board(t);
            nstack.push(down);
            t=this.to2Dint();
        }
        
        if (pos%n>0){
            exchange(t,pos,pos-1);
            Board left= new Board(t);
            nstack.push(left);
            t=this.to2Dint();
        }
        
        if (pos%n<n-1){
            exchange(t,pos,pos+1);
            Board right= new Board(t);
            nstack.push(right);
            t=this.to2Dint();
        }
        
        return nstack;
    }
    
    private void exchange(int[][] x, int a, int b){
        int t = x[a/n][a%n];
        x[a/n][a%n]=x[b/n][b%n];
        x[b/n][b%n]=t;
    }
    
    private int[][] to2Dint(){
        int t[][]=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                t[i][j]=board[n*i+j]-48;
            }
        }
        return t;
    }
     
    
    public boolean equals(Object y){
        if (y==null || y==this){
            return false;
        }            
        if (y.getClass()!=this.getClass()){
            return false;
        }
        if (((Board) y).dimension()!=this.dimension()){
            return false;
        }
        if(((Board) y).toString()!=this.toString()){
            return false;
        }
        return true;
        
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2c ", board[n*i+j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        int[][] a ={{8,1,3},{4,0,2},{7,6,5}};
        Board x= new Board(a);
        StdOut.print(x.toString());
        StdOut.println(x.hamming());
        StdOut.println(x.manhattan());
        StdOut.print(x.twin().toString());
        for (Board i :x.neighbors()){
            StdOut.print(i.toString());
        }

    }

}
