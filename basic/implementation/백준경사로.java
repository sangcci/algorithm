package basic.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준경사로 {

    // input
    static int n;
    static int l;
    static int[][] map;

    // output
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(answer);
    }

    public static void solution() {
        // basic.search map column
        for (int y = 0; y < n; y++) {
            if (isPass(map[y])) {
                answer++;
            }
        }
        // basic.search map row
        for (int x = 0; x < n; x++) {
            int[] rows = new int[n];
            for (int y = 0; y < n; y++) {
                rows[y] = map[y][x];
            }
            if (isPass(rows)) {
                answer++;
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer in = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(in.nextToken());
        l = Integer.parseInt(in.nextToken());

        map = new int[n][n];
        for (int y = 0; y < n; y++) {
            StringTokenizer h = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(h.nextToken());
            }
        }
    }

    public static boolean isPass(int[] height) {
        boolean[] hasRamp = new boolean[n];
        Arrays.fill(hasRamp, false);
        for (int i = 0; i < height.length-1; i++) {
            int cur = height[i];
            int next = height[i+1];
            int diff = Math.abs(cur- next);

            // 2 이상 차이
            if (diff >= 2) return false;
            // 차이 안남
            else if (diff == 0) continue;
            // 내리막 경사로를 놔야 할 경우
            else if (height[i] - height[i+1] == 1) {
                for (int up = 1; up <= l; up++) {
                    if (isOutOfIndex(i+up)) return false;
                    if (hasRamp[i+up]) return false;
                    if (height[i+1] != height[i+up]) return false;
                    hasRamp[i+up] = true;
                }
            }
            // 오르막 경사로를 놔야 할 경우
            else if (height[i] - height[i+1] == -1) {
                for (int down = 0; down < l; down++) {
                    if (isOutOfIndex(i-down)) return false;
                    if (hasRamp[i-down]) return false;
                    if (height[i] != height[i-down]) return false;
                    hasRamp[i-down] = true;
                }
            }
        }
        return true;
    }

    public static boolean isOutOfIndex(int i) {
        return i<0 || i>=n;
    }
}
