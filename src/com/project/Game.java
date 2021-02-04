package com.project;

import java.util.Scanner;

public class Game {

    public static final int COLUMNS = 7;
    public static final int ROWS = 6;

    private final int[][] playBoard = new int[ROWS][COLUMNS];
    private final int[] mFreeCells = new int[COLUMNS];

    private final PlayBoard mPlayBoard = new PlayBoard(playBoard,mFreeCells);
    private ComputerizedPlayer compPlayer;
    private PlayBoard.Outcome outcome = PlayBoard.Outcome.NOTHING;
    private static Game game = new Game();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        game.startGame();
    }

    /**
     * function to reset the play board and start the game
     */
    public void startGame(){
        // initialize the game
        game.initializeGame();

        // get the computerized player
        game.compPlayer = game.getCompPlayer(game.mPlayBoard);

        // set difficulty for computerized player
        game.setDifficultyForComputerPlayer(game.compPlayer);

        // display the matrix
        game.mPlayBoard.displayPlayBoard();

        int POSSIBLE_MOVES = 6*7;

        // while loop for executing the game till all possible moves
        while(--POSSIBLE_MOVES>0){

            // get the player input
            int column = game.getPlayerInput();
            if (game.isPlayerInputValid(column)){
                // the player input is valid and can proceed further
                game.mPlayBoard.placeDisk(column,Player.PLAYER1);

                // checking if the user has won the match
                game.outcome = game.mPlayBoard.checkWin();

                // display the matrix
                game.mPlayBoard.displayPlayBoard();

                if (game.outcome == PlayBoard.Outcome.NOTHING){
                    // then match should continue

                    // placing the computer move
                    game.mPlayBoard.placeDisk(game.compPlayer.getColumn(),Player.COMPUTERIZED_PLAYER);

                    // displaying the play board after computer has placed the move
                    game.mPlayBoard.displayPlayBoard();

                    // checking if the user has won the match
                    game.outcome = game.mPlayBoard.checkWin();

                    // checking if the game should continue as no one has one the game and still moves are left
                    if (game.outcome == PlayBoard.Outcome.NOTHING){
                        continue;
                    }
                    // checking if all the moves are out and nobody has won the game and asking the user if he/she want to play the game again
                    else if (game.outcome == PlayBoard.Outcome.DRAW){
                        System.out.println("Match Drawn!!!");
                        askReplayPrompt();
                        break;
                    }
                    // checking if computer has won the game and asking the user if he/she want to play the game again
                    else if (game.outcome == PlayBoard.Outcome.COMPUTER_WINS){
                        System.out.println("Computer Wins!!!");
                        askReplayPrompt();
                        break;
                    }
                }
                // checking if player has won the game and asking the user if he/she want to play the game again
                else if (game.outcome == PlayBoard.Outcome.PLAYER_WINS){
                    System.out.println("Player 1 Wins!!!");
                    break;
                }
            }else{
                System.exit(1);
            }

        }


    }

    /**
     * function to ask replay prompt
     */
    private void askReplayPrompt() {
        System.out.println("Want to play a match again (y/n)");
        char replayChar = scanner.next().charAt(0);
        if (replayChar == 'y' || replayChar == 'Y'){
            game.initializeGame();
            game.startGame();
        }else{
            System.exit(1);
        }
    }

    /**
     * function to get the player input
     * @return player input
     */
    public int getPlayerInput(){
        System.out.println("Player Enter column no for placing disk::");
        return scanner.nextInt();
    }

    /**
     * function to check if the user input is valid or not
     * @param input column number of the matrix to be placed
     * @return boolean value specifying that if input is valid or not
     */
    public boolean isPlayerInputValid(int input){
        if (input >= 0 && input <=6){
            return true;
        }else {
            System.out.println("Invalid Column Please Enter Column Again");
            return false;
        }
    }

    /**
     * function to re-initialize the game by resetting the play board and restarting the game
     */
    public void initializeGame(){
        outcome = PlayBoard.Outcome.NOTHING;
        for (int j=0;j<COLUMNS;++j){
            for (int i=0;i<ROWS;++i){
                playBoard[i][j] = 0;
            }
            mFreeCells[j] = ROWS;
        }
    }

    /**
     * function to get the instance of the computerized player
     * @param playBoard play board instance
     * @return computerized player instance
     */
    public ComputerizedPlayer getCompPlayer(PlayBoard playBoard){
        return new ComputerizedPlayer(playBoard);
    }

    /**
     * function to set the difficulty of the game by setting the depth of the minmax algorithm
     * @param player computerized player instance
     */
    public void setDifficultyForComputerPlayer(ComputerizedPlayer player){
        player.setDifficulty(7);
    }

}
