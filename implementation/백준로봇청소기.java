package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준로봇청소기 {

    // const
    static final int NOT_YET = 0;
    static final int WALL = 1;
    static final int COMPLETED = 2;
    static final int[] findY = {-1, 0, 1, 0};
    static final int[] findX = {0, 1, 0, -1};

    // input
    static int n, m;
    static int r, c, d;
    static int[][] room;

    // result
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        input();
        cleaning();
        System.out.println(cnt);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer size = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(size.nextToken());
        m = Integer.parseInt(size.nextToken());

        StringTokenizer robot = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(robot.nextToken()) + 1;
        c = Integer.parseInt(robot.nextToken()) + 1;
        d = Integer.parseInt(robot.nextToken());

        room = new int[n+2][m+2];
        Arrays.fill(room[0], WALL);
        Arrays.fill(room[n + 1], WALL);
        for (int i = 0; i <= n+1; i++) {
            room[i][0] = WALL;
            room[i][m+1] = WALL;
        }

        for (int y = 1; y <= n; y++) {
            StringTokenizer block = new StringTokenizer(br.readLine(), " ");
            for (int x = 1; x <= m; x++) {
                room[y][x] = Integer.parseInt(block.nextToken());
            }
        }
    }

    public static void cleaning() {
        while (true) {
            if (room[r][c] == NOT_YET) {
                room[r][c] = COMPLETED;
                cnt++;
            }
            // 앞으로 청소 할 구역이 있을 경우
            boolean flag = true;
            search: for (int i = 0; i < 4; i++) {
                if (room[r + findY[i]][c + findX[i]] == NOT_YET) {
                    // 청소 안한 구역을 찾아 전진
                    moveForward();
                    flag = false;
                    break search;
                }
            }
            // 청소 할 구역이 없을 경우
            if (flag) {
                // 후진
                boolean isStop = back();
                if (isStop) break;
            }
        }
    }

    public static void moveForward() {
        for (int i = 0; i < 4; i++) {
            if (d == 0) d = 3;
            else d -= 1;

            if (room[r + findY[d]][c + findX[d]] == NOT_YET) {
                r += findY[d];
                c += findX[d];
                break;
            }
        }
    }

    public static boolean back() {
        int back;
        if (d == 1) back = 3;
        else if (d == 0) back = 2;
        else back = d - 2;

        if (room[r + findY[back]][c + findX[back]] == WALL) {
            return true;
        } else {
            r += findY[back];
            c += findX[back];
            return false;
        }
    }

    public static void testPrintRoom(int[][] test) {
        for (int y = 0; y <= n+1; y++) {
            System.out.println();
            for (int x = 0; x <= m+1; x++) {
                System.out.print(room[y][x] + " ");
            }
        }
    }
}
