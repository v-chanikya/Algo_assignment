/* *****************************************************************************
 *  Name:              Chanikya
 *  Coursera User ID:  123456
 *  Last modified:     14/12/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final double[] trialsFrac;
    private final int noTrials;
    private double meanVal = 0;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException("Ardguments n, trials should be greater than 0");
        }
        noTrials = trials;
        trialsFrac = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            trialsFrac[i] = (double) perc.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        meanVal = StdStats.mean(trialsFrac);
        return meanVal;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialsFrac);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        if (meanVal == 0) {
            mean();
        }
        return meanVal - CONFIDENCE_95 / Math.sqrt(noTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        if (meanVal == 0) {
            mean();
        }
        return meanVal + CONFIDENCE_95 / Math.sqrt(noTrials);
    }

    // used for test client
    public static void main(String[] args) {
        // This method is not implemented
    }
}
