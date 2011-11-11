import java.util.ArrayList;

/**
 * This class defines a sudoku puzzle and also includes a solution method.
 * @author hrkalona
 */
public class Sudoku {
  private char[][][] sudoku_board; //The Sudoku board and some temporary boards, one for each number, that helps in solving.
  private ArrayList<NumberOrder> list; //The list of the numbers.
  private boolean started; //The solving process has started?
  private boolean solved; //The sudoku was solved?
  private boolean failed; //The sudoku solving process has failed?
  public static final int NUMBER_COUNT = 81; //The maximum number of numbers that can be stored in the board.

    /**
     * The constractor, Sudoku, creates a sudoku puzzle.
     * @param board The
     */
    public Sudoku(char[][] board) {

        sudoku_board = new char[10][9][9]; //10 9x9 boards, the original one and 9 temporary boards.
        sudoku_board[0] = board; //The first board points to the board that needs to be solved.

        list = new ArrayList<NumberOrder>(9); //Creating a 9 spot list.

        for(int i = 1; i < sudoku_board.length; i++) {
            for(int j = 0; j < sudoku_board[0].length; j++) {
                for(int k = 0; k < sudoku_board[0][0].length; k++) {
                    sudoku_board[i][j][k] = sudoku_board[0][j][k]; //The temporary boards get the same numbers from the original board.
                }
            }
        }

        started = false; //Setting the default setting parameters.
        solved = false;
        failed = false;

        sort(); //Adding the numbers to the list according to its number instance count.
        
    }

    /*
     * The method, countNumber, is counting the instances of a number in the board.
     * Parameters: which_number <- The number that needs to be counted.
     */
    private int countNumber(char which_number) {
      int count = 0;

        for(int i = 0; i < sudoku_board[0].length; i++) {
            for(int j = 0; j < sudoku_board[0][0].length; j++) {
                if(sudoku_board[0][i][j] == which_number) {
                    count++;
                }
            }
        }
        return count;

    }

    /**
     * The method, solve, is solving only a number depending on its parameter.
     * @param which_node Which node from the list will be solved, aka which number.
     */
    public void solve(int which_node) {
        
        xMap(list.get(which_node).getIntNumber(), list.get(which_node).getCharNumber(), which_node);
        
    }

     /*
     * The method, checkNumber, is locating the position in the list that a number needs to be placed.
     * Parameters: Number <- The number that needs to be placed in the list.
     */
    private int checkNumber(NumberOrder Number) {
      int i = 0;

        while(i < list.size()) {
            if(Number.getCount() > list.get(i).getCount()) { //find the spot
                return i;
            }
            i++;
        }

        return i;

    }

    /*
     * The method, xMap, contains the algorithms that solve a number.
     * Parameters: numeric_value <- The number in integer form, character_value <- The number in character form, list_node <- The node of the list.
     */
    private void xMap(int numeric_value, char character_value, int list_node) {
      boolean flag = false;
      int counter = 0, i, j, counter2 = 0, counter3 = 0;

        //If a 3x3 block contains that number, then the free cells of the block need to be eliminated by placing a X in the free cells.
        //The following code checks the 9 blocks.
        for(i = 0; i < 3; i++) {
            for(j = 0; j < 3; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    break;
                }
            }
        }

        if(flag == true) {
            for(i = 0; i < 3; i++) {
                for(j = 0; j < 3; j++) {
                    if(!Character.isDigit(sudoku_board[numeric_value][i][j])) {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
            }
        }

        for(i = 0, flag = false; i < 3; i++) {
            for(j = 3; j < 6; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    break;
                }
            }
        }

        if(flag == true) {
            for(i = 0; i < 3; i++) {
                for(j = 3; j < 6; j++) {
                    if(!Character.isDigit(sudoku_board[numeric_value][i][j])) {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
            }
        }

        for(i = 0, flag = false; i < 3; i++) {
            for(j = 6; j < 9; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    break;
                }
            }
        }

        if(flag == true) {
            for(i = 0; i < 3; i++) {
                for(j = 6; j < 9; j++) {
                    if(!Character.isDigit(sudoku_board[numeric_value][i][j])) {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
            }
        }

        for(i = 3, flag = false; i < 6; i++) {
            for(j = 0; j < 3; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    break;
                }
            }
        }

        if(flag == true) {
            for(i = 3; i < 6; i++) {
                for(j = 0; j < 3; j++) {
                    if(!Character.isDigit(sudoku_board[numeric_value][i][j])) {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
            }
        }

        for(i = 3, flag = false; i < 6; i++) {
            for(j = 3; j < 6; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    break;
                }
            }
        }

        if(flag == true) {
            for(i = 3; i < 6; i++) {
                for(j = 3; j < 6; j++) {
                    if(!Character.isDigit(sudoku_board[numeric_value][i][j])) {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
            }
        }

        for(i = 3, flag = false; i < 6; i++) {
            for(j = 6; j < 9; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    break;
                }
            }
        }

        if(flag == true) {
            for(i = 3; i < 6; i++) {
                for(j = 6; j < 9; j++) {
                    if(!Character.isDigit(sudoku_board[numeric_value][i][j])) {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
            }
        }

        for(i = 6, flag = false; i < 9; i++) {
            for(j = 0; j < 3; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    break;
                }
            }
        }

        if(flag == true) {
            for(i = 6; i < 9; i++) {
                for(j = 0; j < 3; j++) {
                    if(!Character.isDigit(sudoku_board[numeric_value][i][j])) {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
            }
        }

        for(i = 6, flag = false; i < 9; i++) {
            for(j = 3; j < 6; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    break;
                }
            }
        }

        if(flag == true) {
            for(i = 6; i < 9; i++) {
                for(j = 3; j < 6; j++) {
                    if(!Character.isDigit(sudoku_board[numeric_value][i][j])) {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
            }
        }

        for(i = 6, flag = false; i < 9; i++) {
            for(j = 6; j < 9; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    break;
                }
            }
        }

        if(flag == true) {
            for(i = 6; i < 9; i++) {
                for(j = 6; j < 9; j++) {
                    if(!Character.isDigit(sudoku_board[numeric_value][i][j])) {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
            }
        }


        //If a row contains that number, then the free cells of the row need to be eliminated by placing a X in the free cells.
        //The following code checks the 9 rows.
        for(i = 0, flag = false; i < sudoku_board[0].length; i++) {
            for(j = 0; j < sudoku_board[0][0].length; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                }
            }
            if(flag == true) {
                for(j = 0; j < sudoku_board[0][0].length; j++) {
                    if(!Character.isDigit(sudoku_board[numeric_value][i][j]) && sudoku_board[numeric_value][i][j] != 'X') {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
                flag = false;
            }
        }

        //If a column contains that number, then the free cells of the column need to be eliminated by placing a X in the free cells.
        //The following code checks the 9 columns.
        for(j = 0; j < sudoku_board[0][0].length; j++) {
            for(i = 0; i < sudoku_board[0].length; i++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                }
            }
            if(flag == true) {
                for(i = 0; i < sudoku_board[0].length; i++) {
                        if(!Character.isDigit(sudoku_board[numeric_value][i][j]) && sudoku_board[numeric_value][i][j] != 'X') {
                            sudoku_board[numeric_value][i][j] = 'X';
                        }
                }
                flag = false;
            }
        }


        //If a block contains only 2 parallel lines of free cells in a column direction, then if another block, in the same columns,
        //contains also only 2 parallel lines of free cells then last block cannot have this number in those columns.
        //Graphically can be seen like a big X between the participant blocks.
        for(j = 0, counter = 0, counter2 = 0, counter3 = 0; j < 7; j += 3, counter = 0, counter2 = 0, counter3 = 0) {
            for(int l = j; l < j + 3; l++) {
                for(int k = 0; k < 3; k++) {
                    if(l == j && sudoku_board[numeric_value][k][l] == ' ') {
                        counter++;
                    }
                    if(l == j + 1 && sudoku_board[numeric_value][k][l] == ' ') {
                        counter2++;
                    }
                    if(l == j + 2 && sudoku_board[numeric_value][k][l] == ' ') {
                        counter3++;
                    }
                }
            }

            int temp_counter = 0, temp_counter2 = 0, temp_counter3 = 0;

            if((counter == 1 || counter == 2 || counter == 3) && (counter2 == 1 || counter2 == 2 || counter2 == 3) && counter3 == 0) {
                for(int b = j; b < j + 3; b++) {
                    for(int a = 3; a < 6; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && temp_counter3 == 0) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][a][j] == ' ') {
                            sudoku_board[numeric_value][a][j] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 1] == ' ') {
                            sudoku_board[numeric_value][a][j + 1] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int b = j; b < j + 3; b++) {
                    for(int a = 6; a < 9; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && temp_counter3 == 0) {
                    for(int a = 3; a < 6; a++) {
                        if(sudoku_board[numeric_value][a][j] == ' ') {
                            sudoku_board[numeric_value][a][j] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 1] == ' ') {
                            sudoku_board[numeric_value][a][j + 1] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;
            }

            if((counter == 1 || counter == 2 || counter == 3) && counter2 == 0  && (counter3 == 1 || counter3 == 2 || counter3 == 3)) {
                for(int b = j; b < j + 3; b++) {
                    for(int a = 3; a < 6; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && temp_counter2 == 0 && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][a][j] == ' ') {
                            sudoku_board[numeric_value][a][j] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 2] == ' ') {
                            sudoku_board[numeric_value][a][j + 2] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int b = j; b < j + 3; b++) {
                    for(int a = 6; a < 9; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && temp_counter2 == 0 && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 3; a < 6; a++) {
                        if(sudoku_board[numeric_value][a][j] == ' ') {
                            sudoku_board[numeric_value][a][j] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 2] == ' ') {
                            sudoku_board[numeric_value][a][j + 2] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;
            }

            if(counter == 0 && (counter2 == 1 || counter2 == 2 || counter2 == 3) && (counter3 == 1 || counter3 == 2 || counter3 == 3)) {
                for(int b = j; b < j + 3; b++) {
                    for(int a = 3; a < 6; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if(temp_counter == 0 && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][a][j + 1] == ' ') {
                            sudoku_board[numeric_value][a][j + 1] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 2] == ' ') {
                            sudoku_board[numeric_value][a][j + 2] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int b = j; b < j + 3; b++) {
                    for(int a = 6; a < 9; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if(temp_counter == 0 && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 3; a < 6; a++) {
                        if(sudoku_board[numeric_value][a][j + 1] == ' ') {
                            sudoku_board[numeric_value][a][j + 1] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 2] == ' ') {
                            sudoku_board[numeric_value][a][j + 2] = 'X';
                        }
                    }
                }
            }
        }


        for(j = 0, counter = 0, counter2 = 0, counter3 = 0; j < 7; j += 3, counter = 0, counter2 = 0, counter3 = 0) {
            for(int l = j; l < j + 3; l++) {
                for(int k = 3; k < 6; k++) {
                    if(l == j && sudoku_board[numeric_value][k][l] == ' ') {
                        counter++;
                    }
                    if(l == j + 1 && sudoku_board[numeric_value][k][l] == ' ') {
                        counter2++;
                    }
                    if(l == j + 2 && sudoku_board[numeric_value][k][l] == ' ') {
                        counter3++;
                    }
                }
            }

            int temp_counter = 0, temp_counter2 = 0, temp_counter3 = 0;

            if((counter == 1 || counter == 2 || counter == 3) && (counter2 == 1 || counter2 == 2 || counter2 == 3) && counter3 == 0) {
                for(int b = j; b < j + 3; b++) {
                    for(int a = 0; a < 3; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && temp_counter3 == 0) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][a][j] == ' ') {
                            sudoku_board[numeric_value][a][j] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 1] == ' ') {
                            sudoku_board[numeric_value][a][j + 1] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int b = j; b < j + 3; b++) {
                    for(int a = 6; a < 9; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && temp_counter3 == 0) {
                    for(int a = 0; a < 3; a++) {
                        if(sudoku_board[numeric_value][a][j] == ' ') {
                            sudoku_board[numeric_value][a][j] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 1] == ' ') {
                            sudoku_board[numeric_value][a][j + 1] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;
            }

            if((counter == 1 || counter == 2 || counter == 3) && counter2 == 0  && (counter3 == 1 || counter3 == 2 || counter3 == 3)) {
                for(int b = j; b < j + 3; b++) {
                    for(int a = 0; a < 3; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && temp_counter2 == 0 && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][a][j] == ' ') {
                            sudoku_board[numeric_value][a][j] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 2] == ' ') {
                            sudoku_board[numeric_value][a][j + 2] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int b = j; b < j + 3; b++) {
                    for(int a = 6; a < 9; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && temp_counter2 == 0 && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 0; a < 3; a++) {
                        if(sudoku_board[numeric_value][a][j] == ' ') {
                            sudoku_board[numeric_value][a][j] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 2] == ' ') {
                            sudoku_board[numeric_value][a][j + 2] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;
            }

            if(counter == 0 && (counter2 == 1 || counter2 == 2 || counter2 == 3) && (counter3 == 1 || counter3 == 2 || counter3 == 3)) {
                for(int b = j; b < j + 3; b++) {
                    for(int a = 0; a < 3; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if(temp_counter == 0 && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][a][j + 1] == ' ') {
                            sudoku_board[numeric_value][a][j + 1] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 2] == ' ') {
                            sudoku_board[numeric_value][a][j + 2] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int b = j; b < j + 3; b++) {
                    for(int a = 6; a < 9; a++) {
                        if(b == j && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter++;
                        }
                        if(b == j + 1 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter2++;
                        }
                        if(b == j + 2 && sudoku_board[numeric_value][a][b] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if(temp_counter == 0 && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 0; a < 3; a++) {
                        if(sudoku_board[numeric_value][a][j + 1] == ' ') {
                            sudoku_board[numeric_value][a][j + 1] = 'X';
                        }
                        if(sudoku_board[numeric_value][a][j + 2] == ' ') {
                            sudoku_board[numeric_value][a][j + 2] = 'X';
                        }
                    }
                }
            }
        }


        //If a block contains only 2 parallel lines of free cells in a row direction, then if another block, in the same rows
        //contains also only 2 parallel lines of free cells then last block cannot have this number in those rows.
        //Graphically can be seen like a big X between the participant blocks.
        for(i = 0, counter = 0, counter2 = 0, counter3 = 0; i < 7; i += 3, counter = 0, counter2 = 0, counter3 = 0) {
            for(int k = 0; k < 3; k++) {
                for(int l = i; l < i + 3; l++) {
                    if(l == i && sudoku_board[numeric_value][l][k] == ' ') {
                        counter++;
                    }
                    if(l == i + 1 && sudoku_board[numeric_value][l][k] == ' ') {
                        counter2++;
                    }
                    if(l == i + 2 && sudoku_board[numeric_value][l][k] == ' ') {
                        counter3++;
                    }
                }
            }

            int temp_counter = 0, temp_counter2 = 0, temp_counter3 = 0;

            if((counter == 1 || counter == 2 || counter == 3) && (counter2 == 1 || counter2 == 2 || counter2 == 3) && counter3 == 0) {
                for(int a = 3; a < 6; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && temp_counter3 == 0) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][i][a] == ' ') {
                            sudoku_board[numeric_value][i][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 1][a] == ' ') {
                            sudoku_board[numeric_value][i + 1][a] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int a = 6; a < 9; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && temp_counter3 == 0) {
                    for(int a = 3; a < 6; a++) {
                        if(sudoku_board[numeric_value][i][a] == ' ') {
                            sudoku_board[numeric_value][i][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 1][a] == ' ') {
                            sudoku_board[numeric_value][i + 1][a] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;
            }

            if((counter == 1 || counter == 2 || counter == 3) && counter2 == 0  && (counter3 == 1 || counter3 == 2 || counter3 == 3)) {
                for(int a = 3; a < 6; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && temp_counter2 == 0  && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][i][a] == ' ') {
                            sudoku_board[numeric_value][i][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 2][a] == ' ') {
                            sudoku_board[numeric_value][i + 2][a] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int a = 6; a < 9; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && temp_counter2 == 0  && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 3; a < 6; a++) {
                        if(sudoku_board[numeric_value][i][a] == ' ') {
                            sudoku_board[numeric_value][i][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 2][a] == ' ') {
                            sudoku_board[numeric_value][i + 2][a] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;
            }

            if(counter == 0 && (counter2 == 1 || counter2 == 2 || counter2 == 3) && (counter3 == 1 || counter3 == 2 || counter3 == 3)) {
                for(int a = 3; a < 6; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if(temp_counter == 0 && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][i + 1][a] == ' ') {
                            sudoku_board[numeric_value][i + 1][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 2][a] == ' ') {
                            sudoku_board[numeric_value][i + 2][a] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int a = 6; a < 9; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if(temp_counter == 0 && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 3; a < 6; a++) {
                        if(sudoku_board[numeric_value][i + 1][a] == ' ') {
                            sudoku_board[numeric_value][i + 1][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 2][a] == ' ') {
                            sudoku_board[numeric_value][i + 2][a] = 'X';
                        }
                    }
                }
            }
        }


        for(i = 0, counter = 0, counter2 = 0, counter3 = 0; i < 7; i += 3, counter = 0, counter2 = 0, counter3 = 0) {
            for(int k = 3; k < 6; k++) {
                for(int l = i; l < i + 3; l++) {
                    if(l == i && sudoku_board[numeric_value][l][k] == ' ') {
                        counter++;
                    }
                    if(l == i + 1 && sudoku_board[numeric_value][l][k] == ' ') {
                        counter2++;
                    }
                    if(l == i + 2 && sudoku_board[numeric_value][l][k] == ' ') {
                        counter3++;
                    }
                }
            }

            int temp_counter = 0, temp_counter2 = 0, temp_counter3 = 0;

            if((counter == 1 || counter == 2 || counter == 3) && (counter2 == 1 || counter2 == 2 || counter2 == 3) && counter3 == 0) {
                for(int a = 0; a < 3; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && temp_counter3 == 0) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][i][a] == ' ') {
                            sudoku_board[numeric_value][i][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 1][a] == ' ') {
                            sudoku_board[numeric_value][i + 1][a] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int a = 6; a < 9; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && temp_counter3 == 0) {
                    for(int a = 0; a < 3; a++) {
                        if(sudoku_board[numeric_value][i][a] == ' ') {
                            sudoku_board[numeric_value][i][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 1][a] == ' ') {
                            sudoku_board[numeric_value][i + 1][a] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;
            }

            if((counter == 1 || counter == 2 || counter == 3) && counter2 == 0  && (counter3 == 1 || counter3 == 2 || counter3 == 3)) {
                for(int a = 0; a < 3; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && temp_counter2 == 0  && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][i][a] == ' ') {
                            sudoku_board[numeric_value][i][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 2][a] == ' ') {
                            sudoku_board[numeric_value][i + 2][a] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int a = 6; a < 9; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if((temp_counter == 1 || temp_counter == 2 || temp_counter == 3) && temp_counter2 == 0  && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 0; a < 3; a++) {
                        if(sudoku_board[numeric_value][i][a] == ' ') {
                            sudoku_board[numeric_value][i][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 2][a] == ' ') {
                            sudoku_board[numeric_value][i + 2][a] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;
            }

            if(counter == 0 && (counter2 == 1 || counter2 == 2 || counter2 == 3) && (counter3 == 1 || counter3 == 2 || counter3 == 3)) {
                for(int a = 0; a < 3; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if(temp_counter == 0 && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 6; a < 9; a++) {
                        if(sudoku_board[numeric_value][i + 1][a] == ' ') {
                            sudoku_board[numeric_value][i + 1][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 2][a] == ' ') {
                            sudoku_board[numeric_value][i + 2][a] = 'X';
                        }
                    }
                }
                temp_counter = 0;
                temp_counter2 = 0;
                temp_counter3 = 0;

                for(int a = 6; a < 9; a++) {
                    for(int b = i; b < i + 3; b++) {
                        if(b == i && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter++;
                        }
                        if(b == i + 1 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter2++;
                        }
                        if(b == i + 2 && sudoku_board[numeric_value][b][a] == ' ') {
                            temp_counter3++;
                        }
                    }
                }
                if(temp_counter == 0 && (temp_counter2 == 1 || temp_counter2 == 2 || temp_counter2 == 3) && (temp_counter3 == 1 || temp_counter3 == 2 || temp_counter3 == 3)) {
                    for(int a = 0; a < 3; a++) {
                        if(sudoku_board[numeric_value][i + 1][a] == ' ') {
                            sudoku_board[numeric_value][i + 1][a] = 'X';
                        }
                        if(sudoku_board[numeric_value][i + 2][a] == ' ') {
                            sudoku_board[numeric_value][i + 2][a] = 'X';
                        }
                    }
                }
            }
        }


        //If there is only 1 free spot in a block then the number should be placed there and all the boards need to be updated,
        //the count of that number needs to be updated, and the whole process needs to started again with recursion.
        //If there are 2 free spots and they form a vertical or horizontal line then all the rest free cells
        //in that row/column need to be eliminated.
        //If there are 3 free spots and they form a vertical or horizontal line then all the rest free cells
        //in that row/column need to be eliminated.
        //The following codes does the above.
        for(i = 0; i < 3; i++) {
            for(j = 0; j < 3; j++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    counter++;
                }
            }
        }
        if(counter == 1 && started == true) {
            for(i = 0; i < 3; i++) {
                for(j = 0; j < 3; j++) {
                    if(sudoku_board[numeric_value][i][j] == ' ') {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        break;
                    }
                }
            }
        }
        if(counter == 2) {
            for(i = 0; i < 3; i++) {
                for(j = 0; j < 3; j++) {
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 2 && sudoku_board[numeric_value][i + 1][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 1 && sudoku_board[numeric_value][i + 2][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 2 && sudoku_board[numeric_value][i][j + 1] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 1 && sudoku_board[numeric_value][i][j + 2] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }
        if(counter == 3) {
            for(i = 0; i < 3; i++) {
                for(j = 0; j < 3; j++) {
                   if((sudoku_board[numeric_value][i][j] == ' ' && i < 1 && sudoku_board[numeric_value][i + 1][j] == ' '
                   && sudoku_board[numeric_value][i + 2][j] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if((sudoku_board[numeric_value][i][j] == ' ' && j < 1 && sudoku_board[numeric_value][i][j + 1] == ' '
                   && sudoku_board[numeric_value][i][j + 2] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }

        for(i = 0, counter = 0; i < 3; i++) {
            for(j = 3; j < 6; j++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    counter++;
                }
            }
        }
        if(counter == 1 && started == true) {
            for(i = 0; i < 3; i++) {
                for(j = 3; j < 6; j++) {
                    if(sudoku_board[numeric_value][i][j] == ' ') {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        break;
                    }
                }
            }
        }
        if(counter == 2) {
            for(i = 0; i < 3; i++) {
                for(j = 3; j < 6; j++) {
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 2 && sudoku_board[numeric_value][i + 1][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 1 && sudoku_board[numeric_value][i + 2][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 5 && sudoku_board[numeric_value][i][j + 1] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 4 && sudoku_board[numeric_value][i][j + 2] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }
        if(counter == 3) {
            for(i = 0; i < 3; i++) {
                for(j = 3; j < 6; j++) {
                   if((sudoku_board[numeric_value][i][j] == ' ' && i < 1 && sudoku_board[numeric_value][i + 1][j] == ' '
                   && sudoku_board[numeric_value][i + 2][j] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if((sudoku_board[numeric_value][i][j] == ' ' && j < 4 && sudoku_board[numeric_value][i][j + 1] == ' '
                   && sudoku_board[numeric_value][i][j + 2] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }


        for(i = 0, counter = 0; i < 3; i++) {
            for(j = 6; j < 9; j++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    counter++;
                }
            }
        }
        if(counter == 1 && started == true) {
            for(i = 0; i < 3; i++) {
                for(j = 6; j < 9; j++) {
                    if(sudoku_board[numeric_value][i][j] == ' ') {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        break;
                    }
                }
            }
        }
        if(counter == 2) {
            for(i = 0; i < 3; i++) {
                for(j = 6; j < 9; j++) {
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 2 && sudoku_board[numeric_value][i + 1][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 1 && sudoku_board[numeric_value][i + 2][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 8 && sudoku_board[numeric_value][i][j + 1] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 7 && sudoku_board[numeric_value][i][j + 2] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }
        if(counter == 3) {
            for(i = 0; i < 3; i++) {
                for(j = 6; j < 9; j++) {
                   if((sudoku_board[numeric_value][i][j] == ' ' && i < 1 && sudoku_board[numeric_value][i + 1][j] == ' '
                   && sudoku_board[numeric_value][i + 2][j] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if((sudoku_board[numeric_value][i][j] == ' ' && j < 7 && sudoku_board[numeric_value][i][j + 1] == ' '
                   && sudoku_board[numeric_value][i][j + 2] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }


        for(i = 3, counter = 0; i < 6; i++) {
            for(j = 0; j < 3; j++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    counter++;
                }
            }
        }
        if(counter == 1 && started == true) {
            for(i = 3; i < 6; i++) {
                for(j = 0; j < 3; j++) {
                    if(sudoku_board[numeric_value][i][j] == ' ') {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        break;
                    }
                }
            }
        }
        if(counter == 2) {
            for(i = 3; i < 6; i++) {
                for(j = 0; j < 3; j++) {
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 5 && sudoku_board[numeric_value][i + 1][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 4 && sudoku_board[numeric_value][i + 2][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 2 && sudoku_board[numeric_value][i][j + 1] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 1 && sudoku_board[numeric_value][i][j + 2] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }
        if(counter == 3) {
            for(i = 3; i < 6; i++) {
                for(j = 0; j < 3; j++) {
                   if((sudoku_board[numeric_value][i][j] == ' ' && i < 4 && sudoku_board[numeric_value][i + 1][j] == ' '
                   && sudoku_board[numeric_value][i + 2][j] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if((sudoku_board[numeric_value][i][j] == ' ' && j < 1 && sudoku_board[numeric_value][i][j + 1] == ' '
                   && sudoku_board[numeric_value][i][j + 2] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }


        for(i = 3, counter = 0; i < 6; i++) {
            for(j = 3; j < 6; j++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    counter++;
                }
            }
        }
        if(counter == 1 && started == true) {
            for(i = 3; i < 6; i++) {
                for(j = 3; j < 6; j++) {
                    if(sudoku_board[numeric_value][i][j] == ' ') {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        break;
                    }
                }
            }
        }
        if(counter == 2) {
            for(i = 3; i < 6; i++) {
                for(j = 3; j < 6; j++) {
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 5 && sudoku_board[numeric_value][i + 1][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 4 && sudoku_board[numeric_value][i + 2][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 5 && sudoku_board[numeric_value][i][j + 1] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 4 && sudoku_board[numeric_value][i][j + 2] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }
        if(counter == 3) {
            for(i = 3; i < 6; i++) {
                for(j = 3; j < 6; j++) {
                   if((sudoku_board[numeric_value][i][j] == ' ' && i < 4 && sudoku_board[numeric_value][i + 1][j] == ' '
                   && sudoku_board[numeric_value][i + 2][j] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if((sudoku_board[numeric_value][i][j] == ' ' && j < 4 && sudoku_board[numeric_value][i][j + 1] == ' '
                   && sudoku_board[numeric_value][i][j + 2] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }


        for(i = 3, counter = 0; i < 6; i++) {
            for(j = 6; j < 9; j++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    counter++;
                }
            }
        }
        if(counter == 1 && started == true) {
            for(i = 3; i < 6; i++) {
                for(j = 6; j < 9; j++) {
                    if(sudoku_board[numeric_value][i][j] == ' ') {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        break;
                    }
                }
            }
        }
        if(counter == 2) {
            for(i = 3; i < 6; i++) {
                for(j = 6; j < 9; j++) {
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 5 && sudoku_board[numeric_value][i + 1][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 4 && sudoku_board[numeric_value][i + 2][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 8 && sudoku_board[numeric_value][i][j + 1] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 7 && sudoku_board[numeric_value][i][j + 2] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }
        if(counter == 3) {
            for(i = 3; i < 6; i++) {
                for(j = 6; j < 9; j++) {
                   if((sudoku_board[numeric_value][i][j] == ' ' && i < 4 && sudoku_board[numeric_value][i + 1][j] == ' '
                   && sudoku_board[numeric_value][i + 2][j] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if((sudoku_board[numeric_value][i][j] == ' ' && j < 7 && sudoku_board[numeric_value][i][j + 1] == ' '
                   && sudoku_board[numeric_value][i][j + 2] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }


        for(i = 6, counter = 0; i < 9; i++) {
            for(j = 0; j < 3; j++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    counter++;
                }
            }
        }
        if(counter == 1 && started == true) {
            for(i = 6; i < 9; i++) {
                for(j = 0; j < 3; j++) {
                    if(sudoku_board[numeric_value][i][j] == ' ') {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        break;
                    }
                }
            }
        }
        if(counter == 2) {
            for(i = 6; i < 9; i++) {
                for(j = 0; j < 3; j++) {
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 8 && sudoku_board[numeric_value][i + 1][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 7 && sudoku_board[numeric_value][i + 2][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 2 && sudoku_board[numeric_value][i][j + 1] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 1 && sudoku_board[numeric_value][i][j + 2] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }
        if(counter == 3) {
            for(i = 6; i < 9; i++) {
                for(j = 0; j < 3; j++) {
                   if((sudoku_board[numeric_value][i][j] == ' ' && i < 7 && sudoku_board[numeric_value][i + 1][j] == ' '
                   && sudoku_board[numeric_value][i + 2][j] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if((sudoku_board[numeric_value][i][j] == ' ' && j < 1 && sudoku_board[numeric_value][i][j + 1] == ' '
                   && sudoku_board[numeric_value][i][j + 2] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }


        for(i = 6, counter = 0; i < 9; i++) {
            for(j = 3; j < 6; j++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    counter++;
                }
            }
        }
        if(counter == 1 && started == true) {
            for(i = 6; i < 9; i++) {
                for(j = 3; j < 6; j++) {
                    if(sudoku_board[numeric_value][i][j] == ' ') {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        break;
                    }
                }
            }
        }
        if(counter == 2) {
            for(i = 6; i < 9; i++) {
                for(j = 3; j < 6; j++) {
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 8 && sudoku_board[numeric_value][i + 1][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 7 && sudoku_board[numeric_value][i + 2][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 5 && sudoku_board[numeric_value][i][j + 1] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 4 && sudoku_board[numeric_value][i][j + 2] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }
        if(counter == 3) {
            for(i = 6; i < 9; i++) {
                for(j = 3; j < 6; j++) {
                   if((sudoku_board[numeric_value][i][j] == ' ' && i < 7 && sudoku_board[numeric_value][i + 1][j] == ' '
                   && sudoku_board[numeric_value][i + 2][j] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if((sudoku_board[numeric_value][i][j] == ' ' && j < 4 && sudoku_board[numeric_value][i][j + 1] == ' '
                   && sudoku_board[numeric_value][i][j + 2] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }


        for(i = 6, counter = 0; i < 9; i++) {
            for(j = 6; j < 9; j++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    counter++;
                }
            }
        }
        if(counter == 1 && started == true) {
            for(i = 6; i < 9; i++) {
                for(j = 6; j < 9; j++) {
                    if(sudoku_board[numeric_value][i][j] == ' ') {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        break;
                    }
                }
            }
        }

        if(counter == 2) {
            for(i = 6; i < 9; i++) {
                for(j = 6; j < 9; j++) {
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 8 && sudoku_board[numeric_value][i + 1][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && i < 7 && sudoku_board[numeric_value][i + 2][j] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 8 && sudoku_board[numeric_value][i][j + 1] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                   if(sudoku_board[numeric_value][i][j] == ' ' && j < 7 && sudoku_board[numeric_value][i][j + 2] == ' ') {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }
        if(counter == 3) {
            for(i = 6; i < 9; i++) {
                for(j = 6; j < 9; j++) {
                   if((sudoku_board[numeric_value][i][j] == ' ' && i < 7 && sudoku_board[numeric_value][i + 1][j] == ' '
                   && sudoku_board[numeric_value][i + 2][j] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i + 1][j] = 'T';
                       sudoku_board[numeric_value][i + 2][j] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][k][j] == ' ') {
                               sudoku_board[numeric_value][k][j] = 'X';
                           }
                           if(sudoku_board[numeric_value][k][j] == 'T') {
                               sudoku_board[numeric_value][k][j] = ' ';
                           }
                       }
                   }
                   if((sudoku_board[numeric_value][i][j] == ' ' && j < 7 && sudoku_board[numeric_value][i][j + 1] == ' '
                   && sudoku_board[numeric_value][i][j + 2] == ' ')) {
                       sudoku_board[numeric_value][i][j] = 'T';
                       sudoku_board[numeric_value][i][j + 1] = 'T';
                       sudoku_board[numeric_value][i][j + 2] = 'T';
                       for(int k = 0; k < sudoku_board[0].length; k++) {
                           if(sudoku_board[numeric_value][i][k] == ' ') {
                               sudoku_board[numeric_value][i][k] = 'X';
                           }
                           if(sudoku_board[numeric_value][i][k] == 'T') {
                               sudoku_board[numeric_value][i][k] = ' ';
                           }
                       }
                   }
                }
            }
        }

        if(started != true) {
            return;
        }

        //If there is only 1 free spot in a row then the number should be placed there and all the boards need to be updated,
        //the count of that number needs to be updated, and the whole process needs to started again with recursion.
        //The following codes does the above.
        for(i = 0, counter = 0; i < sudoku_board[0].length; i++) {
            for(j = 0; j < sudoku_board[0][0].length; j++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    counter++;
                }
            }
            if(counter == 1) {
                for(int l = 0; l < sudoku_board[0][0].length; l++) {
                    if(sudoku_board[numeric_value][i][l] == ' ') {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][l] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        counter = 0;
                        continue;
                    }
                }
            }
            counter = 0;
        }

        //If there is only 1 free spot in a column then the number should be placed there and all the boards need to be updated,
        //the count of that number needs to be updated, and the whole process needs to started again with recursion.
        //The following codes does the above.
        for(j = 0, counter = 0; j < sudoku_board[0][0].length; j++) {
            for(i = 0; i < sudoku_board[0].length; i++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    counter++;
                }
            }
            if(counter == 1) {
                for(int l = 0; l < sudoku_board[0].length; l++) {
                    if(sudoku_board[numeric_value][l][j] == ' ') {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][l][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        counter = 0;
                        continue;
                    }
                }
            }
            counter = 0;
        }

        for(i = 0, counter = 0; i < sudoku_board[0].length; i++) {
            for(j = 0; j < sudoku_board[0][0].length; j++) {
                if(sudoku_board[numeric_value][i][j] == ' ') {
                    for(int k = 1; k < sudoku_board.length; k++) {
                        if(sudoku_board[k][i][j] == ' ') {
                            counter++;
                        }
                    }
                    if(counter == 1) {
                        for(int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        xMap(numeric_value, character_value, list_node);
                        counter = 0;
                        continue;
                    }
                    counter = 0;
                }
            }
        }


        
        //If there are not any free spots in a block and the number in not contained in the block or if there are more than one instances
        //of that number in a block, then the solving process has failed.
        //It also works as a valid entry check.
        //The following code checks the 9 blocks.
        for(i = 0, counter = 0, counter2 = 0, flag = false; i < 3; i++) {
            for(j = 0; j < 3; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if(sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
        }

        if(counter2 > 1) {
            failed = true;
            return;
        }
        if(flag == false && counter == 9) {
            failed = true;
            return;
        }



        for(i = 0, counter = 0, counter2 = 0, flag = false; i < 3; i++) {
            for(j = 3; j < 6; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if(sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
        }

        if(counter2 > 1) {
            failed = true;
            return;
        }
        if(flag == false && counter == 9) {
            failed = true;
            return;
        }

        for(i = 0, counter = 0, counter2 = 0, flag = false; i < 3; i++) {
            for(j = 6; j < 9; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if(sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
        }

        if(counter2 > 1) {
            failed = true;
            return;
        }
        if(flag == false && counter == 9) {
            failed = true;
            return;
        }

        for(i = 3, counter = 0, counter2 = 0, flag = false; i < 6; i++) {
            for(j = 0; j < 3; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if(sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
        }

        if(counter2 > 1) {
            failed = true;
            return;
        }
        if(flag == false && counter == 9) {
            failed = true;
            return;
        }

        for(i = 3, counter = 0, counter2 = 0, flag = false; i < 6; i++) {
            for(j = 3; j < 6; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if(sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
        }

        if(counter2 > 1) {
            failed = true;
            return;
        }
        if(flag == false && counter == 9) {
            failed = true;
            return;
        }

        for(i = 3, counter = 0, counter2 = 0, flag = false; i < 6; i++) {
            for(j = 6; j < 9; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if(sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
        }

        if(counter2 > 1) {
            failed = true;
            return;
        }
        if(flag == false && counter == 9) {
            failed = true;
            return;
        }

        for(i = 6, counter = 0, counter2 = 0, flag = false; i < 9; i++) {
            for(j = 0; j < 3; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if(sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
        }

        if(counter2 > 1) {
            failed = true;
            return;
        }
        if(flag == false && counter == 9) {
            failed = true;
            return;
        }

        for(i = 6, counter = 0, counter2 = 0, flag = false; i < 9; i++) {
            for(j = 3; j < 6; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if(sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
        }

        if(counter2 > 1) {
            failed = true;
            return;
        }
        if(flag == false && counter == 9) {
            failed = true;
            return;
        }

        for(i = 6, counter = 0, counter2 = 0, flag = false; i < 9; i++) {
            for(j = 6; j < 9; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if(sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
        }

        if(counter2 > 1) {
            failed = true;
            return;
        }
        if(flag == false && counter == 9) {
            failed = true;
            return;
        }

        //If there are not any free spots in a row and the number in not contained in the row or if there are more than one instances
        //of that number in a row, then the solving process has failed.
        //It also works as a valid entry check.
        //The following code checks the 9 rows.
        for(i = 0, counter = 0, counter2 = 0, flag = false; i < sudoku_board[0].length; i++) {
            for(j = 0; j < sudoku_board[0][0].length; j++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if(sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
            if(counter2 > 1) {
                failed = true;
                return;
            }
            if(flag == false && counter == 9) {
                failed = true;
                return;
            }
            flag = false;
            counter = 0;
            counter2 = 0;
        }

        //If there are not any free spots in a column and the number in not contained in the column or if there are more than one instances
        //of that number in a column, then the solving process has failed.
        //It also works as a valid entry check.
        //The following code checks the 9 columns.
        for(j = 0, counter = 0, counter2 = 0, flag = false; j < sudoku_board[0][0].length; j++) {
            for(i = 0; i < sudoku_board[0].length; i++) {
                if(sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if(sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
            if(counter2 > 1) {
                failed = true;
                return;
            }
            if(flag == false && counter == 9) {
                failed = true;
                return;
            }
            flag = false;
            counter = 0;
            counter2 = 0;
        }

        //If there are 81 numbers in the board and the program reached that point of the code, means that the sudoku was solved.
        if(getCountAll() == NUMBER_COUNT) {
            solved = true;
        }

    }


    /**
     * The method, getBoard, returns a pointer to our board.
     * @return The board.
     */
    public char[][][] getBoard() {

        return sudoku_board;

    }

    /**
     * The method, getArrayList, returns a pointer to our list.
     * @return The list.
     */
    public ArrayList<NumberOrder> getArrayList() {

        return list;

    }

    /**
     * The method, isSolved, returns the status of the process.
     * @return True or False depending of the status.
     */
    public boolean isSolved() {

        return solved;

    }

    /**
     * The method, hasFailed, returns the status of the process.
     * @return True or False depending of the status.
     */
    public boolean hasFailed() {

        return failed;

    }

    /**
     * The method, hasStarted, returns the status of the process.
     * @return True or False depending of the status.
     */
    public boolean hasStarted() {

        return started;

    }

    /**
     * The method, setStartToTrue, sets the started status to True.
     */
    public void setStartToTrue() {

        this.started = true;

    }

    /**
     * The method, getCountAll, returns the count of numbers in the board.
     * @return The count of the numbers.
     */
    public int getCountAll() {
      int counter = 0;

        for(int i = 0; i < sudoku_board[0].length; i++) {
            for(int j = 0; j < sudoku_board[0].length; j++) {
                if(Character.isDigit(sudoku_board[0][i][j])) {
                    counter++;
                }
            }
        }
        return counter;

    }

    /**
     * The method, sort, sorts the list.
     */
    public void sort() {

        if(list.isEmpty()) {
            for(int i = 0; i < 9; i++) {
                NumberOrder Number = new NumberOrder((char)(49 + i), countNumber((char)(49 + i)));
                list.add(checkNumber(Number), Number);
            }
        }
        else {
            list.removeAll(list);
            for(int i = 0; i < 9; i++) {
                NumberOrder Number = new NumberOrder((char)(49 + i), countNumber((char)(49 + i)));
                list.add(checkNumber(Number), Number);
            }
        }

    }
    
}
