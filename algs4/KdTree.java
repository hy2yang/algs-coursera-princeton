import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    
    private int treesize;
    private Node root;
    
    public KdTree(){
        treesize=0;        
    }
        
    private static class Node{
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        private int level;
        
        public Node(Point2D i, int level){
            this.p=i;
            this.level=level;
        }
     }  
      
    public boolean isEmpty(){
        return this.treesize == 0;
    }
    
    public int size(){
        return this.treesize;
    }
    
    private void checkinput(Point2D p){
        if (p==null) throw new java.lang.IllegalArgumentException("null point");
    }
    
    private Node find(Node x, Point2D i){
        checkinput(i);
        if (x == null) return null;        
        int cmp=0;        
        switch (x.level%2){
        case 0:cmp=Point2D.Y_ORDER.compare(i, root.p);break;
        case 1:cmp=Point2D.X_ORDER.compare(i, root.p);break;
        }
        if (cmp>0) return find(x.rt,i);
        else if (cmp<0) return find(x.lb,i);       
        else return x;
    }   
    
    public boolean contains(Point2D p){        
        return find(root,p)!=null;
    }
    
    public void insert(Point2D p){
        if(this.contains(p)) return;
        root= insert(root,p,0);
        treesize++;
    }
    
    private Node insert(Node x, Point2D i, int level){        
        if (x == null) return new Node(i, level);
        int cmp=0;
        switch (x.level%2){
        case 0:cmp=Point2D.Y_ORDER.compare(i, root.p);break;
        case 1:cmp=Point2D.X_ORDER.compare(i, root.p);break;
        }
        if      (cmp < 0) x.lb = insert(x.lb, i, ++level);
        else if (cmp > 0) x.rt = insert(x.rt, i, ++level);
        return x;
    }
    
    
    
    public static void main(String[] args) {

    }

}
