package com.project;

/*
class for the play board
 */
public class PlayBoard {

    /*
    enum for all possible outcomes
     */
    public enum Outcome {
        NOTHING, DRAW, PLAYER_WINS, COMPUTER_WINS;
    }


    // boolean to track the whether the game is drawn
    private boolean mGameDraw;

    // reference to player win
    private int mStateValue;

    // play board instance
    private final int[][] mPlayBoard;

    // play board rows
    public final int mPlayBoardRows;

    // play board columns
    public final int mPlayBoardCols;

    // player winning starting index
    public int p, q;

    // int array to track free cells in each column
    private final int[] mFreeCells;

    // variables to track the win quadrant
    private int WIN_X = 0;
    private int WIN_Y = 0;

    // variable to track the count of disk to be present consecutively i,e. 4 in this case
    private final int WIN_DISK_COUNT = 4;

    /*
        constructor to get all the required parameters
     */
    public PlayBoard(int[][] playBoard, int[] free) {
        this.mPlayBoard = playBoard;
        mPlayBoardRows = playBoard.length;
        mPlayBoardCols = playBoard[0].length;
        mFreeCells = free;
    }

    public int[] getFreeCells() {
        return mFreeCells;
    }

    /**
     * function to return the outcome of the match
     *
     * @return constant value for state of the match
     */
    public Outcome checkWin() {

        // set the draw value to true
        mGameDraw = true;

        // initialize the mStateValue to 0
        mStateValue = 0;

        // check for win by all means and directions
        if (horizontalWinCheck() || verticalWinCheck() || ascendingDiagonalWinCheck() || descendingDiagonalWinCheck()) {
            return mStateValue == Player.PLAYER1 ? Outcome.PLAYER_WINS : Outcome.COMPUTER_WINS;
        }

        // if the game is not yet won by anyone then check for draw
        return mGameDraw ? Outcome.DRAW : Outcome.NOTHING;
    }

    /**
     * function to check if a combination exists horizontally
     *
     * @return boolean value for win or not
     */
    public boolean horizontalWinCheck() {

        // traverse through the playBoard using for loop
        for (int i = 0; i < mPlayBoardRows; ++i) {
            for (int j = 0; j < mPlayBoardCols - 3; ++j) {

                // fetch the value inside the matrix
                mStateValue = mPlayBoard[i][j];

                // check if the value of the at index i,j is 0
                if (mStateValue == 0) {
                    // it means that no disc is placed
                    mGameDraw = false;
                }

                if (!((j+1)>=0 && (j+1)<mPlayBoardCols && (j+2)>=0 && (j+2)<mPlayBoardCols && (j+3)>=0 && (j+3)<mPlayBoardCols)){
                    return false;
                }

                // if the matrix value at i,j is something then check for the 4 consecutive disks
                if (mStateValue != 0 && mPlayBoard[i][j + 1] == mStateValue && mPlayBoard[i][j + 2] == mStateValue && mPlayBoard[i][j + 3] == mStateValue) {
                    // if its true that means the player has won the game by making consecutive 4 disks horizontally
                    // note down the win index for reference
                    p = i;
                    q = j;

                    // save the win quadrant
                    WIN_X = 1;
                    WIN_Y = 0;

                    return true;
                }

            }
        }
        return false;
    }

    /**
     * function to check if a combination exists vertically
     *
     * @return boolean value for win or not
     */
    public boolean verticalWinCheck() {

        // traverse through the playBoard using for loop
        for (int j = 0; j < mPlayBoardCols; ++j) {
            for (int i = 0; i < mPlayBoardRows - 3; ++i) {

                // fetch the value inside the matrix
                mStateValue = mPlayBoard[i][j];

                // check if the value of the at index i,j is 0
                if (mStateValue == 0) {
                    // it means that no disc is placed
                    mGameDraw = false;
                }

                if (!((i+1)>=0 && (i+1)<mPlayBoardRows && (i+2)>=0 && (i+2)<mPlayBoardRows && (i+3)>=0 && (i+3)<mPlayBoardRows)){
                    return false;
                }

                // if the matrix value at i,j is something then check for the 4 consecutive disks column wise
                if (mStateValue != 0 && mPlayBoard[i + 1][j] == mStateValue && mPlayBoard[i + 2][j] == mStateValue && mPlayBoard[i + 3][j] == mStateValue) {
                    // if its true that means the player has won the game by making consecutive 4 disks vertically
                    // note down the win index for reference
                    p = i;
                    q = j;

                    // save the win quadrant
                    WIN_X = 0;
                    WIN_Y = 1;

                    return true;
                }

            }
        }
        return false;
    }


    /**
     * function to check if a combination exists on any ascending diagonal
     *
     * @return boolean value for win or not
     */
    public boolean ascendingDiagonalWinCheck() {

        // traverse through the playBoard using for loop
        for (int i = 0; i < mPlayBoardRows; ++i) {
            for (int j = 0; j < mPlayBoardCols - 3; ++j) {

                // fetch the value inside the matrix
                mStateValue = mPlayBoard[i][j];

                // check if the value of the at index i,j is 0
                if (mStateValue == 0) {
                    // it means that no disc is placed
                    mGameDraw = false;
                }

                // if the some indexes of diagonal are out of range return false
                if (!((i-1)>=0 && (i-1)<mPlayBoardRows && (j+1)>=0 && (j+1)<mPlayBoardCols && (i-2)>=0 && (i-2)<mPlayBoardRows && (j+2)>=0 && (j+2)<mPlayBoardCols && (i-3)>=0 && (i-3)<mPlayBoardRows && (j+3)>=0 && (j+3)<mPlayBoardCols )){
                    return false;
                }

                // if the matrix value at i,j is something then check for the 4 consecutive disks
                if (mStateValue != 0 && mPlayBoard[i - 1][j + 1] == mStateValue && mPlayBoard[i - 2][j + 2] == mStateValue && mPlayBoard[i - 3][j + 3] == mStateValue) {
                    // if its true that means the player has won the game by making consecutive 4 disks in ascending diagonal
                    // note down the win index for reference
                    p = i;
                    q = j;

                    // save the win quadrant
                    WIN_X = 1;
                    WIN_Y = -1;

                    return true;
                }

            }
        }
        return false;
    }

    /**
     * function to check if a combination exists on any descending diagonal
     *
     * @return boolean value for win or not
     */
    public boolean descendingDiagonalWinCheck() {

        // traverse through the playBoard using for loop
        for (int i = 3; i < mPlayBoardRows; ++i) {
            for (int j = 3; j < mPlayBoardCols; ++j) {

                // fetch the value inside the matrix
                mStateValue = mPlayBoard[i][j];

                // check if the value of the at index i,j is 0
                if (mStateValue == 0) {
                    // it means that no disc is placed
                    mGameDraw = false;
                }

                // if the matrix value at i,j is something then check for the 4 consecutive disks
                if (mStateValue != 0 && mPlayBoard[i - 1][j - 1] == mStateValue && mPlayBoard[i - 2][j - 2] == mStateValue && mPlayBoard[i - 3][j - 3] == mStateValue) {
                    // if its true that means the player has won the game by making consecutive 4 disks in descending diagonal
                    // note down the win index for reference
                    p = i;
                    q = j;

                    // save the win quadrant
                    WIN_X = -1;
                    WIN_Y = -1;

                    return true;
                }

            }
        }
        return false;
    }


    /**
     * function to place a disk in the matrix
     *
     * @param column - the index of the column where disk is to be placed
     * @param player - the player whose disk is to be placed
     */
    public void placeDisk(int column, int player) {
         if (mFreeCells[column] > 0) {
             mPlayBoard[mFreeCells[column] - 1][column] = player;
             mFreeCells[column]--;
         }
    }


    /**
     * function to get the height of a specific column
     *
     * @param index index value for the column
     * @return height of the column in integer
     */
    public int getColumnHeight(int index) {
        return mFreeCells[index];
    }


    /**
     * function to display play board
     */
    public void displayPlayBoard() {

        System.out.println();
        int columnLabel = 0;
        System.out.println("| C/R|  0 |  1 |  2 |  3 |  4 |  5 |  6 |");
        for (int i = 0; i < (mPlayBoardRows*2)+1; ++i) {
            for (int j = 0; j < (mPlayBoardCols*2)+1; ++j) {
                if (j==0){
                    if (columnLabel%2!=0){
                        System.out.print("|  " + columnLabel/2+" ");
                    }
                    else {
                        System.out.print("| -- ");
                    }
                    columnLabel++;
                }
                if (i%2 ==0 && j%2==0){
                    // both i and j are even
                    System.out.print("| ");
                }else if (i%2 ==0 && j%2!=0){
                    // i is even and j is odd
                    System.out.print("-- ");
                }else if(i%2 !=0 && j%2==0){
                    // i is odd and j is even
                    System.out.print("| ");
                } else{
                    // both are odd values
                    if (mPlayBoard[i/2][j/2] == 0){
                        // if the current place is empty
                        System.out.print("** ");
                    }else if (mPlayBoard[i/2][j/2] == 1){
                        // if the disk placed is of user
                        System.out.print(" P ");
                    }else {
                        // if the disk placed is of computer
                        System.out.print(" C ");
                    }
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * function to check for matches in all directions for a specific disk
     * @param column column index
     * @param row row index
     * @return boolean value for the matching having matches
     */
    public boolean checkAllMatches(int column, int row) {
        int horizontal_matches = 0;
        int vertical_matches = 0;
        int forward_diagonal_matches = 0;
        int backward_diagonal_matches = 0;

        // horizontal matches
        for (int i = 1; i < WIN_DISK_COUNT; i++) {
            if (matchingCounters(column, row, column + i, row)) {
                horizontal_matches++;
            } else break;
        }

        for (int i = 1; i < WIN_DISK_COUNT; i++) {
            if (matchingCounters(column, row, column - i, row)) {
                horizontal_matches++;
            } else break;
        }

        // vertical matches
        for (int i = 1; i < WIN_DISK_COUNT; i++) {
            if (matchingCounters(column, row, column, row + i)) {
                vertical_matches++;
            } else break;
        }

        for (int i = 1; i < WIN_DISK_COUNT; i++) {
            if (matchingCounters(column, row, column, row - i)) {
                vertical_matches++;
            } else break;
        }

        // backward diagonal matches ( \ )
        for (int i = 1; i < WIN_DISK_COUNT; i++) {
            if (matchingCounters(column, row, column + i, row - i)) {
                backward_diagonal_matches++;
            } else break;
        }

        for (int i = 1; i < WIN_DISK_COUNT; i++) {
            if (matchingCounters(column, row, column - i, row + i)) {
                backward_diagonal_matches++;
            } else break;
        }

        // forward diagonal matches ( / )
        for (int i = 1; i < WIN_DISK_COUNT; i++) {
            if (matchingCounters(column, row, column + i, row + i)) {
                forward_diagonal_matches++;
            } else break;
        }

        for (int i = 1; i < WIN_DISK_COUNT; i++) {
            if (matchingCounters(column, row, column - i, row - i)) {
                forward_diagonal_matches++;
            } else break;
        }

        return horizontal_matches >= WIN_DISK_COUNT - 1
                || vertical_matches >= WIN_DISK_COUNT - 1
                || forward_diagonal_matches >= WIN_DISK_COUNT - 1
                || backward_diagonal_matches >= WIN_DISK_COUNT - 1;
    }

    /**
     *
     * @param columnA column index for first disk
     * @param rowA row index for first disk
     * @param columnB column index for last disk
     * @param rowB row index for last disk
     * @return return boolean for matching counter
     */
    private boolean matchingCounters(int columnA, int rowA, int columnB, int rowB) {
        // return false if either set of coordinates falls out of bounds
        if (columnA < 0 || columnA >= mPlayBoardCols
                || rowA < 0 || rowA >= mPlayBoardRows
                || columnB < 0 || columnB >= mPlayBoardCols
                || rowB < 0 || rowB >= mPlayBoardRows) {
            return false;
        }
        return !(mPlayBoard[rowA][columnA] == 0 || mPlayBoard[rowB][columnB] == 0) && mPlayBoard[rowA][columnA] == mPlayBoard[rowB][columnB];
    }

    /**
     *  function to undo a disk placed in the playboard
     * @param column column index for removing a disk placed
     */
    public void undoMove(int column) {
        if (mFreeCells[column] < mPlayBoardRows) {
            mFreeCells[column]++;
            mPlayBoard[mFreeCells[column] - 1][column] = 0;

        }
    }

}
