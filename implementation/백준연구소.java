package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준연구소 {
    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static final int[] moveY = {0, 1, 0, -1};
    static final int[] moveX = {1, 0, -1, 0};

    static int n;
    static int m;
    static int[][] labMap;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0);
        System.out.println(max);
    }

    /*
     연구소 맵 받아오기
     */
    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer size = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(size.nextToken());
        m = Integer.parseInt(size.nextToken());

        labMap = new int[n+2][m+2];
        Arrays.fill(labMap[0], WALL);
        Arrays.fill(labMap[n+1], WALL);
        for (int i = 0; i <= n+1; i++) {
            labMap[i][0] = WALL;
            labMap[i][m+1] = WALL;
        }

        for (int y = 1; y <= n; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int x = 1; x <= m; x++) {
                labMap[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /*
     벽 3개 넣기
     */
    public static void dfs(int cnt) {
        if (cnt == 3) {
            int[][] virusMap = bfs(copy(labMap));
            max = Math.max(max, count(virusMap));
        } else {
            for (int y = 1; y <= n; y++) {
                for (int x = 1; x <= m; x++) {
                    if (labMap[y][x] == BLANK) {
                        labMap[y][x] = WALL;
                        dfs(cnt + 1);
                        labMap[y][x] = BLANK;
                    }
                }
            }
        }
    }

    /*
     바이러스 퍼트리기
     */
    public static int[][] bfs(int[][] map) {
        Queue<Node> q = new LinkedList<>();
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (map[y][x] == VIRUS) {
                    q.offer(new Node(y, x));
                }
            }
        }
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = cur.y + moveY[i];
                int nextX = cur.x + moveX[i];
                if (map[nextY][nextX] == BLANK) {
                    map[nextY][nextX] = VIRUS;
                    q.offer(new Node(nextY, nextX));
                }
            }
        }
        return map;
    }

    /*
     안전 영역 갯수 세기
     */
    public static int count(int[][] virusMap) {
        int cnt = 0;
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (virusMap[y][x] == BLANK) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // 2차원 배열 복사
    public static int[][] copy(int[][] src) {
        if (src == null) {
            return null;
        }

        int[][] copy = new int[src.length][];

        for (int i = 0; i < src.length; i++) {
            copy[i] = new int[src[i].length];
            System.arraycopy(src[i], 0, copy[i], 0, src[i].length);
        }

        return copy;
    }

    public static void testOutput(int[][] test) {
        for (int y = 0; y < test.length; y++) {
            System.out.println();
            for (int x = 0; x < test[0].length; x++) {
                System.out.print(test[y][x] + " ");
            }
        }
        System.out.println();
    }
}

class Node {
    int y;
    int x;
    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}