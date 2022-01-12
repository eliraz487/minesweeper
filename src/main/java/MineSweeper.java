
import java.util.ArrayList;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user
 */
public class MineSweeper {

    private int[][] bordgame;
    private boolean[][] bord;
    private final int MAXROW = 10;
    private final int MAXCOL = 10;
    private final int MAXMINES = 10;

    public MineSweeper(int row, int col, int minecount) {
        isVaulidtions(row, col, minecount);
        createGame(row, col, minecount);
        startGame(row, col, minecount);
    }

    private void isVaulidtions(int row, int col, int minecount) {
    }

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

    private int getHowManyMines(int row, int col) {

        ArrayList<Integer> nearsfieds = new ArrayList<>();
        nearsfieds = getNearsFieds(row, col);
        return nearsfieds.size();
    }

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

    public void pressClick(int row, int col) {
        if (bord[row][col]) {
            finshed();
            return;
        }
        open(row, col);
    }

    private void finshed() {
    }

    private void open(int row, int col) {
        if (bord[row][col]) {
            return ;
        }
        if (bordgame[row][col] != -1) {
            return;
        }
        bordgame[row][col] = getHowManyMines(row, col);
        if (bordgame[row][col] == 0) {
            openEmptyFieds(row, col);
        }
    }

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

    public int[][] getBordgame() {
        return bordgame;
    }
    
}
