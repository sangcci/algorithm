package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SWEA보급로 {
    static int n;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] cost;
    static int min = Integer.MAX_VALUE;
    static class Node implements Comparable<Node> {
        int y, x;
        int time;
        public Node(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());

            map = new int[n][n];
            for (int y = 0; y < n; y++) {
                String[] input = br.readLine().split("");
                for (int x = 0; x < n; x++) {
                    map[y][x] = Integer.parseInt(input[x]);
                }
            }
            cost = new int[n][n];
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    cost[y][x] = Integer.MAX_VALUE;
                }
            }
            cost[0][0] = 0;

            bfs();

            answer.append("#" + test_case + " " + min + "\n");

            min = 0;
        }
        System.out.println(answer);
    }

    public static void bfs() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(0, 0, 0));
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if (checkArrival(cur.y, cur.x)) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nextY = cur.y + dir[i][0];
                int nextX = cur.x + dir[i][1];
                if (isOutOfBounds(nextY, nextX)) {
                    continue;
                }
                if (cost[nextY][nextX] > cost[cur.y][cur.x] + map[nextY][nextX]) {
                    cost[nextY][nextX] = cost[cur.y][cur.x] + map[nextY][nextX];
                    q.offer(new Node(nextY, nextX, cost[nextY][nextX]));
                }
            }
        }
        min = cost[n-1][n-1];
    }

    public static boolean checkArrival(int y, int x) {
        return y == n-1 && x == n-1;
    }

    public static boolean isOutOfBounds(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= n;
    }

    public static void testPrint(int[][] arr) {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
    }
}
