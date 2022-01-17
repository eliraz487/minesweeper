
import java.util.ArrayList;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user eliraz pinhasi
 */
public class MineSweeper {

    private int[][] bordgame;//matrix are view the game by int
    private boolean[][] bord; // matrix are use to said if in the locition in the bord game is a mine or not 
    private final int MAXROW = 10;//the maximum rows in the bord game
    private final int MAXCOL = 10;//the maximum columns in the bord game
    private final int MINROW = 3;//the minimum rows in the bord game
    private final int MINCOL = 3;//the minimum columns in the bord game
    private boolean iswin = false;

    /**
     * the function create the game by the logic
     *
     * the bord game size will be row*col
     *
     * the maximum mines in the bord is half of the bord size
     *
     * @param row the size of the row will be in the bord game
     * @param col the size of the columns will be in the bord game
     * @param minecount the number of the mine will be in the game
     */
    public MineSweeper(int row, int col, int minecount) throws Exception {
        isVaulidtions(row, col, minecount);
        createGame(row, col, minecount);
        startGame(row, col, minecount);
    }

    /**
     * the function check if the paramters row col and mine are Vaulidtions row
     * will be Vaulid if he bigger than MAXROW and smaller than MINROW col will
     * be Vaulid if he bigger than MAXCOL and smaller than MINCOL minecount will
     * be Vaulid if he bigger than 3 and smaller than row*col/4
     *
     * @param row the size of the row will be in the bord game
     * @param col the size of the columns will be in the bord game
     * @param minecount the number of the mine will be in the game
     */
    private void isVaulidtions(int row, int col, int minecount) throws Exception {
        boolean isvaulidtions = true;
        String problem = "";
        if (row > MAXROW) {
            isvaulidtions = false;
            problem = problem + "the number of the row is too big (" + row + ")";
        }
        if (col > MAXCOL) {
            isvaulidtions = false;
            problem = problem + "\n" + "the number of the columns is too big (" + col + ")";
        }
        if (row < MINROW) {
            isvaulidtions = false;
            problem = problem + "\n" + "the number of the rows is too big (" + row + ")";

        }
        if (col < MINCOL) {
            isvaulidtions = false;
            problem = problem + "\n" + "the number of the columns is too big (" + col + ")";
        }
        if (!isvaulidtions) {
            throw new Exception(problem);
        }
    }

    /**
     * the fuction create the matrices that describe the game board
     *
     * @param row the size of the row will be in the bord game
     * @param col the size of the columns will be in the bord game
     * @param minecount the number of the mine will be in the game
     */
    private void createGame(int row, int col, int minecount) {
        bord = new boolean[row][col];
        bordgame = new int[row][col];
        for (int temp1 = 0; temp1 < bord.length; temp1++) {
            for (int temp2 = 0; temp2 < bord[0].length; temp2++) {
                bord[temp1][temp2] = false;
                bordgame[temp1][temp2] = -1;
            }
        }
    }

    /**
     * the function start the game put a mine in Matricesu(true in bord and -99
     * in bord game)
     *
     * @param row the size of the row will be in the bord game
     * @param col the size of the columns will be in the bord game
     * @param minecount the number of the mine will be in the game
     */
    private void startGame(int row, int col, int minecount) {
        Random random = new Random();
        while (minecount > 0) {
            int r = random.nextInt(bord.length);
            int c = random.nextInt(bord[0].length);
            if (bord[r][c] == true) {
                continue;
            }
            bord[r][c] = true;
            minecount--;
        }
    }

    /**
     * the function said how many mine sround the squrl in locition(row,col)
     *
     * @param row the row index of the squrl
     * @param col the column index of the squrl
     * @return how many mine sround the squrl
     */
    private int getHowManyMines(int row, int col) {

        ArrayList<Integer> nearsfieds = new ArrayList<>();
        nearsfieds = getNearsFieds(row, col);
        return nearsfieds.size();
    }

    /**
     * the function check the squrls sround the squrl in index(row,col)
     *
     * @param row the row index of the squrl
     * @param col the column index of the squrl
     * @return array of the squrl are mines
     */
    private ArrayList<Integer> getNearsFieds(int row, int col) {
        ArrayList<Integer> nearsfieds = new ArrayList<>();
        for (int temp1 = -1; temp1 < 2; temp1++) {
            if (temp1 == -1 && row == 0) {
                continue;
            }
            if (temp1 == 1 && row == bord.length - 1) {
                continue;
            }
            for (int temp2 = -1; temp2 < 2; temp2++) {
                if (temp2 == -1 && col == 0) {
                    continue;
                }
                if (temp2 == 1 && col == bord[0].length - 1) {
                    continue;
                }
                if (temp1 == 0 && temp2 == 0) {
                    continue;
                }
                if (bord[row + temp1][col + temp2]) {
                    nearsfieds.add(-99);
                }

            }
        }

        return nearsfieds;
    }

    /**
     * the function get the loction of the squrl the user want to press
     *
     * @param row the row index of the squrl
     * @param col the column index of the squrl
     */
    public void pressClick(int row, int col) {
        if (bord[row][col]) {
            finshed();
            return;
        }
        open(row, col);
    }

    /**
     * the function finshed the game by show all the squrl
     */
    private void finshed() {
        if (iswin) {
            return;
        }
        for (int rowindex = 0; rowindex < bordgame.length; rowindex++) {
            for (int colindex = 0; colindex < bordgame[0].length; colindex++) {
                bordgame[rowindex][colindex]=getHowManyMines(rowindex, colindex);
            }
        }

    }

    /**
     * the function show in the squrl in index(row,col) in the bordgame how many
     * mines sround it
     *
     * @param row the row index of the squrl
     * @param col the column index of the squrl
     */
    private void open(int row, int col) {
        if (isWin()) {
            finshed();
            return;
        }
        if (bord[row][col]) {
            return;
        }
        if (bordgame[row][col] != -1) {
            return;
        }
        bordgame[row][col] = getHowManyMines(row, col);
        if (bordgame[row][col] == 0) {
            openEmptyFieds(row, col);
        }
    }

    /**
     * the function open all the squrls sround the squrl in index(row,col)
     *
     * @param row the row index of the squrl
     * @param col the column index of the squrl
     */
    private void openEmptyFieds(int row, int col) {
        for (int temp1 = -1; temp1 < 2; temp1++) {
            if (temp1 == -1 && row == 0) {
                continue;
            }
            if (temp1 == 1 && row == bord.length - 1) {
                continue;
            }
            for (int temp2 = -1; temp2 < 2; temp2++) {
                if (temp2 == -1 && col == 0) {
                    continue;
                }
                if (temp2 == 1 && col == bord[0].length - 1) {
                    continue;
                }
                if (temp1 == 0 && temp2 == 0) {
                    continue;
                }
                open(row + temp1, col + temp2);

            }
        }
    }

    /**
     * geter of the array bordgame
     *
     * @return the array bordgame
     */
    public int[][] getBordgame() {
        return bordgame;
    }

    /**
     * the function check if the player win
     * the player win if he open all the squrl there not mine
     * @return if the player win
     */
    private boolean isWin() {
        for (int rowindex = 0; rowindex < bordgame.length; rowindex++) {
            for (int colindex = 0; colindex < bordgame[0].length; colindex++) {
                if (bordgame[rowindex][colindex] == -1) {
                    return false;
                }
            }
        }
        iswin = true;
        return iswin;
    }

}
