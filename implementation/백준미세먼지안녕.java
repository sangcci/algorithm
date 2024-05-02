package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준미세먼지안녕 {
    static int R, C, T, up, down;
    static int[][] map;
    static final int AIR_CLEANER = -1;
    static final int EMPTY = 0;
    static final int[][] next = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static class Dust {
        int y;
        int x;
        int n;

        public Dust(int y, int x, int n) {
            this.y = y;
            this.x = x;
            this.n = n;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(input.nextToken()); // y
        C = Integer.parseInt(input.nextToken()); // x
        T = Integer.parseInt(input.nextToken());

        map = new int[R][C];
        for (int y = 0; y < R; y++) {
            StringTokenizer inputMap = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < C; x++) {
                int n = Integer.parseInt(inputMap.nextToken());
                if (n == AIR_CLEANER) down = y;
                map[y][x] = n;
            }
        }
        up = down - 1;

        for (int i = 0; i < T; i++) {
            spread();
            turnOnAir();
        }

        System.out.println(sum());
    }

    public static void spread() {
        Queue<Dust> q = new LinkedList<>();
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (map[y][x] != AIR_CLEANER && map[y][x] != EMPTY) {
                    q.offer(new Dust(y, x, map[y][x]));
                }
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Dust cur = q.poll();
                int dirCnt = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int nextY = cur.y + next[dir][0];
                    int nextX = cur.x + next[dir][1];
                    if (!isOutOfIndex(nextY, nextX) && !isAirCleanerHere(nextY, nextX)) {
                        int spreadNum = cur.n / 5;
                        map[nextY][nextX] += spreadNum;
                        dirCnt++;
                    }
                }
                map[cur.y][cur.x] -= ((cur.n/5) * dirCnt);
            }
        }
    }

    public static boolean isOutOfIndex(int y, int x) {
        return y < 0 || y >= R || x < 0 || x >= C;
    }

    public static boolean isAirCleanerHere(int y, int x) {
        return map[y][x] == AIR_CLEANER;
    }

    public static void turnOnAir() {
        // up
        for (int y = up-1; y > 0; y--) {
            swap(y, 0, y-1, 0);
        }
        for (int x = 0; x < C-1; x++) {
            swap(0, x, 0, x+1);
        }
        for (int y = 0; y < up; y++) {
            swap(y, C-1, y+1, C-1);
        }
        for (int x = C-1; x > 1; x--) {
            swap(up, x, up, x-1);
        }
        map[up][1] = 0;
        // down
        for (int y = down + 1; y < R-1; y++) {
            swap(y , 0, y+1, 0);
        }
        for (int x = 0; x < C-1; x++) {
            swap (R-1, x, R-1, x+1);
        }
        for (int y = R - 1; y > down; y--) {
            swap(y, C-1, y-1, C-1);
        }
        for (int x = C-1; x > 1; x--) {
            swap(down, x, down, x-1);
        }
        map[down][1] = 0;
    }

    public static void swap(int y1, int x1, int y2, int x2) {
        int tmp = map[y1][x1];
        map[y1][x1] = map[y2][x2];
        map[y2][x2] = tmp;
    }

    public static int sum() {
        int sum = 0;
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (!isAirCleanerHere(y, x)) {
                    sum += map[y][x];
                }
            }
        }
        return sum;
    }

    public static void testPrint(int[][] arr) {
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}