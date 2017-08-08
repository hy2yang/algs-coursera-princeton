import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
    
    private SET<Point2D> points;
    
    public PointSET(){
        points = new SET<Point2D>();
    }
    
    public boolean isEmpty(){
        return points.isEmpty();
    }
    
    public int size(){
        return points.size();
    }
    
    public void insert(Point2D p){
        checkinput(p);        
        if (!points.contains(p)) points.add(p);
    }
    
    public boolean contains(Point2D p){
        checkinput(p);
        return points.contains(p);
    }
    
    public void draw(){
        for (Point2D p:points){
            p.draw();
        }
    }
    
    private void checkinput(Point2D p){
        if (p==null) throw new java.lang.IllegalArgumentException("null point");
    }
    
    public Iterable<Point2D> range(RectHV rect){
        ArrayList<Point2D> inrange =new ArrayList<>();
        for (Point2D p:points){
            if (p.x() >= rect.xmin() && p.x() <= rect.xmax()){
                if (p.y() >= rect.ymin() && p.y() <= rect.ymax()) inrange.add(p);
            }
        }
        return inrange;
    }
    
    public Point2D nearest(Point2D p){
        if (points.isEmpty()) return null;
        this.contains(p);
        Point2D n= p;
        for (Point2D i:points){
            if (i.equals(p)) continue;
            if (n.equals(p)) n=i; 
            if (i.distanceSquaredTo(p)<n.distanceSquaredTo(p)) n=i;
        }
        return n;
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
