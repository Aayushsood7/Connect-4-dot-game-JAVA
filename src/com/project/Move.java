package com.project;

public class Move {

    // variable to get the column
    private final int mCol;

    // variable to get the score of the move
    private final int mScore;

    public Move(int mCol, int mScore) {
        this.mCol = mCol;
        this.mScore = mScore;
    }

    /**
     *  function to get column of the move
     * @return
     */
    public int getColumn() {
        return mCol;
    }

    /**
     *  function to get score of the move
     * @return
     */
    public int getScore() {
        return mScore;
    }
}
