/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> lineSegments = new ArrayList<>();
    private int noOfLineSegments = 0;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int m = k + 1; m < points.length; m++) {
                        if (points[i] == null || points[j] == null || points[k] == null
                                || points[m] == null) {
                            throw new IllegalArgumentException("One or more points are null");
                        }
                        double ij = points[i].slopeTo(points[j]);
                        double ik = points[i].slopeTo(points[k]);
                        double il = points[i].slopeTo(points[m]);
                        if (ij == Double.NEGATIVE_INFINITY || ik == Double.NEGATIVE_INFINITY
                                || il == Double.NEGATIVE_INFINITY) {
                            throw new IllegalArgumentException("Points are equal");
                        }
                        if (ij == ik && ij == il) {
                            // System.out.println(i + " " + j + " " + k + " " + m);
                            // System.out.println(ij + " " + ik + " " + il);
                            // System.out.println("Creating line segment");
                            lineSegments.add(new LineSegment(points[i], points[m]));
                            noOfLineSegments++;
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return noOfLineSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[noOfLineSegments];
        return lineSegments.toArray(temp);
    }

    public static void main(String[] args) {
        // read the n points from a file testing code
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
