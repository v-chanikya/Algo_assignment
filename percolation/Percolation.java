/* *****************************************************************************
 *  Name:              Chanikya
 *  Coursera User ID:  123456
 *  Last modified:     14/12/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private final WeightedQuickUnionUF wu;
    private boolean[][] openSites;
    private int noOpenSites = 0;
    private final int pn;

    // create n by n sites with initially blocaked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n should be greater than zero");
        }
        pn = n;
        openSites = new boolean[n][n];
        wu = new WeightedQuickUnionUF(n * n + 2);
    }

    // check input bounds
    private void checkBouns(int row, int col, String errmsg) {
        if (row < 1 || col < 1 || row > pn || col > pn) {
            throw new IllegalArgumentException(errmsg);
        }
    }

    // convert row col to union find index
    private int ufIndex(int row, int col) {
        return (row - 1) * pn + col - 1;
    }

    // opens the site
    public void open(int row, int col) {
        checkBouns(row, col, "Input row or col out of bound");
        if (!openSites[row - 1][col - 1]) {
            openSites[row - 1][col - 1] = true;
            noOpenSites++;
            // update weighted union find
            int ind = ufIndex(row, col);
            // System.out.println("Row:" + row + "Col:" + col);
            if (col != 1) {
                if (isOpen(row, col - 1)) wu.union(ind, ufIndex(row, col - 1));
            }
            if (row != 1) {
                if (isOpen(row - 1, col)) wu.union(ind, ufIndex(row - 1, col));
            }
            if (col != pn) {
                if (isOpen(row, col + 1)) wu.union(ind, ufIndex(row, col + 1));
            }
            if (row != pn) {
                if (isOpen(row + 1, col)) wu.union(ind, ufIndex(row + 1, col));
            }
            if (row == 1) {
                wu.union(ind, pn * pn);
            }
            if (row == pn) {
                wu.union(ind, pn * pn + 1);
            }
        }
    }

    // is the site open
    public boolean isOpen(int row, int col) {
        checkBouns(row, col, "Is Open row or col out of bound");
        return openSites[row - 1][col - 1];
    }

    // is the site full i.e is it connected to the top
    // The present implementation has a limitation with virtual sites
    // Implementing other ways would degrade the performance
    public boolean isFull(int row, int col) {
        checkBouns(row, col, "Is Full row or col out of bound");
        return wu.find(pn * pn) == wu.find(ufIndex(row, col));
    }

    // no of open sites
    public int numberOfOpenSites() {
        return noOpenSites;
    }

    // does the system percolates
    public boolean percolates() {
        return wu.find(pn * pn) == wu.find(pn * pn + 1);
    }

    // Used for test client optional
    public static void main(String[] args) {
        // This method is not implemented
    }
}
