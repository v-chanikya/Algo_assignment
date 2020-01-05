/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.Iterator;

public class Solver {
    private static final CompareBoards HAMMING_COMPARATOR = new CompareBoards();
    private final boolean solvable;
    private Board[] solutionBoards;
    private int moves = 0;

    private static class CompareBoards implements Comparator<Board> {
        public int compare(Board o1, Board o2) {
            return o1.hamming() - o2.hamming();
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Initial board is illegl");
        }
        solutionBoards = new Board[2];
        Board temp = initial;
        solutionBoards[moves++] = temp;
        while (!temp.isGoal()) {
            MinPQ<Board> pq = new MinPQ<>(HAMMING_COMPARATOR);
            for (Board iterBoard : temp.neighbors()) {
                pq.insert(iterBoard);
            }
            if (moves == solutionBoards.length) {
                Board[] tempsolutions = new Board[moves * 2];
                for (int i = 0; i < moves; i++) {
                    tempsolutions[i] = solutionBoards[i];
                }
                solutionBoards = tempsolutions;
            }
            solutionBoards[moves++] = pq.delMin();
            temp = solutionBoards[moves - 1];
        }
        solvable = true;
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
