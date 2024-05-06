package advanced.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KnapsackBacktracking {

    static int n, k;
    static class Node implements Comparable<Node> {
        int s;
        int p;
        public Node(int s, int p) {
            this.s = s;
            this.p = p;
        }

        @Override
        public int compareTo(Node o) {
            double thisValuePerWeight = (double) this.p / this.s;
            double oValuePerWeight = (double) o.p / o.s;

            // 먼저 가성비를 비교합니다.
            if (thisValuePerWeight > oValuePerWeight) {
                return 1;
            } else if (thisValuePerWeight < oValuePerWeight) {
                return -1;
            } else {
                // 가성비가 같다면, 무게를 비교하여 가벼운 것이 앞으로 오도록 합니다.
                return this.s - o.s;
            }
        }
    }
    static Node[] nodes;
    static int max = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(input.nextToken());
        k = Integer.parseInt(input.nextToken());

        nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer inputNode = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(inputNode.nextToken());
            int p = Integer.parseInt(inputNode.nextToken());
            nodes[i] = new Node(s, p);
        }

        Arrays.sort(nodes);

        /*for (Node each : nodes) {
            System.out.println(each.s + " " + each.p);
        }
        System.out.println();*/

        knapsack(0, 0, 0);

        System.out.println(max);
    }

    public static void knapsack(int level, int size, int value) {
        //System.out.println("level: " + level + " size: " + size + " value: " + value);
        if (size > k) return;
        if (level >= n) {
            //System.out.println("끝까지 도달");
            max = Math.max(max, value);
        } else {
            Node cur = nodes[level];
            // 선택 O
            if (size + cur.s <= k) {
                int remainSize = k - (size + cur.s);
                if (value + cur.p + fractional(level + 1, remainSize) > max) {
                    knapsack(level + 1, size + cur.s, value + cur.p);
                }
            }
            // 선택 X
            int remainSize = k - size;
            if (value + fractional(level + 1, remainSize) > max) {
                knapsack(level + 1, size, value);
            }
        }
    }

    private static int fractional(int start, int remainSize) {
        int size = 0, value = 0;
        for (int i = start; i < n; i++) {
            if (size + nodes[i].s > remainSize) {
                value += (remainSize - size) * (nodes[i].p / nodes[i].s);
                break;
            } else {
                value += nodes[i].p;
            }
        }
        return value;
    }
}
