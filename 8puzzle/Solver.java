/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.Iterator;

public class Solver {
    private static final HammingCompareBoards HAMMING_COMPARATOR = new HammingCompareBoards();
    private static final ManhattanCompareBoards MANHATTAN_COMPARATOR = new ManhattanCompareBoards();
    private final boolean solvable;
    private Board[] solutionBoards;
    private int moves = 0;

    private static class HammingCompareBoards implements Comparator<Board> {
        public int compare(Board o1, Board o2) {
            return o1.hamming() - o2.hamming();
        }
    }

    private static class ManhattanCompareBoards implements Comparator<Board> {
        public int compare(Board o1, Board o2) {
            return o1.manhattan() - o2.manhattan();
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Initial board is illegl");
        }
        solutionBoards = new Board[2];
        Board temp = initial;
        solutionBoards[moves++] = temp;
        Board ttemp = initial.twin();
        Board[] tsolutionsBoard = new Board[2];
        tsolutionsBoard[0] = ttemp;
        while (!temp.isGoal() && !ttemp.isGoal() && moves < 10) {
            // Actual solution code
            MinPQ<Board> pq = new MinPQ<>(HAMMING_COMPARATOR);
            for (Board iterBoard : temp.neighbors()) {
                if (moves > 1 && !iterBoard.equals(solutionBoards[moves - 2])) {
                    pq.insert(iterBoard);
                }
                else if (moves == 1) {
                    pq.insert(iterBoard);
                }
            }
            if (moves == solutionBoards.length) {
                Board[] tempsolutions = new Board[moves * 2];
                for (int i = 0; i < moves; i++) {
                    tempsolutions[i] = solutionBoards[i];
                }
                solutionBoards = tempsolutions;
            }
            solutionBoards[moves] = pq.delMin();
            temp = solutionBoards[moves];

            // Parallel operations to check solveabality
            MinPQ<Board> tpq = new MinPQ<>(HAMMING_COMPARATOR);
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
        return moves;
    }

    public Iterable<Board> solution() {
        class MySolverIterable implements Iterable<Board> {
            public Iterator<Board> iterator() {
                return new MySolverIter();
            }

            class MySolverIter implements Iterator<Board> {
                private int iterelements = 0;

                public boolean hasNext() {
                    return (iterelements != moves);
                }

                public Board next() {
                    return solutionBoards[iterelements++];
                }

                public void remove() {
                    throw new UnsupportedOperationException("This method is not supported");
                }
            }
        }
        return new MySolverIterable();
    }

    public static void main(String[] args) {

    }
}
