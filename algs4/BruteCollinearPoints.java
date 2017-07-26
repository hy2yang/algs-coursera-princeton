import java.util.Arrays;

public class BruteCollinearPoints {
    
    private int n=0;
    private LineSegment[] a;
    
    public BruteCollinearPoints(Point[] points){
        if (points == null) throw new java.lang.IllegalArgumentException("null list of points");
        Point[] clone = points.clone();
        Arrays.sort(clone);
        for(int i=0;i<clone.length;i++){
            if (clone[i] == null) throw new java.lang.IllegalArgumentException("null point at"+i);
            if (i>0 && clone[i] == clone[i-1]) throw new java.lang.IllegalArgumentException("repeated points");
        }
        
        double s1,s2,s3;
        
        for(int p=0;p<clone.length-3;p++){
            for(int q=p+1;q<clone.length-2;q++){
                s1=clone[p].slopeTo(clone[q]);
                for(int r=q+1;r<clone.length-1;r++){
                    s2=clone[p].slopeTo(clone[r]);
                    if (s1==s2){
                        for(int s=r+1;s<clone.length;s++){
                            s3=clone[p].slopeTo(clone[s]);
                            if (s1==s2 && s2==s3){
                                a[n++]=new LineSegment(clone[p],clone[s]);
                            }
                        }
                    }
                }
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
