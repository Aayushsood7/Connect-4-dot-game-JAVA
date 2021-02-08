package com.project;

import java.util.Scanner;

public class Game {

    public static final int COLUMNS = 7;
    public static final int ROWS = 6;

    private int[][] playBoard = new int[ROWS][COLUMNS];
    private final int[] mFreeCells = new int[COLUMNS];

    private final PlayBoard mPlayBoard = new PlayBoard(playBoard, mFreeCells);
    private ComputerizedPlayer compPlayer;
    private PlayBoard.Outcome outcome = PlayBoard.Outcome.NOTHING;
    private static final Game game = new Game();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        game.startGame();
    }

    /**
     * function to reset the play board and start the game
     */
    public void startGame() {

        // initialize the game
        game.initializeGame();

        // get the computerized player
        game.compPlayer = game.getCompPlayer(game.mPlayBoard);

        // set difficulty for computerized player
        game.setDifficultyForComputerPlayer(game.compPlayer);

        // display the matrix
        game.mPlayBoard.displayPlayBoard();

        int POSSIBLE_MOVES = 6 * 7;

        // while loop for executing the game till all possible moves
        while (--POSSIBLE_MOVES > 0) {

            // get the player input
            int column = game.getPlayerInput();
            // the player input is valid and can proceed further
            game.mPlayBoard.placeDisk(column, Player.PLAYER1);

            // checking if the user has won the match
            game.outcome = game.mPlayBoard.checkWin();

            // display the matrix
            game.mPlayBoard.displayPlayBoard();

            if (game.outcome == PlayBoard.Outcome.NOTHING) {
                // then match should continue

                // placing the computer move
                int compColumn = game.compPlayer.getColumn();
                game.mPlayBoard.placeDisk(compColumn, Player.COMPUTERIZED_PLAYER);
                System.out.println("Computer has placed disk at row " + game.mPlayBoard.getFreeCells()[compColumn] + " ,column " + (compColumn));

                // displaying the play board after computer has placed the move
                game.mPlayBoard.displayPlayBoard();

                // checking if the user has won the match
                game.outcome = game.mPlayBoard.checkWin();

                // checking if the game should continue as no one has one the game and still moves are left
                if (game.outcome == PlayBoard.Outcome.NOTHING) {
                    continue;
                }
                // checking if all the moves are out and nobody has won the game and asking the user if he/she want to play the game again
                else if (game.outcome == PlayBoard.Outcome.DRAW) {
                    System.out.println("Match Drawn!!!");
                    askReplayPrompt();
                    break;
                }
                // checking if computer has won the game and asking the user if he/she want to play the game again
                else if (game.outcome == PlayBoard.Outcome.COMPUTER_WINS) {
                    System.out.println("Computer Wins!!!");
                    askReplayPrompt();
                    break;
                }
            }
            // checking if player has won the game and asking the user if he/she want to play the game again
            else if (game.outcome == PlayBoard.Outcome.PLAYER_WINS) {
                System.out.println("Player 1 Wins!!!");
                break;
            }

        }


    }

    /**
     * function to ask replay prompt
     */
    private void askReplayPrompt() {
        System.out.println("Want to play a match again (y/n)");
        char replayChar = scanner.next().charAt(0);
        if (replayChar == 'y' || replayChar == 'Y') {
            game.initializeGame();
            game.startGame();
        } else {
            System.exit(1);
        }
    }

    /**
     * function to get the player input
     *
     * @return player input
     */
    public int getPlayerInput() {
        while (true) {

            try {
                System.out.println("Enter Column number to place a disk::");
                int input = scanner.nextInt();
                if (input < 0 || input > 6) {
                    System.out.println("Enter a valid column number!!");
                } else {
                    if (game.mPlayBoard.getFreeCells()[input] > 0) {
                        return input;
                    } else {
                        System.out.println("Entered column is filled please select different column!!");
                    }
                }
            }catch (Exception e){
                System.out.println("Entered input is not a number. Please enter number again!!");
                scanner.nextLine();
            }
        }
    }

    /**
     * function to re-initialize the game by resetting the play board and restarting the game
     */
    public void initializeGame() {
        System.out.println("******  Connect 4 Dot Game ******");
        System.out.println("Instructions::\n1.Enter the column number to place a disk on the playboard\n2.P Disk represents your disk, C disk represents computerized player disk and ** represents empty slots\n3.One who makes 4 consecutive disk in any direction wins the game");
        System.out.println("Made by Aayush Sood");
        outcome = PlayBoard.Outcome.NOTHING;
        for (int j = 0; j < COLUMNS; ++j) {
            for (int i = 0; i < ROWS; ++i) {
                playBoard[i][j] = 0;
            }
            mFreeCells[j] = ROWS;
        }
    }

    /**
     * function to get the instance of the computerized player
     *
     * @param playBoard play board instance
     * @return computerized player instance
     */
    public ComputerizedPlayer getCompPlayer(PlayBoard playBoard) {
        return new ComputerizedPlayer(playBoard);
    }

    /**
     * function to set the difficulty of the game by setting the depth of the minmax algorithm
     *
     * @param player computerized player instance
     */
    public void setDifficultyForComputerPlayer(ComputerizedPlayer player) {
        player.setDifficulty(7);
    }

    public PlayBoard getPlayBoard() {
        return mPlayBoard;
    }

    public void setPlayBoard(int[][] playBoard) {
        this.playBoard = playBoard;
    }
}
