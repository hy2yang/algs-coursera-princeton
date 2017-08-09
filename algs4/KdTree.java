import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

public class KdTree {
    
    private int treesize;
    private Node root;
    private double xmin,xmax,ymin,ymax;
    
    public KdTree(){
        treesize=0;
        this.resetxy();
    }
        
    private static class Node{
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        
        public Node(Point2D i, double xmin, double ymin, double xmax, double ymax){
            this.p=i;
            this.rect = new RectHV(xmin, ymin, xmax, ymax);
        }
     }
    
    private void resetxy(){
        xmin=0;
        ymin=0;
        xmax=1;
        ymax=1;
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
    
    private Node find(Point2D i, Node x, boolean vertical){
        checkinput(i);
        if (x == null) return null;
        if (x.p.equals(i)) return x;
        int cmp=0;        
        if (vertical) cmp=Point2D.X_ORDER.compare(i, x.p);        
        else          cmp=Point2D.Y_ORDER.compare(i, x.p);
        
        if (cmp<0) return find(i, x.lb, !vertical);
        else return find(i, x.rt, !vertical); 
    }   
    
    public boolean contains(Point2D p){        
        return (find(p, root, true)!=null);
    }
    
    public void insert(Point2D p){
        if(this.contains(p)) return;
        root= insert(p, root, true);
        treesize++;
        this.resetxy();
    }
    
    private Node insert(Point2D i, Node x, boolean vertical){        
        if (x == null) return new Node(i, xmin, ymin, xmax, ymax);
        int cmp=0;
        if (vertical){
            cmp=Point2D.X_ORDER.compare(i, x.p);
            if (cmp < 0) {
                xmax=x.p.x();
                x.lb = insert(i, x.lb, !vertical); 
            }
            else {
                xmin=x.p.x();
                x.rt = insert(i, x.rt, !vertical);            
            }
        }
        else{
            cmp=Point2D.Y_ORDER.compare(i, x.p);
            if (cmp < 0) {
                ymax=x.p.y();
                x.lb = insert(i, x.lb, !vertical); 
            }
            else {
                ymin=x.p.y();
                x.rt = insert(i, x.rt, !vertical);            
            }
        }        
        return x;
    }
           
    public void draw(){
        this.draw(root,true);
    }
    
    private void draw(Node x, boolean vertical){        
        if (vertical){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(x.p.x(), x.p.y());
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(); 
            StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(x.p.x(), x.p.y());
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }
        
        if (x.lb!=null) draw(x.lb, !vertical);
        if (x.rt!=null) draw(x.rt, !vertical);        
    }
    
    public Iterable<Point2D> range(RectHV rect){
        Stack<Point2D> within = new Stack<Point2D>();
        inrange (rect, root, within);
        return within;        
    }
    
    private void inrange(RectHV qrect, Node x, Stack<Point2D> stack){
        if (x!=null && x.rect.intersects(qrect)){
            if (qrect.contains(x.p)) stack.push(x.p);
            inrange(qrect, x.lb, stack);
            inrange(qrect, x.rt, stack);
        }        
    }
    
    public Point2D nearest(Point2D p){
        return neighbor(root, p, root.p, true);
    }
    
    private Point2D neighbor(Node x, Point2D q, Point2D n, boolean vertical){        
        if (x!=null && x.rect.distanceSquaredTo(q)<n.distanceSquaredTo(q)){
            if (x.p.distanceSquaredTo(q)<=n.distanceSquaredTo(q)){
                n=x.p;
                return n;
            }
            int cmp=0;
            
            if (vertical) cmp=Point2D.X_ORDER.compare(q, x.p);        
            else          cmp=Point2D.Y_ORDER.compare(q, x.p);
            
            if (cmp>0){
                n=neighbor(x.rt, q, n, !vertical);
                n=neighbor(x.lb, q, n, !vertical);
            }
            else if (cmp<0){
                n=neighbor(x.lb, q, n, !vertical);
                n=neighbor(x.rt, q, n, !vertical);
            }
        }
        return n;
    }
    
    public static void main(String[] args) {
    }

} 


