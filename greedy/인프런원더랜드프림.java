package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 인프런원더랜드프림 {
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int v = Integer.parseInt(token.nextToken());
        int e = Integer.parseInt(token.nextToken());

        // ArrayList graph (LinkedList graph)
        List<List<PrimEdge>> edges = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            StringTokenizer edgeToken = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(edgeToken.nextToken());
            int v2 = Integer.parseInt(edgeToken.nextToken());
            int cost = Integer.parseInt(edgeToken.nextToken());
            edges.get(v1).add(new PrimEdge(v2, cost));
            edges.get(v2).add(new PrimEdge(v1, cost));
        }

        // check array
        int[] chkArr = new int[v+1];
        Arrays.fill(chkArr, 0);

        // prim algorithm
        PriorityQueue<PrimEdge> q = new PriorityQueue<>();
        q.offer(new PrimEdge(1, 0)); // 1부터 보장되어 있음
        while (!q.isEmpty()) {
            PrimEdge cur = q.poll();
            if (chkArr[cur.vertex] == 1) {
                continue;
            }
            sum += cur.cost;
            chkArr[cur.vertex] = 1;
            for (PrimEdge next : edges.get(cur.vertex)) {
                q.offer(next);
            }
        }

        System.out.println(sum);
    }
}
class PrimEdge implements Comparable<PrimEdge> {
    int vertex;
    int cost;

    public PrimEdge(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(PrimEdge o) {
        return this.cost - o.cost;
    }
}
