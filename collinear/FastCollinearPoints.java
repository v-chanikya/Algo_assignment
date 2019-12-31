/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private static final Comparator<MySlopes> BYSLOPE = new BySlope();

    private static class BySlope implements Comparator<MySlopes> {
        public int compare(MySlopes o1, MySlopes o2) {
            return Double.compare(o1.slope, o2.slope);
        }
    }

    private class MySlopes {
        private double slope;
        private int pointIndex;
    }

    private class MyLowPoints {
        private Point point;
        private double slope;
    }

    private int noOfLineSegments = 0;
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException("Input invalid");
        }

        lineSegments = new LineSegment[2];
        MyLowPoints[] lowPoints = new MyLowPoints[2];
        for (int i = 0; i < points.length - 1; i++) {

            if (points[i] == null) {
                throw new IllegalArgumentException("Invalid point");
            }

            MySlopes[] slopes = new MySlopes[points.length - i - 1];

            // Find slope of one point with the rest of the points
            for (int j = i + 1; j < points.length; j++) {
                slopes[j - i - 1] = new MySlopes();
                slopes[j - i - 1].slope = points[i].slopeTo(points[j]);
                slopes[j - i - 1].pointIndex = j;
            }

            // System.out.println("index: " + i + points[i]);

            // Sort and find if it has a line segment
            Arrays.sort(slopes, BYSLOPE);
            if (slopes[0].slope == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException("Duplicate points");
            }

            boolean foundSegment = false;
            boolean domath = false;
            int startSegmentPoint = 0;
            int noOfPointsOnSegment = 0;
            for (int k = 0; k < slopes.length - 2; k++) {
                if (slopes[k].slope == slopes[k + 2].slope) {
                    if (!foundSegment) {
                        foundSegment = true;
                        startSegmentPoint = k;
                        noOfPointsOnSegment = 4;
                        // System.out.println("s: " + slopes[k].slope + " " + slopes[k + 1].slope + " " + slopes[k + 2].slope);
                    }
                    else {
                        // System.out.println("s: " + slopes[k + 2].slope);
                        noOfPointsOnSegment++;
                    }
                    if (k == slopes.length - 3) {
                        domath = true;
                    }
                }
                else if (foundSegment) {
                    domath = true;
                    foundSegment = false;
                }
                if (domath) {
                    // System.out.println("new segment found");
                    // Resize dynamic array
                    if (noOfLineSegments == lineSegments.length) {
                        LineSegment[] lineSegmentsTemp = new LineSegment[noOfLineSegments * 2];
                        MyLowPoints[] lowPointsTemp = new MyLowPoints[noOfLineSegments * 2];
                        for (int n = 0; n < noOfLineSegments; n++) {
                            lineSegmentsTemp[n] = lineSegments[n];
                            lowPointsTemp[n] = lowPoints[n];
                        }
                        lineSegments = lineSegmentsTemp;
                        lowPoints = lowPointsTemp;
                    }

                    // Find the lowest and higest point in the line segment
                    Point lowPoint = points[i];
                    Point highPoint = points[i];

                    // System.out.println(startSegmentPoint + " " + k);
                    for (int p = 0; p < noOfPointsOnSegment - 1; p++) {
                        if (lowPoint.compareTo(points[slopes[startSegmentPoint + p].pointIndex]) < 0) {
                            lowPoint = points[slopes[startSegmentPoint + p].pointIndex];
                        }
                        else if (highPoint.compareTo(points[slopes[startSegmentPoint + p].pointIndex]) > 0) {
                            highPoint = points[slopes[startSegmentPoint + p].pointIndex];
                        }
                    }

                    // Add line segment if it has not been added previously
                    if (noOfLineSegments > 0) {
                        boolean newLineSegment = true;
                        for (int n = 0; n < noOfLineSegments; n++) {
                            if ((lowPoint.slopeTo(lowPoints[n].point) == Double.NEGATIVE_INFINITY) && (slopes[k].slope
                                    == lowPoints[n].slope)) {
                                // System.out.println("new matches old " + slopes[k].slope + lowPoints[n].slope);
                                newLineSegment = false;
                            }
                        }
                        if (newLineSegment) {
                            // System.out.println(
                            //         "Adding line: " + startSegmentPoint + " " + noOfPointsOnSegment + " "
                            //                 + slopes[startSegmentPoint].pointIndex);
                            lowPoints[noOfLineSegments] = new MyLowPoints();
                            lowPoints[noOfLineSegments].point = lowPoint;
                            lowPoints[noOfLineSegments].slope = slopes[k].slope;
                            lineSegments[noOfLineSegments] = new LineSegment(lowPoint, highPoint);
                            noOfLineSegments++;
                        }
                    }
                    else {
                        // System.out.println(
                        //         "Adding line: " + startSegmentPoint + " " + noOfPointsOnSegment + " "
                        //                 + slopes[startSegmentPoint].pointIndex);
                        lowPoints[noOfLineSegments] = new MyLowPoints();
                        lowPoints[noOfLineSegments].point = lowPoint;
                        lowPoints[noOfLineSegments].slope = slopes[k].slope;
                        lineSegments[noOfLineSegments] = new LineSegment(lowPoint, highPoint);
                        noOfLineSegments++;
                    }
                    domath = false;
                }
            }
        }
    }

    public int numberOfSegments() {
        return noOfLineSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[noOfLineSegments];
        for (int i = 0; i < noOfLineSegments; i++) {
            temp[i] = lineSegments[i];
        }
        return temp;
    }

    public static void main(String[] args) {
        // Intentionally left blank
    }
}
