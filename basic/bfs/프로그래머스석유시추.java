package basic.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 프로그래머스석유시추 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int[][] land = new int[n][m];
        for (int r = 0; r < n; r++) {
            StringTokenizer inputLand = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < m; c++) {
                land[r][c] = Integer.parseInt(inputLand.nextToken());
            }
        }

        int result = new 프로그래머스석유시추().solution(land);

        System.out.println(result);
    }

    public int solution(int[][] land) {
        int[][] area = new int[land.length][land[0].length];
        Map<Integer, Integer> quantity = new HashMap<>();
        int num = 1;

        // 석유 구역 탐색
        for (int r = 0; r < land.length; r++) {
            for (int c = 0; c < land[0].length; c++) {
                if (land[r][c] == 1 && area[r][c] == 0) {
                    int cnt = bfs(land, area, r, c, num);
                    quantity.put(num, cnt);
                    num++;
                }
            }
        }

        // 시추 장소 탐색
        int max = 0;
        for (int c = 0; c < land[0].length; c++) {
            Set<Integer> areaNum = new HashSet<>();
            for (int r = 0; r < land.length; r++) {
                if (area[r][c] != 0) {
                    areaNum.add(area[r][c]);
                }
            }

            int sum = 0;
            for (int n : areaNum) {
                sum += quantity.get(n);
            }

            max = Math.max(max, sum);
        }

        return max;
    }

    public int bfs(int[][] land, int[][] area, int curR, int curC, int num) {
        int[][] moved = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int maxR = land.length;
        int maxC = land[0].length;
        int cnt = 0;
        area[curR][curC] = num;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{curR, curC});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;
            for (int dir = 0; dir < 4; dir++) {
                int nextR = cur[0] + moved[dir][0];
                int nextC = cur[1] + moved[dir][1];
                if (checkBoundary(nextR, nextC, maxR, maxC)
                        && land[nextR][nextC] == 1
                        && area[nextR][nextC] == 0) {
                    area[nextR][nextC] = num;
                    q.offer(new int[]{nextR, nextC});
                }
            }
        }

        return cnt;
    }

    public boolean checkBoundary(int r, int c, int n, int m) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    public void testPrint(int[][] arr) {
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
