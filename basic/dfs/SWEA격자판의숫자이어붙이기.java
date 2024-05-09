package basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA격자판의숫자이어붙이기 {

    static int[][] board;
    static Set<String> set;
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            set = new HashSet<>();

            board = new int[4][4];
            for (int y = 0; y < 4; y++) {
                StringTokenizer input = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < 4; x++) {
                    board[y][x] = Integer.parseInt(input.nextToken());
                }
            }

            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    //System.out.println("탐색 시작 x: " + x + " y: " + y);
                    String num = String.valueOf(board[y][x]);
                    dfs(0, y, x, num);
                }
            }

            /*for (String each : set) {
                System.out.println(each);
            }*/

            int answer = set.size();

            output.append("#" + test_case + " " + answer + "\n");
        }
        System.out.println(output);
    }

    public static void dfs(int level, int curY, int curX, String num) {
        if (level == 6) {
            set.add(num);
        } else {
            for (int i = 0; i < 4; i++) {
                int nextY = curY + move[i][0];
                int nextX = curX + move[i][1];
                if (checkBoundary(nextY, nextX)) {
                    dfs(level + 1, nextY, nextX, num + String.valueOf(board[nextY][nextX]));
                }
            }
        }
    }

    public static boolean checkBoundary(int y, int x) {
        return y >= 0 && y < 4 && x >= 0 && x < 4;
    }

}
