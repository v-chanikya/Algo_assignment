/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

import java.util.Comparator;

public class Solver {
    // private static final HammingCompareBoards HAMMING_COMPARATOR = new HammingCompareBoards();
    private static final ManhattanCompareBoards MANHATTAN_COMPARATOR = new ManhattanCompareBoards();
    private final boolean solvable;
    private final Queue<Board> solutionBoards = new Queue<>();
    private int moves = 0;

    // private static class HammingCompareBoards implements Comparator<Board> {
    //     public int compare(Board o1, Board o2) {
    //         return o1.hamming() - o2.hamming();
    //     }
    // }

    private static class ManhattanCompareBoards implements Comparator<Board> {
        public int compare(Board o1, Board o2) {
            return o1.manhattan() - o2.manhattan();
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Initial board is illegl");
        }
        Board temp = initial;
        Board[] prev2prev = new Board[2];
        prev2prev[0] = initial;
        solutionBoards.enqueue(temp);
        moves++;

        Board ttemp = initial.twin();
        Board[] tsolutionsBoard = new Board[2];
        tsolutionsBoard[0] = ttemp;
        while (!temp.isGoal() && !ttemp.isGoal()) {
            // Actual solution code
            MinPQ<Board> pq = new MinPQ<>(MANHATTAN_COMPARATOR);
            for (Board iterBoard : temp.neighbors()) {
                if (moves > 1 && !iterBoard.equals(prev2prev[0])) {
                    pq.insert(iterBoard);
                }
                else if (moves == 1) {
                    pq.insert(iterBoard);
                }
            }

            temp = pq.delMin();
            solutionBoards.enqueue(temp);
            if (moves > 1)
                prev2prev[0] = prev2prev[1];
            prev2prev[1] = temp;

            // Parallel operations to check solveabality
            MinPQ<Board> tpq = new MinPQ<>(MANHATTAN_COMPARATOR);
            for (Board titerBoard : ttemp.neighbors()) {
                if (moves > 1 && !titerBoard.equals(tsolutionsBoard[0])) {
                    tpq.insert(titerBoard);
                }
                else if (moves == 1) {
                    tpq.insert(titerBoard);
                }
            }
            ttemp = tpq.delMin();
            if (moves > 1)
                tsolutionsBoard[0] = tsolutionsBoard[1];
            tsolutionsBoard[1] = ttemp;
            moves++;
        }
        if (ttemp.isGoal()) {
            solvable = false;
        }
        else {
            solvable = true;
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return moves - 1;
    }

    public Iterable<Board> solution() {
        return solutionBoards;
    }

    public static void main(String[] args) {
        // Intentionally left empty
    }
}
