package game;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static char[][] board = new char[9][9];


    public static void main(String[] args) {

        System.out.println(isValidSudoku(getBoard()));
    }

    public static boolean isValidSudoku(char[][] board) {
        for (char[] row : board){
            if (!isValid(row)){
                return false;
            }
        }
        return checkColumn(board) && checkSub_Boxes(board);
    }

    private static boolean isValid(char[] row){
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char element: row){
            if (element > '0' && element <= '9'){
                frequencies.computeIfPresent(element, (k,v)-> ++v);
                frequencies.putIfAbsent(element, 1);
            }
        }
        for (int i : frequencies.values()){
            if (i > 1){
                return false;
            }
        }
        return true;
    }

    private static boolean checkSub_Boxes(char[][] board){
        int startRow = 0;
        int finishRow = 3;
        while(finishRow<=9){
            int startCol = 0;
            int finishCol = 3;

            while (finishCol <=9){
                char[] box = new char[9];
                int count = 0;

                for (int i = startRow; i < finishRow; i++) {
                    for (int j = startCol; j < finishCol; j++) {
                        box[count++] = board[i][j];
                    }
                }
                if (!isValid(box)){
                    return false;
                }
                startCol+=3;
                finishCol+=3;
            }
            startRow+=3;
            finishRow+=3;
        }
        return true;
    }

    private static boolean checkColumn(char[][] board){
        for (int j = 0; j < board.length; j++) {
            char[] column = new char[9];
            int count = 0;
            for (int i = 0; i < board.length; i++) {
                column[count++] = board[i][j];
            }
            if (!isValid(column)){
                return false;
            }
        }
        return true;
    }

    private static char[][] getBoard(){
        char[][] board = new char[9][9];
        String input = "" +
                "0, 0, 0, 0, 0, 0, 5, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "9, 3, 0, 0, 2, 0, 4, 0, 0\n" +
                "0, 0, 7, 0, 0, 0, 3, 0, 0\n" +
                "0, 0, 0, 0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 3, 4, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 3, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 5, 2, 0, 0\n";

        int r = 0;
        for (String row : input.trim().split("\\R")){
            int c =0;
            for (String ch : row.split(",")){
                board[r][c] = ch.trim().charAt(0);
                c++;
            }
            r++;
        }

        for (char[] row : board){
            System.out.println(row);
        }
        return board;
    }
}