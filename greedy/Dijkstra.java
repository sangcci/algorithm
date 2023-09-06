package greedy;
/*
[[ 다익스트라 알고리즘 ]]
Java 알고리즘 강의 greedy
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
import java.util.*;
public class Dijkstra {
    static class Edge implements Comparable<Edge>{
        public int vertex;
        public int cost;
        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static ArrayList<ArrayList<Edge>> graph;
    static int N; // 정점 갯수
    static int M; // 간선 갯수
    static int[] dis;
    public void dijkstra(int me) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(me, 0));
        dis[me] = 0; // overflow로 인한 음수 현상 방지

        while (!pq.isEmpty()) {
            System.out.println("start step");
            Edge current = pq.poll();
            int currentVertex = current.vertex;
            int currentCost = current.cost;
            System.out.println("current node : " + currentVertex + " " + currentCost);

            if (currentCost > dis[currentVertex]) {
                continue;
            }
            for (Edge e : graph.get(currentVertex)) {
                int nextVertex = e.vertex;
                int nextCost = e.cost;
                System.out.println("next node : " + nextVertex + " " + nextCost);
                if (dis[currentVertex] + nextCost < dis[nextVertex]) {
                    System.out.println("dis currentVertex : " + dis[currentVertex]);
                    System.out.println("nextCost : " + nextCost);
                    dis[nextVertex] = dis[currentVertex] + nextCost;
                    System.out.println("offer!");
                    System.out.println("nextVertex " + dis[nextVertex]);
                    pq.offer(new Edge(nextVertex, dis[currentVertex] + nextCost));
                }
            }
            System.out.println("------");
        }
    }
    public static void main(String[] args) {
        Dijkstra m = new Dijkstra();
        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        M = in.nextInt();
        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            int cost = in.nextInt();
            graph.get(start).add(new Edge(end, cost));
        }
        dis = new int[N + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);

        m.dijkstra(1);

        for (int i = 2; i < N + 1; i++) {
            if (dis[i] == Integer.MAX_VALUE) {
                System.out.println(i + " : IMPOSSIBLE");
            } else {
                System.out.println(i + " : " + dis[i]);
            }
        }
    }
}