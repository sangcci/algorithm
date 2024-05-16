package basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 프로그래머스네트워크 {

    static int n, answer;
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        answer = 0;

        graph = new int[n][n];
        for (int y = 0; y < n; y++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < n; x++) {
                graph[y][x] = Integer.parseInt(input.nextToken());
            }
        }

        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                dfs(i);
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int start) {
        for (int end = 0; end < n; end++) {
            if (graph[start][end] == 1 && !visited[end]) {
                visited[end] = true;
                dfs(end);
            }
        }
    }
}