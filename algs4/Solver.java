import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {    
    
    private boolean solvable;
    private Node result;
    
    private class Node implements Comparable<Node>{
        Board board;
        Node previous;
        int m,moves,p;
        
        public int compareTo(Node that){
            if (this.p>that.p) return +1;
            if (this.p<that.p) return -1;
            return 0;
        }
        
        private Node(Board a, int move, Node p){            
            this.previous=p;
            this.board=a;
            this.m=a.manhattan();
            this.moves=move;
            this.p=this.m+this.moves;
        }
    }   
    
    public Solver(Board initial){
        
        if (initial==null) throw new java.lang.IllegalArgumentException();        
        MinPQ<Node> pq=new MinPQ<Node>();
        MinPQ<Node> tpq=new MinPQ<Node>();
        Node ini=new Node(initial,0,null);
        Node tini=new Node(initial.twin(),0,null);
        
        
        Node least=ini;
        Node tleast=tini;
        while (!least.board.isGoal() && !tleast.board.isGoal()){ 
            nextmove(pq,least,least.moves+1);
            nextmove(tpq,tleast,tleast.moves+1);
            least=pq.delMin();
            tleast=tpq.delMin();
        }
        
        
        if(least.board.isGoal()){
            solvable=true;
            result=least;
        }
        
        if(tleast.board.isGoal()){
            solvable=false;
            result=tleast;
        }
    }
    
    private void nextmove(MinPQ<Node> pq, Node min ,int move){        
        for (Board n:min.board.neighbors()){
            if (min.previous==null){
                pq.insert(new Node(n,move,min));
                continue;
            }
            if (n.equals(min.previous.board)) continue;
            pq.insert(new Node(n,move,min));
        }        
    }
    
    public boolean isSolvable(){
        return this.solvable;
    }
    
    public int moves(){
        if (!this.isSolvable()) return -1;        
        return result.moves;
    }
    
    public Iterable<Board> solution(){
        if (!this.isSolvable()) return null;
        Stack<Board> steps = new Stack<Board>();
        Node step=result;
        while(step.previous!=null){
            steps.push(step.board);
            step=step.previous;
        }
        steps.push(step.board);
        return steps;
        
    }
    
    public static void main(String[] args) {        
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
