/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;
    private int noOfLineSegments = 0;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        lineSegments = new LineSegment[2];
        Point[] lowPoints = new Point[2];
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
                        double im = points[i].slopeTo(points[m]);
                        if (ij == Double.NEGATIVE_INFINITY || ik == Double.NEGATIVE_INFINITY
                                || im == Double.NEGATIVE_INFINITY) {
                            throw new IllegalArgumentException("Points are equal");
                        }
                        if (ij == ik && ij == im) {
                            // System.out.println(i + " " + j + " " + k + " " + m);
                            // System.out.println(ij + " " + ik + " " + im);
                            // System.out.println("Creating line segment");
                            if (noOfLineSegments == lineSegments.length) {
                                LineSegment[] lineSegmentsTemp = new LineSegment[noOfLineSegments * 2];
                                Point[] lowPointsTemp = new Point[noOfLineSegments * 2];
                                for (int n = 0; n < noOfLineSegments; n++) {
                                    lineSegmentsTemp[n] = lineSegments[n];
                                    lowPointsTemp[n] = lowPoints[n];
                                }
                                lineSegments = lineSegmentsTemp;
                                lowPoints = lowPointsTemp;
                            }

                            Point highPoint = points[i];
                            Point lowPoint = points[i];

                            if (highPoint.compareTo(points[j]) > 0) {
                                highPoint = points[j];
                            }
                            else if (lowPoint.compareTo(points[j]) < 0) {
                                lowPoint = points[j];
                            }

                            if (highPoint.compareTo(points[k]) > 0) {
                                highPoint = points[k];
                            }
                            else if (lowPoint.compareTo(points[k]) < 0) {
                                lowPoint = points[k];
                            }

                            if (highPoint.compareTo(points[m]) > 0) {
                                highPoint = points[m];
                            }
                            else if (lowPoint.compareTo(points[m]) < 0) {
                                lowPoint = points[m];
                            }

                            if (noOfLineSegments > 0) {
                                boolean newLineSegment = true;
                                for (int n = 0; n < noOfLineSegments; n++) {
                                    if (lowPoint.slopeTo(lowPoints[n]) == Double.NEGATIVE_INFINITY) {
                                        newLineSegment = false;
                                    }
                                }
                                if (newLineSegment) {
                                    lowPoints[noOfLineSegments] = lowPoint;
                                    lineSegments[noOfLineSegments] = new LineSegment(lowPoint, highPoint);
                                    noOfLineSegments++;
                                }
                            }
                            else {
                                lowPoints[noOfLineSegments] = lowPoint;
                                lineSegments[noOfLineSegments] = new LineSegment(lowPoint, highPoint);
                                noOfLineSegments++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(noOfLineSegments);
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
        // Intentionally left empty
    }
}
