import java.util.Arrays;

public class FastCollinearPoints {
    private int n=0;
    private LineSegment[] a;
        
    public FastCollinearPoints(Point[] points){
        if (points == null) throw new java.lang.IllegalArgumentException("null list of points");
        Point[] clone = points.clone();
        Arrays.sort(clone);
        for(int i=0;i<clone.length;i++){
            if (clone[i] == null) throw new java.lang.IllegalArgumentException("null point at"+i);
            if (i>0 && clone[i] == clone[i-1]) throw new java.lang.IllegalArgumentException("repeated points");
        }
        
        for(int p=0;p<clone.length-3;p++){
            //double[] slopes=new double[points.length-1-p];
            //int i=0;
            for(int q=p+1;q<clone.length;q++){
                //slopes[i++]=points[p].slopeTo(points[q]);
                Arrays.sort(clone,clone[p].SLOPE_ORDER);
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
