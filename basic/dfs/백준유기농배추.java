package basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준유기농배추 {
    static int n, m, k;
    static int[][] map;
    static boolean[][] visited;
    static final int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int cnt;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(input.nextToken());
            m = Integer.parseInt(input.nextToken());
            k = Integer.parseInt(input.nextToken());
            map = new int[n][m];
            for (int i = 0; i < k; i++) {
                StringTokenizer cabbage = new StringTokenizer(br.readLine(), " ");
                int r = Integer.parseInt(cabbage.nextToken());
                int c = Integer.parseInt(cabbage.nextToken());
                map[r][c] = 1;
            }
            visited = new boolean[n][m];
            cnt = 0;

            // search
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (map[r][c] == 1 && !visited[r][c]) {
                        dfs(r, c);
                        cnt++;
                    }
                }
            }
            
            output.append(cnt + "\n");
        }
        System.out.println(output);
    }
    
    static void dfs(int curR, int curC) {
        boolean nothing = false;
        // cur visit
        visited[curR][curC] = true;
        // search next
        for (int dir = 0; dir < 4; dir++) {
            int nextR = curR + move[dir][0];
            int nextC = curC + move[dir][1];
            if (checkBoundary(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 1) {
                dfs(nextR, nextC);
            }
        }
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

}
