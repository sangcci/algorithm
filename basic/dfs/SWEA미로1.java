package basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA미로1 {

    static int[][] map;
    static final int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static boolean[][] visited;
    static int canEscape;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());

            map = new int[16][16];
            for (int y = 0; y < 16; y++) {
                String[] input = br.readLine().split("");
                for (int x = 0; x < 16; x++) {
                    map[y][x] = Integer.parseInt(input[x]);
                }
            }
            visited = new boolean[16][16];
            canEscape = 0;

            dfs(0, 1, 1);

            output.append("#" + test_case + " " + canEscape + "\n");
        }
        System.out.println(output);
    }

    public static void dfs(int level, int curY, int curX) {
        if (level >= 169) return;
        if (map[curY][curX] == 3) {
            canEscape = 1;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextY = curY + move[i][0];
            int nextX = curX + move[i][1];
            if (checkBoundary(nextY, nextX) && !visited[nextY][nextX] && map[nextY][nextX] != 1) {
                visited[nextY][nextX] = true;
                dfs(level + 1, nextY, nextX);
                visited[nextY][nextX] = false;
            }
        }
    }

    public static boolean checkBoundary(int y, int x) {
        return y >= 0 && y < 16 && x >= 0 && x < 16;
    }
}
