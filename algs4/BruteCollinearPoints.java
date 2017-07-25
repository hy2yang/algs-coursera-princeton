import java.util.Arrays;

public class BruteCollinearPoints {
    
    private int n=0;
    private LineSegment[] a;
    
    public BruteCollinearPoints(Point[] points){
        if (points == null) throw new java.lang.IllegalArgumentException("null list of points");
        Arrays.sort(points);
        for(int i=0;i<points.length;i++){
            if (points[i] == null) throw new java.lang.IllegalArgumentException("null point at"+i);
            if (i>0 && points[i] == points[i-1]) throw new java.lang.IllegalArgumentException("repeated points");
        }
        
        double s1,s2,s3;
        
        for(int p=0;p<points.length-3;p++){
            for(int q=p+1;q<points.length-2;q++){
                s1=points[p].slopeTo(points[q]);
                for(int r=q+1;r<points.length-1;r++){
                    s2=points[p].slopeTo(points[r]);
                    if (s1==s2){
                        for(int s=r+1;s<points.length;s++){
                            s3=points[p].slopeTo(points[s]);
                            if (s1==s2 && s2==s3){
                                a[n++]=new LineSegment(points[p],points[s]);
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
        // TODO Auto-generated method stub

    }

}
