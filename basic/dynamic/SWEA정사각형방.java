package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA정사각형방 {

    static int n;
    static int[][] room;
    static int[][] dp;
    static boolean[][] visited;
    static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 상 오 하 좌
    static int max, startNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());
            room = new int[n][n];
            for (int y = 0; y < n; y++) {
                StringTokenizer input = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < n; x++) {
                    room[y][x] = Integer.parseInt(input.nextToken());
                }
            }
            dp = new int[n][n];
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    dp[y][x] = -1;
                }
            }
            visited = new boolean[n][n];
            max = -1;
            startNum = -1;

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    //System.out.println("탐색 시작: " + y + " " + x);
                    visited[y][x] = true;
                    int curMax = dfs(y, x);
                    visited[y][x] = false;
                    if (max < curMax) {
                        //System.out.println("갱신!");
                        max = curMax;
                        startNum = room[y][x];
                        //System.out.println("max: " + max + " startNum: " + startNum);
                    } else if (max == curMax) {
                        startNum = Math.min(startNum, room[y][x]);
                    }
                   // testPrint(dp);
                }
            }
            String answer = startNum + " " + max;

            output.append("#" + test_case + " " + answer + "\n");
        }
        System.out.println(output);
    }

    public static int dfs(int curY, int curX) {
        if (dp[curY][curX] != -1) {
            return dp[curY][curX];
        } else {
            int curMax = 1;
            for (int i = 0; i < 4; i++) {
                int nextY = curY + move[i][0];
                int nextX = curX + move[i][1];
                if (!checkBoundary(nextY, nextX) || visited[nextY][nextX]) {
                    continue;
                }
                else {
                    if (room[nextY][nextX] == room[curY][curX] + 1) {
                        //System.out.println("go! " + nextY + " " + nextX);
                        visited[nextY][nextX] = true;
                        curMax = Math.max(curMax, dfs(nextY, nextX) + 1);
                        visited[nextY][nextX] = false;
                        //System.out.println("back");
                    }
                }
            }
            dp[curY][curX] = curMax;
            return dp[curY][curX];
        }
    }

    public static boolean checkBoundary(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
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
