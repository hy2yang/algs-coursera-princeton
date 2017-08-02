import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    
    MinPQ<Node> pq=new MinPQ<Node>();
    MinPQ<Node> tpq=new MinPQ<Node>();
    
    private class Node implements Comparable<Node>{
        Board board;
        Node previous;
        int m,moves,p;
        
        public int compareTo(Node that){
            if (this.p>that.p) return +1;
            if (this.p<that.p) return -1;
            return 0;
        }
        
        private Node(Board a, int move){            
            this.previous=null;
            this.board=a;
            this.m=a.manhattan();
            this.moves=move;
            this.p=this.m+this.moves;
        }
    }
    
    
    
    public Solver(Board initial){
        Node ini=new Node(initial,0);
        Node tini=new Node(initial.twin(),0);
        pq.insert(ini);
        tpq.insert(tini);
        
        while (!pq.delMin().board.isGoal()){
            
        }
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
