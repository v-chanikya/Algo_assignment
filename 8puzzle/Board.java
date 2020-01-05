/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class Board {
    private final int[][] tiles;
    private final int size;
    private final int hammingDist;
    private final int manhattanDist;
    private int emptySlot;

    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException("empty array not accepted");
        }
        size = tiles.length;
        this.tiles = new int[size][size];
        int manhattandist = 0;
        int hammingdist = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
                if (tiles[i][j] != 0) {
                    if (tiles[i][j] != i * size + j + 1) {
                        hammingdist++;
                    }
                    int y = (tiles[i][j] - 1) / size;
                    int x = (tiles[i][j] - 1) % size;
                    manhattandist += Math.abs(i - y) + Math.abs(j - x);
                }
                else {
                    emptySlot = (i * size) + j;
                }
            }
        }
        this.hammingDist = hammingdist;
        this.manhattanDist = manhattandist;
    }

    public String toString() {
        StringBuilder printString = new StringBuilder();
        printString.append(size);
        for (int i = 0; i < size; i++) {
            printString.append("\n");
            for (int j = 0; j < size; j++) {
                printString.append(tiles[i][j]);
                printString.append(" ");
            }
        }
        return printString.toString();
    }

    public int dimension() {
        return size;
    }

    public int hamming() {
        return hammingDist;
    }

    public int manhattan() {
        return manhattanDist;
    }

    public boolean isGoal() {
        return (hammingDist == 0);
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        if (y == this) {
            return true;
        }
        Board that = (Board) y;
        if (this.size == that.size) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (that.tiles[i][j] != this.tiles[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }


    public Iterable<Board> neighbors() {

        class MyBoardIterable implements Iterable<Board> {

            class MyBoardIter implements Iterator<Board> {
                private int remainingNeigh = 0;
                private Board[] boards;

                public MyBoardIter() {
                    int x = emptySlot % size;
                    int y = emptySlot / size;
                    boards = new Board[4];
                    int[][] tempTiles = tiles;
                    if (x - 1 != -1) {
                        tempTiles[y][x] = tempTiles[y][x - 1];
                        tempTiles[y][x - 1] = 0;
                        boards[remainingNeigh++] = new Board(tempTiles);
                        tempTiles[y][x - 1] = tempTiles[y][x];
                        tempTiles[y][x] = 0;
                    }
                    if (y - 1 != -1) {
                        tempTiles[y][x] = tempTiles[y - 1][x];
                        tempTiles[y - 1][x] = 0;
                        boards[remainingNeigh++] = new Board(tempTiles);
                        tempTiles[y - 1][x] = tempTiles[y][x];
                        tempTiles[y][x] = 0;
                    }
                    if (x + 1 != size) {
                        tempTiles[y][x] = tempTiles[y][x + 1];
                        tempTiles[y][x + 1] = 0;
                        boards[remainingNeigh++] = new Board(tempTiles);
                        tempTiles[y][x + 1] = tempTiles[y][x];
                        tempTiles[y][x] = 0;
                    }
                    if (y + 1 != size) {
                        tempTiles[y][x] = tempTiles[y + 1][x];
                        tempTiles[y + 1][x] = 0;
                        boards[remainingNeigh++] = new Board(tempTiles);
                        tempTiles[y + 1][x] = tempTiles[y][x];
                        tempTiles[y][x] = 0;
                    }
                }

                public boolean hasNext() {
                    if (remainingNeigh == 0) {
                        return false;
                    }
                    return true;
                }

                public Board next() {
                    return boards[--remainingNeigh];
                }

                public void remove() {
                    throw new UnsupportedOperationException("This method is not supported");
                }
            }

            public Iterator<Board> iterator() {
                return new MyBoardIter();
            }
        }
        return new MyBoardIterable();
    }

    private void swap(int[][] arr, int x1, int y1, int x2, int y2) {
        int tmpval = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = tmpval;
    }
    // This is used to see if the board is solvable
    public Board twin() {
        int[][] tempTiles = tiles.clone();
        Board tmpBoard;
        if (emptySlot == 0) {
            swap(tempTiles, 0, 1, 1, 0);
            tmpBoard = new Board(tempTiles);
            swap(tempTiles, 1, 0, 0, 1);
        }
        else if (emptySlot == 1) {
            swap(tempTiles, 0, 0, 1, 0);
            tmpBoard = new Board(tempTiles);
            swap(tempTiles, 1, 0, 0, 0);
        }
        else {
            swap(tempTiles, 0, 0, 0, 1);
            tmpBoard = new Board(tempTiles);
            swap(tempTiles, 0, 1, 0, 0);
        }
        return tmpBoard;
    }


    public static void main(String[] args) {

    }
}
