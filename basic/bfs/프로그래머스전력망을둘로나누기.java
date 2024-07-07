package basic.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 프로그래머스전력망을둘로나누기 {

    int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] wires = new int[n-1][2];
        for (int i = 0; i < n-1; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 2; j++) {
                wires[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        int result = new 프로그래머스전력망을둘로나누기().solution(n, wires);

        System.out.println(result);
    }

    public int solution(int n, int[][] wires) {
        // init map
        min = Integer.MAX_VALUE;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] wire : wires) {
            // 1
            if (map.getOrDefault(wire[0], null) == null) {
                map.put(wire[0], new ArrayList<>());
            }
            List<Integer> list1 = map.get(wire[0]);
            list1.add(wire[1]);
            // 2
            if (map.getOrDefault(wire[1], null) == null) {
                map.put(wire[1], new ArrayList<>());
            }
            List<Integer> list2 = map.get(wire[1]);
            list2.add(wire[0]);
        }

        // number of cases
        for (int[] wire : wires) {
            // remove
            List<Integer> list1 = map.get(wire[0]);
            list1.remove(list1.indexOf(wire[1]));
            List<Integer> list2 = map.get(wire[1]);
            list2.remove(list2.indexOf(wire[0]));

            // count
            int count = bfs(map, n);

            // compare
            min = Math.min(min, count);

            // roll back
            list1.add(wire[1]);
            list2.add(wire[0]);
        }
        return min;
    }

    public int bfs(Map<Integer, List<Integer>> map, int n) {
        int count = 0;
        int startKey = 1;
        boolean[] visited = new boolean[n+1];

        // network 1
        Queue<Integer> q = new LinkedList<>();
        q.offer(startKey);
        while (!q.isEmpty()) {
            count++;
            int startNum = q.poll();
            visited[startNum] = true;
            List<Integer> nextNumList = map.get(startNum);
            for (int nextNum : nextNumList) {
                if (!visited[nextNum]) {
                    q.offer(nextNum);
                }
            }
        }

        // network 2
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                startKey = i;
                break;
            }
        }
        Queue<Integer> q2 = new LinkedList<>();
        q2.offer(startKey);
        while (!q2.isEmpty()) {
            count--;
            int startNum = q2.poll();
            visited[startNum] = true;
            List<Integer> nextNumList = map.get(startNum);
            for (int nextNum : nextNumList) {
                if (!visited[nextNum]) {
                    q2.offer(nextNum);
                }
            }
        }

        return Math.abs(count);
    }

    public static void testPrint(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                sb.append(arr[r][c] + " ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void testPrint(Map<Integer, List<Integer>> map) {
        StringBuilder sb = new StringBuilder();
        Set<Integer> keySet = map.keySet();
        for (int key : keySet) {
            List<Integer> list = map.get(key);
            sb.append(key + " : ");
            for (int node : list) {
                sb.append(node + " ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
