package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
class Solution {
    public void gameOfLife(int[][] board) {
        int willDie = 10;
        int willLive = 20;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                int liveNeighborCells = 0;
                for (int cellR = r - 1; cellR <= r + 1; cellR++) {
                    if (cellR > -1 && cellR < board.length) {
                        for (int cellC = c - 1; cellC <= c + 1; cellC++) {
                            if (cellC > -1 && cellC < board[cellR].length && !(r == cellR && c == cellC)) {
                                if (board[cellR][cellC] >= willLive) {
                                    liveNeighborCells += board[cellR][cellC] - willLive;
                                } else if (board[cellR][cellC] >= willDie) {
                                    liveNeighborCells += board[cellR][cellC] - willDie;
                                } else {
                                    liveNeighborCells += board[cellR][cellC];
                                }
                            }
                        }
                    }
                }

                if (board[r][c] == 1 && liveNeighborCells < 2 || liveNeighborCells > 3) {
                    board[r][c] += willDie;
                } else if (board[r][c] == 0 && liveNeighborCells == 3) {
                    board[r][c] += willLive;
                }
            }
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] >= willLive) {
                    board[r][c] = 1;
                } else if (board[r][c] >= willDie) {
                    board[r][c] = 0;
                }
            }
        }
    }
}
