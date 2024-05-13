package advanced.findNextPlace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA파핑파핑지뢰찾기 {

    static int n;
    static char[][] map;
    static boolean[][] visited;
    static final int[][] move = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static int cnt;
    static class Node {

        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());

            map = new char[n][n];
            for (int y = 0; y < n; y++) {
                String[] inputs = br.readLine().split("");
                for (int x = 0; x < n; x++) {
                    map[y][x] = inputs[x].charAt(0);
                }
            }
            visited = new boolean[n][n];
            cnt = 0;

            // find '0' place and bfs
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (map[y][x] == '.') {
                        boolean isZero = true;
                        for (int i = 0; i < 8; i++) {
                            int nextY = y + move[i][0];
                            int nextX = x + move[i][1];
                            if (!checkBoundary(nextY, nextX)) continue;
                            if (map[nextY][nextX] == '*') {
                                isZero = false;
                                break;
                            }
                        }
                        if (isZero) {
                            map[y][x] = '0';
                            bfs(y, x, map);
                        }
                    }
                }
            }

            // choose '0', '.' and count
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (map[y][x] == '0') {
                        if (!visited[y][x]) {
                            cnt++;
                        }
                    } else if (map[y][x] == '.') {
                        cnt++;
                    }

                }
            }

            output.append("#" + test_case + " " + cnt + "\n");
        }
        System.out.println(output);
    }

    public static void bfs(int curY, int curX, char[][] nextMap) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(curY, curX));
        while (!q.isEmpty()) {
            Node cur = q.poll();

            // count bomb around
            int cnt = 0;
            for (int i = 0; i < 8; i++) {
                int nextY = cur.y + move[i][0];
                int nextX = cur.x + move[i][1];
                if (checkBoundary(nextY, nextX) && nextMap[nextY][nextX] == '*') {
                    cnt++;
                }
            }
            // write cnt
            nextMap[cur.y][cur.x] = (char) (cnt + '0');

            // spread
            if (cnt == 0) {
                for (int i = 0; i < 8; i++) {
                    int nextY = cur.y + move[i][0];
                    int nextX = cur.x + move[i][1];
                    if (checkBoundary(nextY, nextX) && !visited[nextY][nextX] && nextMap[nextY][nextX] == '.') {
                        visited[nextY][nextX] = true;
                        q.offer(new Node(nextY, nextX));
                    }
                }
            }
        }
    }

    public static boolean checkBoundary(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }

    public static void testPrint(char[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
