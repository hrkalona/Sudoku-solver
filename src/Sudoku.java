
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class defines a sudoku puzzle and also includes a solution method.
 *
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
     *
     * @param board The
     */
    public Sudoku(char[][] board) {

        sudoku_board = new char[10][9][9]; //10 9x9 boards, the original one and 9 temporary boards.
        sudoku_board[0] = board; //The first board points to the board that needs to be solved.

        list = new ArrayList<NumberOrder>(9); //Creating a 9 spot list.

        for (int i = 1; i < sudoku_board.length; i++) {
            for (int j = 0; j < sudoku_board[0].length; j++) {
                for (int k = 0; k < sudoku_board[0][0].length; k++) {
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

        for (int i = 0; i < sudoku_board[0].length; i++) {
            for (int j = 0; j < sudoku_board[0][0].length; j++) {
                if (sudoku_board[0][i][j] == which_number) {
                    count++;
                }
            }
        }
        return count;

    }

    /**
     * The method, solve, is solving only a number depending on its parameter.
     *
     * @param which_node Which node from the list will be solved, aka which
     * number.
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

        while (i < list.size()) {
            if (Number.getCount() > list.get(i).getCount()) { //find the spot
                return i;
            }
            i++;
        }

        return i;

    }

    private boolean checkForFailure(int numeric_value, char character_value) {

        int i, j;
        //If there are not any free spots in a block and the number is not contained in the block or if there are more than one instances
        //of that number in a block, then the solving process has failed.
        //It also works as a valid entry check.
        //The following code checks the 9 blocks.
        for (int blockx = 0; blockx < 3; blockx++) {

            for (int blocky = 0; blocky < 3; blocky++) {

                int counter = 0, counter2 = 0;
                boolean flag = false;
                for (i = blockx * 3; i < (blockx + 1) * 3; i++) {
                    for (j = blocky * 3; j < (blocky + 1) * 3; j++) {

                        if (sudoku_board[numeric_value][i][j] == character_value) {
                            flag = true;
                            counter2++;
                        }
                        if (sudoku_board[numeric_value][i][j] != ' ') {
                            counter++;
                        }
                    }
                }

                if (counter2 > 1) {
                    return true;
                }
                if (flag == false && counter == 9) {
                    return true;
                }
            }
        }

        //If there are not any free spots in a row and the number in not contained in the row or if there are more than one instances
        //of that number in a row, then the solving process has failed.
        //It also works as a valid entry check.
        //The following code checks the 9 rows.
        for (i = 0; i < sudoku_board[0].length; i++) {

            int counter = 0, counter2 = 0;
            boolean flag = false;

            for (j = 0; j < sudoku_board[0][0].length; j++) {
                if (sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if (sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
            if (counter2 > 1) {
                failed = true;
                return true;
            }
            if (flag == false && counter == 9) {
                failed = true;
                return true;
            }
        }

        //If there are not any free spots in a column and the number in not contained in the column or if there are more than one instances
        //of that number in a column, then the solving process has failed.
        //It also works as a valid entry check.
        //The following code checks the 9 columns.
        for (j = 0; j < sudoku_board[0][0].length; j++) {
            int counter = 0, counter2 = 0;
            boolean flag = false;
            
            for (i = 0; i < sudoku_board[0].length; i++) {
                if (sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                    counter2++;
                }
                if (sudoku_board[numeric_value][i][j] != ' ') {
                    counter++;
                }
            }
            if (counter2 > 1) {
                return true;
            }
            if (flag == false && counter == 9) {
                return true;
            }
        }
        
        return false;

    }

    /*
     * The method, xMap, contains the algorithms that solve a number.
     * Parameters: numeric_value <- The number in integer form, character_value <- The number in character form, list_node <- The node of the list.
     */
    private void xMap(int numeric_value, char character_value, int list_node) {

        filterBlocks(numeric_value, character_value);
        filterRows(numeric_value, character_value);
        filterColumns(numeric_value, character_value);

        if (!started) {
            return;
        }

        checkForPairsOrTripplets();
        eliminateRedundants();
        xWingColumn();
        xWingRow();
        handleOneTwoThreeEmptySpot(numeric_value, character_value);
        
        trySolving(numeric_value, character_value, list_node);

        failed = checkForFailure(numeric_value, character_value);

        //If there are 81 numbers in the board and the program reached that point of the code, means that the sudoku was solved.
        if (getCountAll() == NUMBER_COUNT) {
            solved = true;
        }

    }

    private boolean handleSameX(int x, String matched) {

        boolean replaced = false;
        for (int j = 0; j < sudoku_board[0][0].length; j++) {

            String numbersIn = "";
            for (int k = 1; k < sudoku_board.length; k++) {

                if (sudoku_board[k][x][j] == ' ') {
                    numbersIn += "" + k;
                }
            }

            if (!numbersIn.isEmpty() && !numbersIn.equals(matched)) {
                for (int digit = 0; digit < matched.length(); digit++) {
                    int id = Integer.parseInt("" + matched.charAt(digit));
                    if (sudoku_board[id][x][j] == ' ') {
                        sudoku_board[id][x][j] = 'X';
                        replaced = true;
                    }

                }

            }

        }

        return replaced;
    }

    private boolean handleSameXExcludingPoints(int x, String matched, ArrayList<Point> points) {

        boolean replaced = false;
        for (int j = 0; j < sudoku_board[0][0].length; j++) {

            String numbersIn = "";
            for (int k = 1; k < sudoku_board.length; k++) {

                if (sudoku_board[k][x][j] == ' ') {
                    numbersIn += "" + k;
                }
            }

            if (!numbersIn.isEmpty() && !numbersIn.equals(matched) && !points.contains(new Point(x, j))) {
                for (int digit = 0; digit < matched.length(); digit++) {
                    int id = Integer.parseInt("" + matched.charAt(digit));
                    if (sudoku_board[id][x][j] == ' ') {
                        sudoku_board[id][x][j] = 'X';
                        replaced = true;
                    }

                }

            }

        }

        return replaced;
    }

    private boolean handleSameY(int y, String matched) {

        boolean replaced = false;
        for (int i = 0; i < sudoku_board[0].length; i++) {

            String numbersIn = "";
            for (int k = 1; k < sudoku_board.length; k++) {

                if (sudoku_board[k][i][y] == ' ') {
                    numbersIn += "" + k;
                }
            }

            if (!numbersIn.isEmpty() && !numbersIn.equals(matched)) {
                for (int digit = 0; digit < matched.length(); digit++) {
                    int id = Integer.parseInt("" + matched.charAt(digit));
                    if (sudoku_board[id][i][y] == ' ') {
                        sudoku_board[id][i][y] = 'X';
                        replaced = true;
                    }

                }

            }

        }

        return replaced;
    }

    private boolean handleSameYExcludingPoints(int y, String matched, ArrayList<Point> points) {

        boolean replaced = false;
        for (int i = 0; i < sudoku_board[0].length; i++) {

            String numbersIn = "";
            for (int k = 1; k < sudoku_board.length; k++) {

                if (sudoku_board[k][i][y] == ' ') {
                    numbersIn += "" + k;
                }
            }

            if (!numbersIn.isEmpty() && !numbersIn.equals(matched) && !points.contains(new Point(i, y))) {
                for (int digit = 0; digit < matched.length(); digit++) {
                    int id = Integer.parseInt("" + matched.charAt(digit));
                    if (sudoku_board[id][i][y] == ' ') {
                        sudoku_board[id][i][y] = 'X';
                        replaced = true;
                    }

                }

            }

        }

        return replaced;
    }

    private void xWingColumn() {

        int i, j;

        for (int k = 1; k < sudoku_board.length; k++) {
            for (j = 0; j < sudoku_board[0][0].length; j++) {

                ArrayList<Point> points = new ArrayList<>();
                for (i = 0; i < sudoku_board[0].length; i++) {

                    if (sudoku_board[k][i][j] == ' ') {
                        points.add(new Point(i, j));
                    }
                }

                if (points.size() == 2) {
                    checkForXWingColumnSecondCandidate(k, j, points);
                }
            }
        }
    }

    private void xWingRow() {
        int i, j;

        for (int k = 1; k < sudoku_board.length; k++) {
            for (i = 0; i < sudoku_board[0].length; i++) {

                ArrayList<Point> points = new ArrayList<>();
                for (j = 0; j < sudoku_board[0][0].length; j++) {

                    if (sudoku_board[k][i][j] == ' ') {
                        points.add(new Point(i, j));
                    }
                }

                if (points.size() == 2) {
                    checkForXWingRowSecondCandidate(k, i, points);
                }
            }
        }

    }

    private boolean checkForXWingColumnSecondCandidate(int k, int initialColumn, ArrayList<Point> InitialPoints) {

        boolean replaced = false;
        int i, j;
        for (j = 0; j < sudoku_board[0][0].length; j++) {

            if (j == initialColumn) {
                continue;
            }

            ArrayList<Point> points = new ArrayList<>();
            for (i = 0; i < sudoku_board[0].length; i++) {

                if (sudoku_board[k][i][j] == ' ') {
                    points.add(new Point(i, j));
                }
            }

            if (points.size() == 2) {
                if (points.get(0).getX() == InitialPoints.get(0).getX()
                        && points.get(1).getX() == InitialPoints.get(1).getX()) {

                    for (int column = 0; column < sudoku_board[0][0].length; column++) {
                        if (column == initialColumn || column == j) {
                            continue;
                        }

                        if (sudoku_board[k][(int) points.get(0).getX()][column] == ' ') {
                            sudoku_board[k][(int) points.get(0).getX()][column] = 'X';
                            replaced = true;
                        }

                        if (sudoku_board[k][(int) points.get(1).getX()][column] == ' ') {
                            sudoku_board[k][(int) points.get(1).getX()][column] = 'X';
                            replaced = true;
                        }

                    }

                }
            }
        }

        return replaced;

    }

    private boolean checkForXWingRowSecondCandidate(int k, int initialRow, ArrayList<Point> InitialPoints) {

        boolean replaced = false;
        int i, j;
        for (i = 0; i < sudoku_board[0].length; i++) {

            if (i == initialRow) {
                continue;
            }

            ArrayList<Point> points = new ArrayList<>();
            for (j = 0; j < sudoku_board[0][0].length; j++) {

                if (sudoku_board[k][i][j] == ' ') {
                    points.add(new Point(i, j));
                }
            }

            if (points.size() == 2) {
                if (points.get(0).getY() == InitialPoints.get(0).getY()
                        && points.get(1).getY() == InitialPoints.get(1).getY()) {

                    for (int row = 0; row < sudoku_board[0].length; row++) {
                        if (row == initialRow || row == i) {
                            continue;
                        }

                        if (sudoku_board[k][row][(int) points.get(0).getY()] == ' ') {
                            sudoku_board[k][row][(int) points.get(0).getY()] = 'X';
                            replaced = true;
                        }

                        if (sudoku_board[k][row][(int) points.get(1).getY()] == ' ') {
                            sudoku_board[k][row][(int) points.get(1).getY()] = 'X';
                            replaced = true;
                        }

                    }

                }
            }
        }

        return replaced;

    }

    private boolean check_for_same_points_block(int blockx, int blocky, int initialK, ArrayList<Point> initialPoints) {

        int i, j;

        boolean replaced = false;

        for (int k = 1; k < sudoku_board.length; k++) {

            if (k == initialK) {
                continue;
            }

            ArrayList<Point> points = new ArrayList<>();
            for (i = blockx * 3; i < (blockx + 1) * 3; i++) {
                for (j = blocky * 3; j < (blocky + 1) * 3; j++) {

                    if (sudoku_board[k][i][j] == ' ') {
                        points.add(new Point(i, j));
                    }
                }

            }

            if (points.size() == 2) {

                if (points.get(0).equals(initialPoints.get(0))
                        && points.get(1).equals(initialPoints.get(1))) {

                    for (int id = 1; id < sudoku_board.length; id++) {
                        if (id == k || id == initialK) {
                            continue;
                        }

                        if (sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] == ' ') {
                            sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] = 'X';
                            replaced = true;
                        }

                        if (sudoku_board[id][(int) points.get(1).getX()][(int) points.get(1).getY()] == ' ') {
                            sudoku_board[id][(int) points.get(1).getX()][(int) points.get(1).getY()] = 'X';
                            replaced = true;
                        }
                    }
                }
            }
        }

        return replaced;

    }

    private boolean check_for_same_points_row(int row, int initialK, ArrayList<Point> initialPoints) {

        int j;

        boolean replaced = false;

        for (int k = 1; k < sudoku_board.length; k++) {

            if (k == initialK) {
                continue;
            }

            ArrayList<Point> points = new ArrayList<>();

            for (j = 0; j < sudoku_board[0][0].length; j++) {
                if (sudoku_board[k][row][j] == ' ') {
                    points.add(new Point(row, j));
                }
            }

            if (points.size() == 2) {

                if (points.get(0).equals(initialPoints.get(0))
                        && points.get(1).equals(initialPoints.get(1))) {

                    for (int id = 1; id < sudoku_board.length; id++) {
                        if (id == k || id == initialK) {
                            continue;
                        }

                        if (sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] == ' ') {
                            sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] = 'X';
                            replaced = true;
                        }

                        if (sudoku_board[id][(int) points.get(1).getX()][(int) points.get(1).getY()] == ' ') {
                            sudoku_board[id][(int) points.get(1).getX()][(int) points.get(1).getY()] = 'X';
                            replaced = true;
                        }
                    }
                }
            }

        }

        return replaced;

    }

    private boolean check_for_same_points_column(int column, int initialK, ArrayList<Point> initialPoints) {

        int i;

        boolean replaced = false;

        for (int k = 1; k < sudoku_board.length; k++) {

            if (k == initialK) {
                continue;
            }

            ArrayList<Point> points = new ArrayList<>();

            for (i = 0; i < sudoku_board[0].length; i++) {
                if (sudoku_board[k][i][column] == ' ') {
                    points.add(new Point(i, column));
                }
            }

            if (points.size() == 2) {

                if (points.get(0).equals(initialPoints.get(0))
                        && points.get(1).equals(initialPoints.get(1))) {

                    for (int id = 1; id < sudoku_board.length; id++) {
                        if (id == k || id == initialK) {
                            continue;
                        }

                        if (sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] == ' ') {
                            sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] = 'X';
                            replaced = true;
                        }

                        if (sudoku_board[id][(int) points.get(1).getX()][(int) points.get(1).getY()] == ' ') {
                            sudoku_board[id][(int) points.get(1).getX()][(int) points.get(1).getY()] = 'X';
                            replaced = true;
                        }
                    }
                }
            }

        }

        return replaced;

    }

    private void eliminateRedundants() {
        int i, j;

        //If a pair can only be placed only on two cells
        //or a single can b only place only on one cell
        //Eliminate all the rest options, if any, from those cell(s)
        
        //Scan blocks
        for (int blockx = 0; blockx < 3; blockx++) {

            for (int blocky = 0; blocky < 3; blocky++) {

                for (int k = 1; k < sudoku_board.length; k++) {
                    ArrayList<Point> points = new ArrayList<>();
                    for (i = blockx * 3; i < (blockx + 1) * 3; i++) {
                        for (j = blocky * 3; j < (blocky + 1) * 3; j++) {

                            if (sudoku_board[k][i][j] == ' ') {
                                points.add(new Point(i, j));
                            }
                        }

                    }

                    if (points.size() == 2) {
                        check_for_same_points_block(blockx, blocky, k, points);
                    } else if (points.size() == 1) {
                        for (int id = 1; id < sudoku_board.length; id++) {
                            if (id == k) {
                                continue;
                            }

                            if (sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] == ' ') {
                                sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] = 'X';
                            }
                        }
                    }
                }

            }
        }

        //rows
        for (int k = 1; k < sudoku_board.length; k++) {

            for (i = 0; i < sudoku_board[0].length; i++) {

                ArrayList<Point> points = new ArrayList<>();
                for (j = 0; j < sudoku_board[0][0].length; j++) {
                    if (sudoku_board[k][i][j] == ' ') {
                        points.add(new Point(i, j));
                    }
                }

                if (points.size() == 2) {
                    check_for_same_points_row(i, k, points);
                } else if (points.size() == 1) {
                    for (int id = 1; id < sudoku_board.length; id++) {
                        if (id == k) {
                            continue;
                        }

                        if (sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] == ' ') {
                            sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] = 'X';
                        }
                    }
                }
            }

        }

        //columns
        for (int k = 1; k < sudoku_board.length; k++) {

            for (j = 0; j < sudoku_board[0][0].length; j++) {
                ArrayList<Point> points = new ArrayList<>();
                for (i = 0; i < sudoku_board[0].length; i++) {
                    if (sudoku_board[k][i][j] == ' ') {
                        points.add(new Point(i, j));
                    }
                }
                if (points.size() == 2) {
                    check_for_same_points_column(j, k, points);
                } else if (points.size() == 1) {
                    for (int id = 1; id < sudoku_board.length; id++) {
                        if (id == k) {
                            continue;
                        }

                        if (sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] == ' ') {
                            sudoku_board[id][(int) points.get(0).getX()][(int) points.get(0).getY()] = 'X';
                        }
                    }
                }
            }

        }

    }

    private void checkForPairsOrTripplets() {
        int i, j;

        //Scan blocks
        for (int blockx = 0; blockx < 3; blockx++) {

            for (int blocky = 0; blocky < 3; blocky++) {
                ArrayList<String> numbersList = new ArrayList<>();
                String matched = "";
                for (i = blockx * 3; i < (blockx + 1) * 3; i++) {
                    for (j = blocky * 3; j < (blocky + 1) * 3; j++) {

                        String numbers = "";
                        for (int k = 1; k < sudoku_board.length; k++) {

                            if (sudoku_board[k][i][j] == ' ') {
                                numbers += "" + k;
                            }
                        }

                        if (!numbers.isEmpty()) {
                            numbersList.add(numbers);
                            if (numbers.length() == 2 && Collections.frequency(numbersList, numbers) == 2) { //pair
                                matched = numbers;
                                break;
                            } else if (numbers.length() == 3 && Collections.frequency(numbersList, numbers) == 3) { //tripplet
                                matched = numbers;
                                break;
                            }
                        }
                    }

                    if (!matched.isEmpty()) {
                        break;
                    }
                }

                if (!matched.isEmpty()) {

                    ArrayList<Point> points = new ArrayList<>();

                    for (i = blockx * 3; i < (blockx + 1) * 3; i++) {
                        for (j = blocky * 3; j < (blocky + 1) * 3; j++) {

                            String numbers = "";
                            for (int k = 1; k < sudoku_board.length; k++) {

                                if (sudoku_board[k][i][j] == ' ') {
                                    numbers += "" + k;
                                }
                            }

                            if (!numbers.isEmpty() && !numbers.equals(matched)) {
                                for (int digit = 0; digit < matched.length(); digit++) {
                                    int id = Integer.parseInt("" + matched.charAt(digit));
                                    if (sudoku_board[id][i][j] == ' ') {
                                        sudoku_board[id][i][j] = 'X';
                                    }

                                }

                            } else if (numbers.equals(matched)) {
                                points.add(new Point(i, j));
                            }
                        }
                    }

                    if (points.size() == 2 && !points.get(0).equals(points.get(1))) {

                        boolean sameX = points.get(0).getX() == points.get(1).getX();
                        boolean sameY = points.get(0).getY() == points.get(1).getY();

                        if (sameX) {
                            handleSameX((int) points.get(0).getX(), matched);
                        }

                        if (sameY) {
                            handleSameY((int) points.get(0).getY(), matched);
                        }
                    } else if (points.size() == 3 && !points.get(0).equals(points.get(1)) && !points.get(1).equals(points.get(2))) {

                        boolean sameX = points.get(0).getX() == points.get(1).getX() && points.get(1).getX() == points.get(2).getX();
                        boolean sameY = points.get(0).getY() == points.get(1).getY() && points.get(1).getY() == points.get(2).getY();

                        if (sameX) {
                            handleSameX((int) points.get(0).getX(), matched);
                        }

                        if (sameY) {
                            handleSameY((int) points.get(0).getY(), matched);
                        }
                    } else {
                        System.out.println("ERROR!!");
                        System.exit(1);
                    }

                }
            }
        }

        //rows
        for (i = 0; i < sudoku_board[0].length; i++) {

            ArrayList<String> numbersList = new ArrayList<>();
            String matched = "";
            for (j = 0; j < sudoku_board[0][0].length; j++) {
                String numbers = "";
                for (int k = 1; k < sudoku_board.length; k++) {

                    if (sudoku_board[k][i][j] == ' ') {
                        numbers += "" + k;
                    }
                }

                if (!numbers.isEmpty()) {
                    numbersList.add(numbers);
                    if (numbers.length() == 2 && Collections.frequency(numbersList, numbers) == 2) { //pair
                        matched = numbers;
                        break;
                    } else if (numbers.length() == 3 && Collections.frequency(numbersList, numbers) == 3) { //tripplet
                        matched = numbers;
                        break;
                    }
                }
            }

            if (!matched.isEmpty()) {

                for (j = 0; j < sudoku_board[0][0].length; j++) {

                    String numbers = "";
                    for (int k = 1; k < sudoku_board.length; k++) {

                        if (sudoku_board[k][i][j] == ' ') {
                            numbers += "" + k;
                        }
                    }

                    if (!numbers.isEmpty() && !numbers.equals(matched)) {
                        for (int digit = 0; digit < matched.length(); digit++) {
                            int id = Integer.parseInt("" + matched.charAt(digit));
                            if (sudoku_board[id][i][j] == ' ') {
                                sudoku_board[id][i][j] = 'X';
                            }

                        }
                    }
                }

            }
        }

        //columns
        for (j = 0; j < sudoku_board[0][0].length; j++) {

            ArrayList<String> numbersList = new ArrayList<>();
            String matched = "";

            for (i = 0; i < sudoku_board[0].length; i++) {
                String numbers = "";
                for (int k = 1; k < sudoku_board.length; k++) {

                    if (sudoku_board[k][i][j] == ' ') {
                        numbers += "" + k;
                    }
                }

                if (!numbers.isEmpty()) {
                    numbersList.add(numbers);
                    if (numbers.length() == 2 && Collections.frequency(numbersList, numbers) == 2) { //pair
                        matched = numbers;
                        break;
                    } else if (numbers.length() == 3 && Collections.frequency(numbersList, numbers) == 3) { //tripplet
                        matched = numbers;
                        break;
                    }
                }
            }

            if (!matched.isEmpty()) {

                for (i = 0; i < sudoku_board[0].length; i++) {

                    String numbers = "";
                    for (int k = 1; k < sudoku_board.length; k++) {

                        if (sudoku_board[k][i][j] == ' ') {
                            numbers += "" + k;
                        }
                    }

                    if (!numbers.isEmpty() && !numbers.equals(matched)) {
                        for (int digit = 0; digit < matched.length(); digit++) {
                            int id = Integer.parseInt("" + matched.charAt(digit));
                            if (sudoku_board[id][i][j] == ' ') {
                                sudoku_board[id][i][j] = 'X';
                            }

                        }

                    }
                }

            }
        }

    }

    private void handleOneTwoThreeEmptySpot(int numeric_value, char character_value) {

        int i, j;

        for (int blockx = 0; blockx < 3; blockx++) {

            for (int blocky = 0; blocky < 3; blocky++) {
                ArrayList<Point> points = new ArrayList<>();
                for (i = blockx * 3; i < (blockx + 1) * 3; i++) {
                    for (j = blocky * 3; j < (blocky + 1) * 3; j++) {
                        if (sudoku_board[numeric_value][i][j] == ' ') {
                            points.add(new Point(i, j));
                        }
                    }
                }

                if (points.size() == 1) {
                    handleSameXExcludingPoints((int) points.get(0).getX(), "" + character_value, points);
                    handleSameYExcludingPoints((int) points.get(0).getY(), "" + character_value, points);
                } else if (points.size() == 2 && !points.get(0).equals(points.get(1))) {

                    boolean sameX = points.get(0).getX() == points.get(1).getX();
                    boolean sameY = points.get(0).getY() == points.get(1).getY();

                    if (sameX) {
                        handleSameXExcludingPoints((int) points.get(0).getX(), "" + character_value, points);
                    }

                    if (sameY) {
                        handleSameYExcludingPoints((int) points.get(0).getY(), "" + character_value, points);
                    }
                } else if (points.size() == 3 && !points.get(0).equals(points.get(1)) && !points.get(1).equals(points.get(2))) {

                    boolean sameX = points.get(0).getX() == points.get(1).getX() && points.get(1).getX() == points.get(2).getX();
                    boolean sameY = points.get(0).getY() == points.get(1).getY() && points.get(1).getY() == points.get(2).getY();

                    if (sameX) {
                        handleSameXExcludingPoints((int) points.get(0).getX(), "" + character_value, points);
                    }

                    if (sameY) {
                        handleSameYExcludingPoints((int) points.get(0).getY(), "" + character_value, points);
                    }
                }

            }
        }

    }

    private void trySolving(int numeric_value, char character_value, int list_node) {

        int i, j;

        boolean updated = false;

        //Check blocks if they have one empty spot
        for (int blockx = 0; blockx < 3; blockx++) {

            for (int blocky = 0; blocky < 3; blocky++) {

                ArrayList<Point> points = new ArrayList<>();
                for (i = blockx * 3; i < (blockx + 1) * 3; i++) {
                    for (j = blocky * 3; j < (blocky + 1) * 3; j++) {
                        if (sudoku_board[numeric_value][i][j] == ' ') {
                            points.add(new Point(i, j));
                        }
                    }
                }

                if (points.size() == 1) {
                    for (int k = 0; k < sudoku_board.length; k++) {
                        sudoku_board[k][(int) points.get(0).getX()][(int) points.get(0).getY()] = character_value;
                    }
                    list.get(list_node).updateCount();
                    updated = true;
                    break;
                }

            }

            if (updated) {
                break;
            }
        }

        if (updated) {
            filterBlocks(numeric_value, character_value);
            filterRows(numeric_value, character_value);
            filterColumns(numeric_value, character_value);
            trySolving(numeric_value, character_value, list_node);
        }

        updated = false;
        //If there is only 1 free spot in a row then the number should be placed there and all the boards need to be updated,
        //the count of that number needs to be updated, and the whole process needs to started again with recursion.
        //The following codes does the above.
        for (i = 0; i < sudoku_board[0].length; i++) {

            ArrayList<Point> points = new ArrayList<>();
            for (j = 0; j < sudoku_board[0][0].length; j++) {
                if (sudoku_board[numeric_value][i][j] == ' ') {
                    points.add(new Point(i, j));
                }
            }

            if (points.size() == 1) {
                for (int k = 0; k < sudoku_board.length; k++) {
                    sudoku_board[k][(int) points.get(0).getX()][(int) points.get(0).getY()] = character_value;
                }
                list.get(list_node).updateCount();
                updated = true;
                break;
            }
        }

        if (updated) {
            filterBlocks(numeric_value, character_value);
            filterRows(numeric_value, character_value);
            filterColumns(numeric_value, character_value);
            trySolving(numeric_value, character_value, list_node);
        }

        updated = false;
        //If there is only 1 free spot in a column then the number should be placed there and all the boards need to be updated,
        //the count of that number needs to be updated, and the whole process needs to started again with recursion.
        //The following codes does the above.
        for (j = 0; j < sudoku_board[0][0].length; j++) {

            ArrayList<Point> points = new ArrayList<>();
            for (i = 0; i < sudoku_board[0].length; i++) {
                if (sudoku_board[numeric_value][i][j] == ' ') {
                    points.add(new Point(i, j));
                }
            }

            if (points.size() == 1) {
                for (int k = 0; k < sudoku_board.length; k++) {
                    sudoku_board[k][(int) points.get(0).getX()][(int) points.get(0).getY()] = character_value;
                }
                list.get(list_node).updateCount();
                updated = true;
                break;
            }
        }

        if (updated) {
            filterBlocks(numeric_value, character_value);
            filterRows(numeric_value, character_value);
            filterColumns(numeric_value, character_value);
            trySolving(numeric_value, character_value, list_node);
        }

        updated = false;

        //Check if for a particular cell, there only one available number to be added
        for (i = 0; i < sudoku_board[0].length; i++) {
            for (j = 0; j < sudoku_board[0][0].length; j++) {
                if (sudoku_board[numeric_value][i][j] == ' ') {
                    int counter = 0;
                    for (int k = 1; k < sudoku_board.length; k++) {
                        if (sudoku_board[k][i][j] == ' ') {
                            counter++;
                        }
                    }
                    if (counter == 1) {
                        for (int k = 0; k < sudoku_board.length; k++) {
                            sudoku_board[k][i][j] = character_value;
                        }
                        list.get(list_node).updateCount();
                        updated = true;
                        break;
                    }
                }
            }

            if (updated) {
                break;
            }
        }

        if (updated) {
            filterBlocks(numeric_value, character_value);
            filterRows(numeric_value, character_value);
            filterColumns(numeric_value, character_value);
            trySolving(numeric_value, character_value, list_node);
        }
    }

    private void filterRows(int numeric_value, char character_value) {

        boolean flag = false;
        int i, j;

        //If a row contains that number, then the free cells of the row need to be eliminated by placing a X in the free cells.
        //The following code checks the 9 rows.
        for (i = 0, flag = false; i < sudoku_board[0].length; i++) {
            for (j = 0; j < sudoku_board[0][0].length; j++) {
                if (sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                }
            }
            if (flag == true) {
                for (j = 0; j < sudoku_board[0][0].length; j++) {
                    if (!Character.isDigit(sudoku_board[numeric_value][i][j]) && sudoku_board[numeric_value][i][j] != 'X') {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
                flag = false;
            }
        }

    }

    private void filterColumns(int numeric_value, char character_value) {

        boolean flag = false;
        int i, j;

        //If a column contains that number, then the free cells of the column need to be eliminated by placing a X in the free cells.
        //The following code checks the 9 columns.
        for (j = 0; j < sudoku_board[0][0].length; j++) {
            for (i = 0; i < sudoku_board[0].length; i++) {
                if (sudoku_board[numeric_value][i][j] == character_value) {
                    flag = true;
                }
            }
            if (flag == true) {
                for (i = 0; i < sudoku_board[0].length; i++) {
                    if (!Character.isDigit(sudoku_board[numeric_value][i][j]) && sudoku_board[numeric_value][i][j] != 'X') {
                        sudoku_board[numeric_value][i][j] = 'X';
                    }
                }
                flag = false;
            }
        }

    }

    private void filterBlocks(int numeric_value, char character_value) {

        //If a 3x3 block contains that number, then the free cells of the block need to be eliminated by placing a X in the free cells.
        //The following code checks the 9 blocks.
        int i, j;
        for (int blockx = 0; blockx < 3; blockx++) {

            for (int blocky = 0; blocky < 3; blocky++) {

                boolean flag = false;
                for (i = blockx * 3; i < (blockx + 1) * 3; i++) {
                    for (j = blocky * 3; j < (blocky + 1) * 3; j++) {
                        if (sudoku_board[numeric_value][i][j] == character_value) {
                            flag = true;
                            break;
                        }
                    }
                }

                if (flag == true) {
                    for (i = blockx * 3; i < (blockx + 1) * 3; i++) {
                        for (j = blocky * 3; j < (blocky + 1) * 3; j++) {
                            if (!Character.isDigit(sudoku_board[numeric_value][i][j])) {
                                sudoku_board[numeric_value][i][j] = 'X';
                            }
                        }
                    }
                }

            }

        }

    }

    /**
     * The method, getBoard, returns a pointer to our board.
     *
     * @return The board.
     */
    public char[][][] getBoard() {

        return sudoku_board;

    }

    /**
     * The method, getArrayList, returns a pointer to our list.
     *
     * @return The list.
     */
    public ArrayList<NumberOrder> getArrayList() {

        return list;

    }

    /**
     * The method, isSolved, returns the status of the process.
     *
     * @return True or False depending of the status.
     */
    public boolean isSolved() {

        return solved;

    }

    /**
     * The method, hasFailed, returns the status of the process.
     *
     * @return True or False depending of the status.
     */
    public boolean hasFailed() {

        return failed;

    }

    /**
     * The method, hasStarted, returns the status of the process.
     *
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
     *
     * @return The count of the numbers.
     */
    public int getCountAll() {
        int counter = 0;

        for (int i = 0; i < sudoku_board[0].length; i++) {
            for (int j = 0; j < sudoku_board[0].length; j++) {
                if (Character.isDigit(sudoku_board[0][i][j])) {
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

        if (list.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                NumberOrder Number = new NumberOrder((char) (49 + i), countNumber((char) (49 + i)));
                list.add(checkNumber(Number), Number);
            }
        } else {
            list.removeAll(list);
            for (int i = 0; i < 9; i++) {
                NumberOrder Number = new NumberOrder((char) (49 + i), countNumber((char) (49 + i)));
                list.add(checkNumber(Number), Number);
            }
        }

    }

}
