package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdDraw;

import java.io.File;
import java.io.FileInputStream;

/******************************************************************************
 *  Compilation:  javac PlotFilter.java
 *  Execution:    java PlotFilter < input.txt
 *  Dependencies: StdDraw.java StdIn.java
 *
 *  % java PlotFilter < USA.txt
 *
 *  Datafiles:    https://introcs.cs.princeton.edu/java/15inout/USA.txt
 *
 ******************************************************************************/

public class PlotFilter {

    public static void main(String[] args) {

        try (FileInputStream is = new FileInputStream(new File("src\\main\\java\\net\\qiguang\\algorithms\\C1_Fundamentals\\S1_BasicProgrammingModel\\USA.txt"))) {
            System.setIn(is);

            // read in bounding box and rescale
            double x0 = StdIn.readDouble();
            double y0 = StdIn.readDouble();
            double x1 = StdIn.readDouble();
            double y1 = StdIn.readDouble();
            StdDraw.setXscale(x0, x1);
            StdDraw.setYscale(y0, y1);

            // for bigger points
            StdDraw.setPenRadius(0.005);

            // to speed up performance, defer displaying points
            StdDraw.enableDoubleBuffering();

            // plot points, one at a time
            while (!StdIn.isEmpty()) {
                double x = StdIn.readDouble();
                double y = StdIn.readDouble();
                StdDraw.point(x, y);
            }

            // display all of the points now
            StdDraw.show();
        } catch (Exception e) {
            StdOut.println(e);
        }


    }

    private static void run(String[] args) {

    }
}