/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

public class Solver {
    private static final CompareBoards COMPARATOR = new CompareBoards();
    private final boolean solvable;
    private final Stack<Board> solutionBoards;
    private final int fmoves;

    private static class CompareBoards implements Comparator<BoardFN> {
        public int compare(BoardFN o1, BoardFN o2) {
            return (o1.hn + o1.gn) - (o2.hn + o2.gn);
        }
    }

    private class BoardFN {
        private final BoardFN prev;
        private final Board board;
        private final int gn;
        private final int hn;

        public BoardFN(BoardFN prev, Board board, int gn) {
            this.prev = prev;
            this.board = board;
            this.gn = gn;
            this.hn = board.hamming();
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Initial board is illegl");
        }
        int moves = 1;

        // Variables for actual initial board
        BoardFN temp = new BoardFN(null, initial, 0);
        Board[] prev2prev = { initial, null };
        MinPQ<BoardFN> pq = new MinPQ<>(COMPARATOR);
        pq.insert(temp);

        // parallel instance vars init
        BoardFN ttemp = new BoardFN(null, initial.twin(), 0);
        Board[] tsolutionsBoard = { ttemp.board, null };
        MinPQ<BoardFN> tpq = new MinPQ<>(COMPARATOR);
        tpq.insert(ttemp);
        while (!temp.board.isGoal() && !ttemp.board.isGoal()) {
            // while (!temp.board.isGoal()) {
            // Actual solution code
            temp = pq.delMin();

            for (Board iterBoard : temp.board.neighbors()) {
                if ((moves == 1) || (moves > 1 && !iterBoard.equals(prev2prev[0]))) {
                    // The below code checks if the entry board is already present but
                    // it takes constant time which is worst for this problem
                    // Iterator<BoardFN> iter = pq.iterator();
                    // boolean hasElement = false;
                    // while (iter.hasNext()) {
                    //     if (iter.next().board.equals(iterBoard)) {
                    //         hasElement = true;
                    //     }
                    // }
                    // if (hasElement)
                    //     continue;
                    BoardFN boardtemp = new BoardFN(temp, iterBoard, temp.gn + 1);
                    pq.insert(boardtemp);
                }
            }
            if (moves > 1)
                prev2prev[0] = prev2prev[1];
            prev2prev[1] = temp.board;

            // Parallel operations to check solveabality
            ttemp = tpq.delMin();
            for (Board titerBoard : ttemp.board.neighbors()) {
                if ((moves == 1) || (moves > 1 && !titerBoard.equals(tsolutionsBoard[0]))) {
                    BoardFN boardtemp = new BoardFN(ttemp, titerBoard, ttemp.gn + 1);
                    tpq.insert(boardtemp);
                }
            }
            if (moves > 1)
                tsolutionsBoard[0] = tsolutionsBoard[1];
            tsolutionsBoard[1] = ttemp.board;

            moves++;
        }

        if (temp.board.isGoal()) {
            fmoves = temp.gn;
            solutionBoards = new Stack<>();
            while (temp.prev != null) {
                solutionBoards.push(temp.board);
                temp = temp.prev;
            }
            solutionBoards.push(temp.board);
            solvable = true;
        }
        else {
            fmoves = -1;
            solutionBoards = null;
            solvable = false;
        }

        System.out.println("total no of iterations = " + moves);

    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return fmoves;
    }

    public Iterable<Board> solution() {
        return solutionBoards;
    }

    public static void main(String[] args) {
        // Intentionally left empty
    }
}
