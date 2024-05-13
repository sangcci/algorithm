package basic.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEAContact {

    static int n;
    static int start;
    static Map<Integer, List<Integer>> graph;
    static boolean[] visited;
    static List<Node> results;

    static class Node implements Comparable<Node> {

        int num, lv;

        public Node(int num, int lv) {
            this.num = num;
            this.lv = lv;
        }

        @Override
        public int compareTo(Node o) {
            if (this.lv == o.lv) return o.num - this.num;
            else return o.lv - this.lv;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(input.nextToken());
            start = Integer.parseInt(input.nextToken());

            graph = new HashMap<>();
            StringTokenizer inputGraph = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n / 2; i++) {
                int st = Integer.parseInt(inputGraph.nextToken());
                int ed = Integer.parseInt(inputGraph.nextToken());

                graph.computeIfAbsent(st, k -> new ArrayList<>());

                List<Integer> graphList = graph.get(st);
                graphList.add(ed);
            }
            visited = new boolean[101];
            results = new ArrayList<>();

            bfs();
            int answer = calMaxNum();

            output.append("#" + test_case + " " + answer + "\n");
        }
        System.out.println(output);
    }

    public static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        visited[start] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            //visited[cur.num] = true;
            // don't go next, add results
            List<Integer> nextNums = graph.get(cur.num);
            if (nextNums == null) {
                results.add(cur);
            }
            // go next
            else {
                boolean flag = true;
                for (int nextNum : nextNums) {
                    if (!visited[nextNum]) {
                        flag = false;
                        visited[nextNum] = true;
                        q.offer(new Node(nextNum, cur.lv + 1));
                    }
                }
                if (flag) results.add(cur);
            }
        }
    }

    public static int calMaxNum() {
        // find max lv, max num
        Collections.sort(results);
        /*for (Node node : results) {
            System.out.println(node.lv + " " + node.num);
        }*/
        return results.get(0).num;
    }
}
