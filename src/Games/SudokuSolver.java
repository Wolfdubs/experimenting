package Games;

public class SudokuSolver {

    private static final int GRIDSIZE = 9;


    public static void main(String[] args) {
        int[][] gameBoard = {         //the structure of the gameboard with starting values
                {5,4,0,9,0,0,0,6,0},
                {3,0,0,0,0,0,4,0,7},
                {0,0,0,0,0,3,1,2,0},
                {0,8,4,3,0,6,2,5,0},
                {1,0,0,2,0,0,7,0,9},
                {0,2,3,0,0,0,6,8,0},
                {0,0,5,0,6,0,0,1,0},
                {0,0,8,9,2,0,9,0,0},
                {2,7,9,0,0,1,0,0,0}
        };
    }

    //Helper methods to see if a value already exists in a row/column/3x3 square. if so, don't place a number there
    private static boolean isNumberInRow(int[][] board, int row, int num){   //takes in the current board, the row to check and the number to check against
        for (int i=0; i<board[row].length; i++) {
            if (board[row][i] == num)
                return true;
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int column, int num){
        for (int i=0; i<GRIDSIZE; i++) {
            if (board[i][column] == num)
                return true;
        }
        return false;
    }

    //indexBeingCheck e.g. [4][7]; row 4, column 7
    private static boolean isNumberIn3x3Square(int[][] board, int rowIndex, int columnIndex, int num){
        int squareIterator;
        if (rowIndex<=2) {
            if (columnIndex<=2){
                for (int i=0; i<=2; i++){
                    for (int j=0; j<=2; j++){
                        squareIterator = board[i][j];
                        if (squareIterator==num){
                            return true;
                        }
                    }
                }
            }
            if (columnIndex<=5){
                for (int i=0; i<=2; i++){
                    for (int j=0+3; j<=2+3; j++){
                        squareIterator = board[i][j];
                        if (squareIterator==num){
                            return true;
                        }
                    }
                }
            }
            if (columnIndex<=8){
                for (int i=0; i<=2; i++){
                    for (int j=0+6; j<=2+6; j++){
                        squareIterator = board[i][j];
                        if (squareIterator==num){
                            return true;
                        }
                    }
                }
            }
        }
        if (rowIndex<=5){
            if (columnIndex<=2){
                for (int i=0+3; i<=2+3; i++){
                    for (int j=0; j<=2; j++){
                        squareIterator = board[i][j];
                        if (squareIterator==num){
                            return true;
                        }
                    }
                }

            }
            if (columnIndex<=5){
                for (int i=0+3; i<=2+3; i++){
                    for (int j=0+3; j<=2+3; j++){
                        squareIterator = board[i][j];
                        if (squareIterator==num){
                            return true;
                        }
                    }
                }

            }
            if (columnIndex<=8){
                for (int i=0+3; i<=2+3; i++){
                    for (int j=0+6; j<=2+6; j++){
                        squareIterator = board[i][j];
                        if (squareIterator==num){
                            return true;
                        }
                    }
                }
            }
        }
        if (rowIndex<=8){
            if (columnIndex<=2){
                for (int i=0+6; i<=2+6; i++){
                    for (int j=0; j<=2; j++){
                        squareIterator = board[i][j];
                        if (squareIterator==num){
                            return true;
                        }
                    }
                }
            }
            if (columnIndex<=5){
                for (int i=0+6; i<=+6; i++){
                    for (int j=0+3; j<=2+3; j++){
                        squareIterator = board[i][j];
                        if (squareIterator==num){
                            return true;
                        }
                    }
                }
            }
            if (columnIndex<=8){
                for (int i=0+6; i<=2+6; i++){
                    for (int j=0+6; j<=2+6; j++){
                        squareIterator = board[i][j];
                        if (squareIterator==num){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
