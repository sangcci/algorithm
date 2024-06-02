package basic.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스아이템줍기 {

    int[][] map;
    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    final int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean[][] visited;
    int answer;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        answer = 0;

        // fill ractangle
        map = new int[102][102];
        for (int i = 0; i < rectangle.length; i++) {
            int y_start = rectangle[i][1] * 2;
            int x_start = rectangle[i][0] * 2;
            int y_end = rectangle[i][3] * 2;
            int x_end = rectangle[i][2] * 2;
            for (int r = y_start; r <= y_end; r++) {
                for (int c = x_start; c <= x_end; c++) {
                    if (map[r][c] == 2) continue;
                    if (r == y_start || r == y_end || c == x_start || c == x_end) {
                        map[r][c] = 1; // border
                    } else {
                        map[r][c] = 2; // inside
                    }
                }
            }
        }

        //testPrint(map);

        // find shortest path
        characterX *= 2; characterY *= 2;
        itemX *= 2; itemY *= 2;
        bfs(characterX, characterY, itemX, itemY);

        // return answer
        return answer / 2;

    }

    void bfs(int characterX, int characterY, int itemX, int itemY) {
        int cnt = 0;
        visited = new boolean[102][102];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(characterY, characterX));
        bfs: while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if (cur.r == itemY && cur.c == itemX) {
                    answer = cnt;
                    break bfs;
                }
                for (int dir = 0; dir < 4; dir++) {
                    int nextR = cur.r + move[dir][0];
                    int nextC = cur.c + move[dir][1];
                    if (!visited[nextR][nextC] && map[nextR][nextC] == 1) {
                        visited[nextR][nextC] = true;
                        q.offer(new Node(nextR, nextC));
                    }
                }
            }
            cnt++;
        }
    }

    void testPrint(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                sb.append(arr[r][c] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}