package basic.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 인프런원더랜드크루스칼 {

    static int[] unf;
    static int sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int v = Integer.parseInt(token.nextToken());
        int e = Integer.parseInt(token.nextToken());

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            StringTokenizer edgeToken = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(edgeToken.nextToken());
            int v2 = Integer.parseInt(edgeToken.nextToken());
            int cost = Integer.parseInt(edgeToken.nextToken());
            edges.add(new Edge(v1, v2, cost));
        }
        Collections.sort(edges);

        unf = new int[v+1];
        for (int i = 1; i < unf.length; i++) {
            unf[i] = i;
        }

        sum = 0;
        for (int i = 0; i < e; i++) {
            Edge cur = edges.get(i);
            union(cur.v1, cur.v2, cur.cost);
        }

        System.out.println(sum);
    }

    public static void union(int v1, int v2, int cost) {
        v1 = find(v1);
        v2 = find(v2);
        if (v1 == v2) { // 집합 제외
        } else if (v1 < v2) {
            unf[v2] = v1;
            sum += cost;
        } else {
            unf[v1] = v2;
            sum += cost;
        }
    }

    public static int find(int node) {
        if (node == unf[node]) {
            return node;
        } else {
            return unf[node] = find(unf[node]);
        }
    }
}

class Edge implements Comparable<Edge>{

    int v1;
    int v2;
    int cost;

    public Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}
