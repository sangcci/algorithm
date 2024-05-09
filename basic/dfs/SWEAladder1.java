package basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEAladder1 {

    static int[][] ladder;
    static boolean[][] visited;
    static final int[][] move = {{0, 1}, {0, -1}, {1, 0}};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());

            ladder = new int[100][100];
            for (int y = 0; y < 100; y++) {
                StringTokenizer input = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < 100; x++) {
                    ladder[y][x] = Integer.parseInt(input.nextToken());
                }
            }

            visited = new boolean[100][100];

            for (int x = 0; x < 100; x++) {
                if (ladder[0][x] == 1) {
                    //System.out.println("x위치: " + x);
                    dfs(x, 0, x);
                }
            }

            output.append("#" + test_case + " " + answer + "\n");
        }
        System.out.println(output);
    }

    public static void dfs(int start, int curY, int curX) {
        if (curY == 99) {
            //System.out.println("도달!");
            if (ladder[curY][curX] == 2) answer = start;
        } else {
            for (int i = 0; i < 3; i++) {
                int nextY = curY + move[i][0];
                int nextX = curX + move[i][1];
                if (checkBoundary(nextY, nextX) && ladder[nextY][nextX] != 0 && visited[nextY][nextX] == false) {
                    //System.out.println(nextY + " " + nextX);
                    visited[nextY][nextX] = true;
                    dfs(start, nextY, nextX);
                    visited[nextY][nextX] = false;
                    break;
                }
            }
        }
    }

    public static boolean checkBoundary(int y, int x) {
        return y >= 0 && y < 100 && x >= 0 && x < 100;
    }
}
