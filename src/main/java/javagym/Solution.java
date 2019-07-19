package javagym;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.tuple.Pair;

import static javagym.Input.Point;

public class Solution {

    @Nonnull
    public Pair<Point, Point> solve(@Nonnull Point[] points) {

        // Find the 2 outmost points in the x, y and z direction.
        Point p1 = null;
        Point p2 = null;
        Point p3 = null;
        Point p4 = null;
        Point p5 = null;
        Point p6 = null;

        double lowestX = 0;
        double highestX = 0;
        double lowestY = 0;
        double highestY = 0;
        double lowestZ = 0;
        double highestZ = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[i].x < lowestX) {
                lowestX = points[i].x;
                p1 = points[i];
            }
            if (points[i].x > highestX) {
                highestX = points[i].x;
                p2 = points[i];
            }
            if (points[i].y < lowestY) {
                lowestY = points[i].y;
                p3 = points[i];
            }
            if (points[i].y > highestY) {
                highestY = points[i].y;
                p4 = points[i];
            }
            if (points[i].z < lowestZ) {
                lowestZ = points[i].z;
                p5 = points[i];
            }
            if (points[i].z > highestZ) {
                highestZ = points[i].z;
                p6 = points[i];
            }
        }

        // no idea. It's what it says here. https://airccj.org/CSCP/vol5/csit54302.pdf
        double[] sum = new double[points.length];
        int[] index = new int[sum.length];
        for (int i = 0; i < points.length; i++ ) {
            sum[i] = 11 * points[i].dist(p1) +
                    101 * points[i].dist(p2) +
                    547 * points[i].dist(p3) +
                    1009 * points[i].dist(p4) +
                    5501 * points[i].dist(p5) +
                    10007 * points[i].dist(p6);
            index[i] = i;
        }

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(sum, index, 0, sum.length-1);

        double closestSoFar = 9999999999.0;
        int point1 = -1;
        int point2 = -1;
        for (int i = 0; i < sum.length; i++ ) {
            for (int j = i+1; j < i+100; j++ ) {
                if (j < sum.length) {
                    double dist = points[index[i]].dist(points[index[j]]);
                    if (dist < closestSoFar) {
                        closestSoFar = dist;
                        point1 = index[i];
                        point2 = index[j];
                    }
                }
            }
        }

        return Pair.of(points[point1], points[point2]);
    }
}
