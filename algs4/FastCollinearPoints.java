import java.util.Arrays;

public class FastCollinearPoints {
    private int n=0;
    private LineSegment[] a;
        
    public FastCollinearPoints(Point[] points){
        if (points == null) throw new java.lang.IllegalArgumentException("null list of points");
        //Point[] clone = points.clone();
        Arrays.sort(points);
        for(int i=0;i<points.length;i++){
            if (points[i] == null) throw new java.lang.IllegalArgumentException("null point at"+i);
            if (i>0 && points[i] == points[i-1]) throw new java.lang.IllegalArgumentException("repeated points");
        }
        
        for(int p=0;p<points.length-3;p++){
            //double[] slopes=new double[points.length-1-p];
            //int i=0;
            for(int q=p+1;q<points.length;q++){
                //slopes[i++]=points[p].slopeTo(points[q]);
                Arrays.sort(points,points[p].SLOPE_ORDER);
            }
        }
    }
    
    public int numberOfSegments(){
        return n;
    }
    
    public LineSegment[] segments(){
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
