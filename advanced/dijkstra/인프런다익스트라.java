package advanced.dijkstra;
/*
6 9
1 2 12
1 3 4
2 1 2
2 3 5
2 5 5
3 4 5
4 2 2
4 5 5
6 4 5
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class 인프런다익스트라 {
    static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int n;
    static int m;
    static List<List<Node>> graph;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(input.nextToken());
        m = Integer.parseInt(input.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer InputNode = new StringTokenizer(br.readLine(), " ");
            int index = Integer.parseInt(InputNode.nextToken());
            int next = Integer.parseInt(InputNode.nextToken());
            int cost = Integer.parseInt(InputNode.nextToken());
            List<Node> nodes = graph.get(index);
            nodes.add(new Node(next, cost));
        }
        cost = new int[n+1];
        // cost[1] = 0; 1이 시작점이기 때문
        for (int i = 2; i <= n; i++) {
            cost[i] = Integer.MAX_VALUE;
        }

        dijkstra();

        StringBuilder answer = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            answer.append(i + " : " + cost[i] + "\n");
        }
        System.out.println(answer);
    }

    public static void dijkstra() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            List<Node> nodes = graph.get(cur.vertex);
            for (Node next : nodes) {
                int nextNum = next.vertex;
                if (cost[nextNum] > cost[cur.vertex] + next.cost) {
                    cost[nextNum] = cost[cur.vertex] + next.cost;
                    q.offer(new Node(nextNum, cost[cur.vertex] + next.cost));
                }
            }
        }
    }
}