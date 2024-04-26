package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA디저트카페 {

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] dessert;
    static int startY, startX;
    static int[][] move = {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};
    static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());

            map = new int[n][n];
            for (int y = 0; y < n; y++) {
                StringTokenizer input = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < n; x++) {
                    map[y][x] = Integer.parseInt(input.nextToken());
                }
            }

            for (int y = 0; y < n-1; y++) {
                for (int x = 0; x < n; x++) {
                    visited = new boolean[n][n];
                    dessert = new boolean[101]; // 1 ~ 100
                    startY = y;
                    startX = x;
                    visited[y][x] = true;
                    dessert[map[y][x]] = true;
                    dfs(1, y, x, 0);
                }
            }

            answer.append("#")
                    .append(test_case)
                    .append(" ")
                    .append(max)
                    .append("\n");

            max = -1;
        }

        System.out.println(answer);
    }

    public static void dfs(int depth, int curY, int curX, int preLoc) {
        for (int dir = preLoc; dir < 4; dir++) {
            int nextY = curY + move[dir][0];
            int nextX = curX + move[dir][1];
            if (!isOutOfIndex(nextY, nextX)) {
                if (nextY == startY && nextX == startX && depth >= 4) {
                    max = Math.max(max, depth);
                } else if (!visited[nextY][nextX] && !dessert[map[nextY][nextX]]) {
                    visited[nextY][nextX] = true;
                    dessert[map[nextY][nextX]] = true;
                    dfs(depth + 1, nextY, nextX, dir);
                    visited[nextY][nextX] = false;
                    dessert[map[nextY][nextX]] = false;
                }
            }
        }
    }

    public static boolean isOutOfIndex(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= n;
    }

}
