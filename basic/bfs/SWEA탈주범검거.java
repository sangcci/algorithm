package basic.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA탈주범검거 {

    // input
    static int n;
    static int m;
    static int[][] map;
    static int R;
    static int C;
    static int L;

    // logic
    static int[][] visited;
    static final int NOT_YET = 0;
    static final int VISITED = 1;
    static int[] moveY = {-1, 0, 1, 0};
    static int[] moveX = {0, 1, 0, -1};

    // result
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(input.nextToken());
            m = Integer.parseInt(input.nextToken());
            R = Integer.parseInt(input.nextToken());
            C = Integer.parseInt(input.nextToken());
            L = Integer.parseInt(input.nextToken());

            map = new int[n][m];
            for (int y = 0; y < n; y++) {
                StringTokenizer inputMap = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < m; x++) {
                    map[y][x] = Integer.parseInt(inputMap.nextToken());
                }
            }
            visited = new int[n][m];

            // logic
            //basic.dfs(1, R, C);
            bfs();

            // output
            answer.append("#")
                    .append(test_case)
                    .append(" ")
                    .append(cnt)
                    .append("\n");

            // init
            cnt = 0;
        }
        System.out.println(answer);
    }

    public static void bfs () {
        Queue<TunnelLocation> q = new LinkedList<>();

        // init
        int level = 1;
        q.offer(new TunnelLocation(R, C));

        // start
        bfs: while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0 ; i < size; i++) {
                if (level > L) break bfs;

                TunnelLocation tl = q.poll();
                int curY = tl.y;
                int curX = tl.x;
                if (visited[curY][curX] == VISITED) continue;

                cnt++;
                visited[curY][curX] = VISITED;
                int curTunnel = map[curY][curX];
                for (int dir = 0; dir < 4; dir++) {
                    if (dir == 0 &&
                            (curTunnel == 1 || curTunnel == 2 || curTunnel == 4 || curTunnel == 7)) {
                        int nextY = curY + moveY[dir];
                        int nextX = curX + moveX[dir];
                        if (!isOutOfIndex(nextY, nextX) && visited[nextY][nextX] != VISITED) {
                            int nextTunnel = map[nextY][nextX];
                            if (nextTunnel == 1 || nextTunnel == 2 || nextTunnel == 5 || nextTunnel == 6) {
                                q.offer(new TunnelLocation(nextY, nextX));
                            }
                        }
                    }
                    if (dir == 1 &&
                            (curTunnel == 1 || curTunnel == 3 || curTunnel == 4 || curTunnel == 5)) {
                        int nextY = curY + moveY[dir];
                        int nextX = curX + moveX[dir];
                        if (!isOutOfIndex(nextY, nextX) && visited[nextY][nextX] != VISITED) {
                            int nextTunnel = map[nextY][nextX];
                            if (nextTunnel == 1 || nextTunnel == 3 || nextTunnel == 6 || nextTunnel == 7) {
                                q.offer(new TunnelLocation(nextY, nextX));
                            }
                        }
                    }
                    if (dir == 2 &&
                            (curTunnel == 1 || curTunnel == 2 || curTunnel == 5 || curTunnel == 6)) {
                        int nextY = curY + moveY[dir];
                        int nextX = curX + moveX[dir];
                        if (!isOutOfIndex(nextY, nextX) && visited[nextY][nextX] != VISITED) {
                            int nextTunnel = map[nextY][nextX];
                            if (nextTunnel == 1 || nextTunnel == 2 || nextTunnel == 4 || nextTunnel == 7) {
                                q.offer(new TunnelLocation(nextY, nextX));
                            }
                        }
                    }
                    if (dir == 3 &&
                            (curTunnel == 1 || curTunnel == 3 || curTunnel == 6 || curTunnel == 7)) {
                        int nextY = curY + moveY[dir];
                        int nextX = curX + moveX[dir];
                        if (!isOutOfIndex(nextY, nextX) && visited[nextY][nextX] != VISITED) {
                            int nextTunnel = map[nextY][nextX];
                            if (nextTunnel == 1 || nextTunnel == 3 || nextTunnel == 4 || nextTunnel == 5) {
                                q.offer(new TunnelLocation(nextY, nextX));
                            }
                        }
                    }
                }
            }
            level++;
        }
    }


    public static void dfs(int move, int curY, int curX) {
        if (move > L) {
            return;
        }
        if (visited[curY][curX] == VISITED) {
            return;
        }
        System.out.println(curY + " " + curX);
        cnt++;
        visited[curY][curX] = VISITED;

        int curTunnel = map[curY][curX];
        for (int dir = 0; dir < 4; dir++) {
            if (dir == 0 &&
                    (curTunnel == 1 || curTunnel == 2 || curTunnel == 4 || curTunnel == 7)) {
                int nextY = curY + moveY[dir];
                int nextX = curX + moveX[dir];
                if (!isOutOfIndex(nextY, nextX) && visited[nextY][nextX] != VISITED) {
                    int nextTunnel = map[nextY][nextX];
                    if (nextTunnel == 1 || nextTunnel == 2 || nextTunnel == 5 || nextTunnel == 6) {
                        dfs(move + 1, nextY, nextX);
                    }
                }
            }
            if (dir == 1 &&
                    (curTunnel == 1 || curTunnel == 3 || curTunnel == 4 || curTunnel == 5)) {
                int nextY = curY + moveY[dir];
                int nextX = curX + moveX[dir];
                if (!isOutOfIndex(nextY, nextX) && visited[nextY][nextX] != VISITED) {
                    int nextTunnel = map[nextY][nextX];
                    if (nextTunnel == 1 || nextTunnel == 3 || nextTunnel == 6 || nextTunnel == 7) {
                        dfs(move + 1, nextY, nextX);
                    }
                }
            }
            if (dir == 2 &&
                    (curTunnel == 1 || curTunnel == 2 || curTunnel == 5 || curTunnel == 6)) {
                int nextY = curY + moveY[dir];
                int nextX = curX + moveX[dir];
                if (!isOutOfIndex(nextY, nextX) && visited[nextY][nextX] != VISITED) {
                    int nextTunnel = map[nextY][nextX];
                    if (nextTunnel == 1 || nextTunnel == 2 || nextTunnel == 4 || nextTunnel == 7) {
                        dfs(move + 1, nextY, nextX);
                    }
                }
            }
            if (dir == 3 &&
                    (curTunnel == 1 || curTunnel == 3 || curTunnel == 6 || curTunnel == 7)) {
                int nextY = curY + moveY[dir];
                int nextX = curX + moveX[dir];
                if (!isOutOfIndex(nextY, nextX) && visited[nextY][nextX] != VISITED) {
                    int nextTunnel = map[nextY][nextX];
                    if (nextTunnel == 1 || nextTunnel == 3 || nextTunnel == 4 || nextTunnel == 5) {
                        dfs(move + 1, nextY, nextX);
                    }
                }
            }
        }
    }

    public static boolean isOutOfIndex(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= m;
    }

}
class TunnelLocation {
    int y;
    int x;
    public TunnelLocation(int y, int x) {
        this.y = y;
        this.x = x;
    }
}