package basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA등산로조정 {

    static int n;
    static int k;
    static int[][] map;
    static final int[] moveY = {-1, 0, 1, 0};
    static final int[] moveX = {0, 1, 0, -1};
    static int[][] visited;
    static final int NOT_YET = 0;
    static final int VISITED = 1;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            // input
            String[] split = br.readLine().split(" ");
            n = Integer.parseInt(split[0]);
            k = Integer.parseInt(split[1]);

            map = new int[n][n];
            int maxheigth = 0;
            for (int y = 0; y < n; y++) {
                StringTokenizer input = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < n; x++) {
                    int each = Integer.parseInt(input.nextToken());
                    maxheigth = Math.max(maxheigth, each);
                    map[y][x] = each;
                }
            }

            visited = new int[n][n];

            // logic
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (map[y][x] == maxheigth) {
                        visited[y][x] = VISITED;
                        dfs(1, y, x, false);
                        visited[y][x] = NOT_YET;
                    }
                }
            }

            // output
            answer.append("#")
                    .append(test_case)
                    .append(" ")
                    .append(max)
                    .append("\n");

            // init
            max = 0;
        }

        System.out.println(answer);
    }

    public static void dfs(int level, int curY, int curX, boolean isRemove) {
        boolean isEnd = true;

        // 다시 여기에 풀어보자
        for (int i = 0; i < 4; i++) {
            int nextY = curY + moveY[i];
            int nextX = curX + moveX[i];
            if (isNotOutOfIndex(nextY, nextX) && visited[nextY][nextX] == NOT_YET) {
                for (int count = 0; count <= k; count++) {
                    if (count == 0) {
                        if (map[nextY][nextX] < map[curY][curX]) {
                            isEnd = false;
                            visited[nextY][nextX] = VISITED;
                            dfs(level + 1, nextY, nextX, isRemove);
                            visited[nextY][nextX] = NOT_YET;
                        }
                    } else {
                        if (!isRemove && map[nextY][nextX] - count < map[curY][curX]) {
                            isEnd = false;
                            visited[nextY][nextX] = VISITED;
                            map[nextY][nextX] -= count;
                            dfs(level + 1, nextY, nextX, true);
                            visited[nextY][nextX] = NOT_YET;
                            map[nextY][nextX] += count;
                        }
                    }
                }
            }
        }

        // 끝 점 도달
        if (isEnd) {
            max = Math.max(max, level);
        }
    }

    public static boolean isNotOutOfIndex(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }
}
