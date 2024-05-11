package advanced.moveSamePlace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA혁진이의프로그램검증 {

    static int R, C;
    static char[][] commands;
    static boolean[][][][] visited;
    static final int[][] move = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static final int LEFT = 0, RIGHT = 1, UP = 2, BOTTOM = 3;
    static boolean canStop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            R = Integer.parseInt(input.nextToken());
            C = Integer.parseInt(input.nextToken());

            commands = new char[R][C];
            for (int y = 0; y < R; y++) {
                String[] inputs = br.readLine().split("");
                for (int x = 0; x < C; x++) {
                    commands[y][x] = inputs[x].charAt(0);
                }
            }
            visited = new boolean[R][C][4][16];
            canStop = false;

            boolean has = false;
            for (int y = 0; y < R; y++) {
                for (int x = 0; x < C; x++) {
                    if (commands[y][x] == '@') {
                        has = true;
                        break;
                    }
                }
            }

            if (has)
                run(0, 0, RIGHT, 0);

            String answer = "";
            if (has && canStop) {
                answer = "YES";
            } else {
                answer = "NO";
            }

            output.append("#" + test_case + " " + answer + "\n");
        }
        System.out.println(output);
    }

    public static void run(int curY, int curX, int dir, int memory) {
        if (canStop) return;

        if (visited[curY][curX][dir][memory]) return;
        visited[curY][curX][dir][memory] = true;

        char cnd = commands[curY][curX];
        if (cnd == '?') {
            visited[curY][curX][0][memory] = true;
            visited[curY][curX][1][memory] = true;
            visited[curY][curX][2][memory] = true;
            visited[curY][curX][3][memory] = true;
            for (int i = 0; i < 4; i++) {
                int nextY = curY + move[i][0];
                if (nextY < 0) nextY = R - 1;
                else if (nextY >= R) nextY = 0;

                int nextX = curX + move[i][1];
                if (nextX < 0) nextX = C - 1;
                else if (nextX >= C) nextX = 0;

                /*int nextY = moveYOverBoundary(curY + move[i][0]);
                int nextX = moveXOverBoundary(curX + move[i][1]);*/
                run(nextY, nextX, i, memory);
                if (canStop) return;
            }
        } else {
            if (cnd == '@') {
                canStop = true;
                return;
            }
            if (cnd == '<') dir = LEFT;
            else if (cnd == '>') dir = RIGHT;
            else if (cnd == '^') dir = UP;
            else if (cnd == 'v') dir = BOTTOM;
            else if (cnd == '_') {
                if (memory == 0) dir = RIGHT;
                else dir = LEFT;
            }
            else if (cnd == '|') {
                if (memory == 0) dir = BOTTOM;
                else dir = UP;
            }
            else if (cnd == '.') {}
            else if (Character.isDigit(cnd)) memory = Character.getNumericValue(cnd);
            else if (cnd == '+') {
                if (memory == 15) memory = 0;
                else memory++;
            }
            else if (cnd == '-') {
                if (memory == 0) memory = 15;
                else memory--;
            }
            int nextY = curY + move[dir][0];
            if (nextY < 0) nextY = R - 1;
            else if (nextY >= R) nextY = 0;

            int nextX = curX + move[dir][1];
            if (nextX < 0) nextX = C - 1;
            else if (nextX >= C) nextX = 0;
            /*int nextY = moveYOverBoundary(curY + move[dir][0]);
            int nextX = moveXOverBoundary(curX + move[dir][1]);*/
            run(nextY, nextX, dir, memory);
        }
    }

    public static int moveYOverBoundary(int y) {
        if (y < 0) {
            return R - 1;
        } else if (y >= R) {
            return 0;
        } else {
            return y;
        }
    }

    public static int moveXOverBoundary(int x) {
        if (x < 0) {
            return C - 1;
        } else if (x >= C) {
            return 0;
        } else {
            return x;
        }
    }
}
