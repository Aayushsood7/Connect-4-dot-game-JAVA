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

}
