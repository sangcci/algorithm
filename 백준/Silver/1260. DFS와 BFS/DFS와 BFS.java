import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, v;
    static List<List<Integer>> graphs;
    static StringBuilder sb;

    static boolean[] visited;
    static boolean isDone;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        graphs = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            graphs.add(new LinkedList<>());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(input.nextToken());
            int end = Integer.parseInt(input.nextToken());
            graphs.get(start).add(end);
            graphs.get(end).add(start);
        }
        for (int i = 1; i <= n; i++) {
            graphs.get(i).sort(Integer::compareTo);
        }
        //print(graphs);

        sb = new StringBuilder();

        visited = new boolean[n + 1];
        visited[v] = true;
        dfs(v, 1);

        sb.append("\n");

        visited = new boolean[n + 1];
        visited[v] = true;
        bfs(visited);

        System.out.println(sb);
    }

    public static void dfs(int cur, int level) {
        sb.append(cur + " ");
        if (level == n) {
            isDone = true;
        } else {
            List<Integer> nexts = graphs.get(cur);
            for (int next : nexts) {
                if (isDone) {
                    return;
                }
                if (!visited[next]) {
                    visited[next] = true;
                    dfs(next, level + 1);
                    //visited[next] = false;
                }
            }
        }
    }

    public static void bfs(boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(v);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                sb.append(cur + " ");

                List<Integer> nexts = graphs.get(cur);
                for (int next : nexts) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
        }
    }

    public static <T> void print(List<List<T>> graph) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.size(); i++) {
            sb.append(i + " : ");
            List<T> list = graph.get(i);
            for (T each : list) {
                sb.append(each + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static <T> void print(T[] arr) {
        StringBuilder sb = new StringBuilder();
        for (T each : arr) {
            sb.append(each + " ");
        }
        System.out.println(sb);
    }
}
