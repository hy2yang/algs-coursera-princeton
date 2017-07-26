import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private int n=0;
    private LineSegment[] a;
    private ArrayList<LineSegment> foundsegments = new ArrayList<LineSegment>();
        
    public FastCollinearPoints(Point[] points){
        if (points == null) throw new java.lang.IllegalArgumentException("null list of points");        
        for(int i=0;i<points.length;i++){
            if (points[i] == null) throw new java.lang.IllegalArgumentException("null point at"+i);
            for(int j=i+1;j<points.length;j++){
                if ( points[i].compareTo(points[j])==0) throw new java.lang.IllegalArgumentException("repeated points");  
            }            
        }
        Point[] clone = points.clone();
        Arrays.sort(clone);
        
        for(int p=0;p<clone.length-3;p++){            
            Arrays.sort(clone,points[p].slopeOrder());            
            for(int q=1;q<clone.length-2;){  
                int i = 1;
                while ( q+i<clone.length && clone[p].slopeTo(clone[q]) == clone[p].slopeTo(clone[q+i]) ){
                    i++;
                }
                if (i>2) {
                    foundsegments.add(new LineSegment(clone[p],clone[q+i-1])); 
                    n++;
                }
                q+=i;
            }
        }        
    }
    
    public int numberOfSegments(){
        return n;
    }
    
    public LineSegment[] segments(){
        a = new LineSegment[n];
        for (int i=0;i<n;i++){
            a[i]=foundsegments.get(i);
        }  
        return a;
    }
    
    public static void main(String[] args) {
     // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
