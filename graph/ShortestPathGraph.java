package graph;

import java.util.*;
/*
[[ 그래프 최단거리 - BFS ]]

 */
public class ShortestPathGraph {
    public static ArrayList<ArrayList<Integer>> graph;
    public static int[] chkArr, dis;
    public static int N, M;
    public void BFS(int v) {
        Queue<Integer> q = new LinkedList<>();
        // 첫 시작은 0
        dis[v] = 0;

        q.offer(v);
        while(!q.isEmpty()) {
            int cv = q.poll();
            ArrayList<Integer> nvArr = graph.get(cv);
            for(int nv : nvArr) {
                if(chkArr[nv] == 0) {
                    dis[nv] = dis[cv] + 1;
                    chkArr[nv] = 1;
                    q.offer(nv);
                }
            }
        }
    }
    public static void main(String[] args) {
        ShortestPathGraph m = new ShortestPathGraph();
        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        graph = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }

        M = in.nextInt();
        for(int i = 0; i < M; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph.get(a).add(b);
        }

        // check 배열 초기화
        chkArr = new int[N + 1];
        chkArr[1] = 1;

        // dis 배열 초기화
        dis = new int[N + 1];

        // 최단거리 탐색
        m.BFS(1);

        // 출력
        for(int i = 1; i <= N; i++) {
            System.out.println(i + ":" + dis[i]);
        }
    }
}