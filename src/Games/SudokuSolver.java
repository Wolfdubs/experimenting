package Games;

public class SudokuSolver {

    private static final int GRIDSIZE = 9;


    //Traverse each row in the board. For every index currently 0, iterate through 1-9 to check if the number is valid to be added
    //Need backtracking; if at any index, no 1-9 is valid, then backtrack to the last index added, and remove the number added and try
    //from number+1 instead for it. If a number can be added, continue forward. If not, backtrack again.
    //Keep traversing until entire grid is filled with valid moves

    public static void main(String[] args) {
        int[][] gameBoard = {         //the structure of the board with starting values
                {0,0,0,0,0,5,0,2,0},
                {0,0,1,2,9,6,0,0,0},
                {0,0,0,0,0,4,9,0,0},
                {0,9,7,8,0,0,0,0,0},
                {0,0,0,9,0,1,0,0,0},
                {1,0,8,0,0,7,2,4,9},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {8,0,6,5,0,3,0,0,2}
        };
        printBoard(gameBoard);
        if (solveBoard(gameBoard)) {
            System.out.println("Sudoku problem solved:\n");
            printBoard(gameBoard);
        }
        else System.out.println("Sudoku problem is unsolvable");

    }

    private static boolean solveBoard(int[][] board){
        for (int row = 0; row<GRIDSIZE; row++){   //iterates over every row
            for (int column = 0; column<GRIDSIZE; column++){    //iterates over every column, so looping through entire grid
                if (board[row][column] == 0) {      //at any blank spot, below code will then execute. if already has value, it will skip
                    for (int numberToTest = 1; numberToTest<=GRIDSIZE; numberToTest++){  //for each blank, try every number 1-9
                        if (isNumberAllowed(board, row, column, numberToTest)){   //check if the current number is a valid placement (not already in row/column/box)
                            board[row][column] = numberToTest;
                            if (solveBoard(board)){  //recursion needed to pass in the now updated board with the new number inside
                                return true;   //if the new number was valid, and all the recursive calls beyond that succeeded (so every index was given a valid number), then this true bubbles up from every recursive call to return true for the entire board. ie this current placement led to the entire board being solved with the recursive calls
                            }  //so tried to place a number at the index, saw it was valid placement, and so tried to solve the rest of the board. if the rest of the board cannot be solved, then the point it fails will return false for (solveBoard(board)), which will bubble back up to the current number tried
                            else{  //if the new number meant the recursive calls to solveBoard did not return true, so didn't lead to a completable board, then clear out the number that was tried
                                board[row][column] = 0;  //now the numberToTest for loop will just try to place the next number along into the current index
                            }
                        }
                    } return false; //means for the current index, no number could be added, so board had been made unsolvable by the prior insertion
                }
            }
        } return true;  //if you iterate through the entire board with no problems
    }

    private static boolean isNumberAllowed(int[][] board, int row, int column, int num){
        return !isNumberInRow(board, row, num) && !isNumberInColumn(board, column, num) && !isNumberInBox(board, row, column, num);
    }

    //Helper methods to see if a value already exists in a row/column/3x3 square. if so, don't place a number there
    private static boolean isNumberInRow(int[][] board, int row, int num){   //takes in the current board, the row to check and the number to check against
        for (int i=0; i<GRIDSIZE; i++) {
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

    private static boolean isNumberInBox(int[][] board, int rowIndex, int columnIndex, int num){
        //identify which of 9 boxes the row&column align to
        //start at top left corner and iterate through box searching for num
      //  rowIndecies & columnIndecies for upperleft either 0, 3, 6
        int upperLeftRow = rowIndex - rowIndex % 3;
        int upperLeftColumn = columnIndex - columnIndex % 3;
        for (int i = upperLeftRow; i <= upperLeftRow+2; i++){
            for (int j = upperLeftColumn; j<= upperLeftColumn+2; j++){
                if (board[i][j]==num)
                    return true;
            }
        }
        return false;
    }

    private static void printBoard(int[][] board){
        for (int row = 0; row<GRIDSIZE; row++){
            if (row%3==0 && row!=0){            //the row!=0 is to make sure nothing prints out above the top row
                System.out.println("-----------"); //print a series of -- between every 3rd row
            }
            for (int column=0; column<GRIDSIZE; column++){
                if (column % 3 ==0 && column!=0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();   //printing each row on a new line
        }
    }



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
