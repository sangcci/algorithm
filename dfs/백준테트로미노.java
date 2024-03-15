package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준테트로미노 {

    static int n;
    static int m;
    static int max;
    static int[][] board;
    static int[][] checkArr;
    static int[] moveY = {0, 1, 0, -1};
    static int[] moveX = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
         board 초기화
         */
        StringTokenizer size = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(size.nextToken());
        m = Integer.parseInt(size.nextToken());

        board = new int[n][m];
        for (int y = 0; y < n; y++) {
            StringTokenizer num = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < m; x++) {
                board[y][x] = Integer.parseInt(num.nextToken());
            }
        }
        /*
         문제 해결
         */
        solution();

        /*
         정답 출력
         */
        System.out.println(max);
    }

    public static void solution() {
        /*
         dfs를 위한 체크 배열 초기화
         */
        checkArr = new int[n][m];

        /*
         각 지점마다 dfs 확인
         */
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                checkArr[y][x] = 1; // 처음 위치도 visit 표시
                dfs(1, board[y][x], y, x);
                checkArr[y][x] = 0;
            }
        }
    }

    /*
     테트로미노 만들 dfs
     */
    public static void dfs(int level, int sum, int y, int x) {
        // 4칸까지 갔을 때 최대 값 비교
        if (level == 4) {
            max = Math.max(max, sum);
            //System.out.println(sum);
           // System.out.println(y + " " + x);
        }
        else {
            for (int i = 0; i < 4; i++) {
                int nextY = y + moveY[i];
                int nextX = x + moveX[i];
                // 바깥으로 나갈 시 다음 경우의 수 체크
                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m) {
                    continue;
                }
                if (checkArr[nextY][nextX] == 0) {
                    if (level == 2) {
                        checkArr[nextY][nextX] = 1;
                        dfs(level + 1, sum + board[nextY][nextX], y, x);
                        checkArr[nextY][nextX] = 0;
                    }
                    checkArr[nextY][nextX] = 1;
                    dfs(level + 1, sum + board[nextY][nextX], nextY, nextX);
                    checkArr[nextY][nextX] = 0;
                }
            }
        }
    }

    public static void testPrintBoard(int[][] board) {
        for (int y = 0; y < board.length; y++) {
            System.out.println();
            for (int x = 0; x < board[0].length; x++) {
                System.out.print(board[y][x] + " ");
            }
        }
    }
}
