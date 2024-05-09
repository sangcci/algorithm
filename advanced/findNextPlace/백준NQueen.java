package advanced.findNextPlace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준NQueen {

    static int n;
    static int[][] chessBoard;
    static boolean[] v1, v2, v3;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        chessBoard = new int[n][n];

        v1 = new boolean[n];
        v2 = new boolean[2*n + 1];
        v3 = new boolean[2*n + 1];

        cnt = 0;

        dfs(0);

        System.out.println(cnt);
    }

    public static void dfs(int num) {
        if (num == n) {
            cnt++;
        } else {
            for (int x = 0; x < n; x++) {
                if (chessBoard[num][x] == 0) {
                    if (!v1[x] && !v2[num - x + n] && !v3[num + x]) {
                        v1[x] = true;
                        v2[num - x + n] = true;
                        v3[num + x] = true;

                        dfs(num + 1);

                        v1[x] = false;
                        v2[num - x + n] = false;
                        v3[num + x] = false;
                    }
                }
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
