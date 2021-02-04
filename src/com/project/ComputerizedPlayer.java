package com.project;

public class ComputerizedPlayer {

    // Reference to the play board
    public final PlayBoard mPlayBoard;

    private int maxDepth;

    public ComputerizedPlayer(PlayBoard mPlayBoard){
        this.mPlayBoard = mPlayBoard;
    }


    public void setDifficulty(int maxDepth){
        this.maxDepth = maxDepth;
    }

    /**
     * run computerized move
     * @return column to put the disk in the playboard
     */
    public int getColumn() {
        return chooseMove(Player.COMPUTERIZED_PLAYER, Player.PLAYER1, -10000, 10000, maxDepth).getColumn();
    }

    /**
     * Recursive minmax algorithm to get best move for obstructing the player
     *
     * @param player   player taking their move at this level of the tree
     * @param opponent player NOT taking their move at this level of the tree
     * @param alpha    the best score this AIPlayer object can achieve
     * @param beta     the best score the other player can achieve
     * @param depth    the current depth in the tree, 0 is a leaf node
     * @return best move
     */
    private Move chooseMove(int player, int opponent,
                            int alpha, int beta, int depth) {
        Move best = new Move(-1, player == Player.COMPUTERIZED_PLAYER ? alpha : beta);
        // go from left to right until you find a non-full column
        for (int i = 0; i < mPlayBoard.mPlayBoardCols; i++) {
            if (mPlayBoard.getColumnHeight(i) > 0) {
                // add a counter to that column, then check for win-condition
                mPlayBoard.placeDisk(i, player);
                // score this move and all its children
                int score = 0;
                if (mPlayBoard.checkAllMatches(i, mPlayBoard.getColumnHeight(i))) {
                    // this move is a winning move for the player
                    score = player == Player.COMPUTERIZED_PLAYER ? 1 : -1;
                } else if (depth != 1) {
                    // this move wasn't a win or a draw, so go to the next move
                    score = chooseMove(opponent, player, alpha, beta,
                            depth - 1).getScore();
                }
                mPlayBoard.undoMove(i);
                // if this move beats this player's best move so far, record it
                if (player == Player.COMPUTERIZED_PLAYER && score > best.getScore()) {
                    best = new Move(i, score);
                    alpha = score;
                } else if (player == Player.PLAYER1 && score < best.getScore()) {
                    best = new Move(i, score);
                    beta = score;
                }
                // don't continue with this branch, we've already found better
                if (alpha >= beta) {
                    return best;
                }
            }
        }

        return best;
    }


}
