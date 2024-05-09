package advanced.findNextPlace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEANQueen {

    static int n;
    static int[][] chessBoard;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());
            chessBoard = new int[n][n];
            cnt = 0;

            dfs(0);

            answer.append("#" + test_case + " " + cnt+  "\n");
        }
        System.out.println(answer);
    }

    public static void dfs(int num) {
        if (num == n) {
            cnt++;
        } else {
            for (int x = 0; x < n; x++) {
                if (chessBoard[num][x] == 0) {
                    int[][] curChessBoard = new int[n][n];
                    placeQueen(num, x, curChessBoard);
                    dfs(num + 1);
                    removeQueen(num, x, curChessBoard);
                }
            }
        }
    }

    private static void removeQueen(int y, int x, int[][] curChessBoard) {
        chessBoard[y][x] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (curChessBoard[i][j] == -1) {
                    chessBoard[i][j] = 0;
                }
            }
        }
    }

    public static void placeQueen(int y, int x, int[][] curChessBoard) {
        chessBoard[y][x] = 1;
        for (int i = y; i < n; i++) {
            if (chessBoard[i][x] == 0) {
                chessBoard[i][x] = -1;
                curChessBoard[i][x] = -1;
            }
        }
        for (int i = y; i >= 0; i--) {
            if (chessBoard[i][x] == 0) {
                chessBoard[i][x] = -1;
                curChessBoard[i][x] = -1;
            }
        }
        for (int j = x; j < n; j++) {
            if (chessBoard[y][j] == 0) {
                chessBoard[y][j] = -1;
                curChessBoard[y][j] = -1;
            }
        }
        for (int j = x; j >= 0; j--) {
            if (chessBoard[y][j] == 0) {
                chessBoard[y][j] = -1;
                curChessBoard[y][j] = -1;
            }
        }

        for (int i = y, j = x; i < n && j >= 0; i++, j--) {
            if (chessBoard[i][j] == 0) {
                chessBoard[i][j] = -1;
                curChessBoard[i][j] = -1;
            }
        }
        for (int i = y, j = x; i < n && j < n; i++, j++) {
            if (chessBoard[i][j] == 0) {
                chessBoard[i][j] = -1;
                curChessBoard[i][j] = -1;
            }
        }
        for (int i = y, j = x; i >= 0 && j < n; i--, j++) {
            if (chessBoard[i][j] == 0) {
                chessBoard[i][j] = -1;
                curChessBoard[i][j] = -1;
            }
        }
        for (int i = y, j = x; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 0) {
                chessBoard[i][j] = -1;
                curChessBoard[i][j] = -1;
            }
        }
    }

    public static void testPrint(int[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
