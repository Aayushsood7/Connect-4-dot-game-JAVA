package com.project;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GameTest {

    /**
     * Test function to check if the checkWin method is giving player as winner
     */
    @Test
    public void doesPlayerWon() {
        int[] free = {6,6,6,6,6,6,6};
        int[][] playBoard = {{0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0},
                {0,1,0,0,0,0,0},
                {0,1,0,0,0,0,0},
                {0,1,2,0,0,2,0}};

        PlayBoard playBoardObj = new PlayBoard(playBoard,free);
        PlayBoard.Outcome expectedOutcome = PlayBoard.Outcome.PLAYER_WINS;

        PlayBoard.Outcome actualOutcome = playBoardObj.checkWin();

        Assert.assertEquals(actualOutcome,expectedOutcome);

    }


    /**
     * Test function to check if the disk is placed on correct spot in the playBoard
     */
    @Test
    public void isDiskPlaced(){
        int[] free = {6,6,6,6,6,6,6};
        int[][] actualPlayBoard = {{0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}};

        PlayBoard mPlayBoard = new PlayBoard(actualPlayBoard,free);
        mPlayBoard.placeDisk(6,Player.PLAYER1);

        Assert.assertEquals(actualPlayBoard[5][6],Player.PLAYER1);

    }

    /**
     * Test function to check if the checkWin method is giving computerized player as winner
     */
    @Test
    public void doesComputerizedPlayerWon() {
        int[] free = {6,6,6,6,6,6,6};
        int[][] playBoard = {{0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,2,0},
                {0,1,0,0,2,1,0},
                {0,1,0,2,1,1,0},
                {0,1,2,1,1,1,0}};

        PlayBoard playBoardObj = new PlayBoard(playBoard,free);
        PlayBoard.Outcome expectedOutcome = PlayBoard.Outcome.COMPUTER_WINS;

        PlayBoard.Outcome actualOutcome = playBoardObj.checkWin();

        Assert.assertEquals(actualOutcome,expectedOutcome);

    }

    /**
     * Test function to check if the playBoard is properly initialized and properly reset
     */
    @Test
    public void initializeGame() {

        int[][] playBoard = {{0,1,2,0,1,2,0},
                {0,1,2,0,1,2,0},
                {0,1,2,0,1,2,0},
                {0,1,2,0,1,2,0},
                {0,1,2,0,1,2,0},
                {0,1,2,0,1,2,0}};


        int[][] expectedPlayBoard = {{0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}};


        Game game = new Game();
        game.setPlayBoard(playBoard);
        game.initializeGame();

        Assert.assertTrue("PlayBoard Reset Test Case",equal(playBoard,expectedPlayBoard));

    }

    /**
     * Test function to check if the setDifficulty method is setting appropriate difficulty value
     */
    @Test
    public void setDifficultyForComputerPlayer() {
        int[] free = {6,6,6,6,6,6,6};
        PlayBoard playBoard = new PlayBoard(new int[6][7],free);
        ComputerizedPlayer player = new ComputerizedPlayer(playBoard);
        int actualDifficulty = 7;
        player.setDifficulty(actualDifficulty);
        Assert.assertEquals(actualDifficulty,player.getDifficulty());
    }

    /**
     * Helper function to check if two matrices are equal or not
     * @param matrix1 instance of the first matrix
     * @param matrix2 instance of the second matrix
     * @return boolean value for equality
     */
    public boolean equal(final int[][] matrix1, final int[][] matrix2) {

        // if matrix1 and matrix2 are null
        if (matrix1 == null) {
            return (matrix2 == null);
        }

        // if matrix1 is not null but matrix2 is null
        if (matrix2 == null) {
            return false;
        }

        // matrix1 and matrix2 have different lengths
        if (matrix1.length != matrix2.length) {
            return false;
        }

        // checking each value inside both matrices
        for (int i = 0; i < matrix1.length; i++) {

            // if any of the value is different return false
            if (!Arrays.equals(matrix1[i], matrix2[i])) {
                return false;
            }
        }

        // if all the criteria of equality is fulfilled then return true
        return true;
    }

}