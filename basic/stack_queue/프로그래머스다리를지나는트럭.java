package basic.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 프로그래머스다리를지나는트럭 {

    static class Truck {
        int weight, dir;
        public Truck(int weight, int dir) {this.weight = weight; this.dir = dir;}
    }

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int bridge_length = Integer.parseInt(br.readLine());
        int weight = Integer.parseInt(br.readLine());
        int truck_count = Integer.parseInt(br.readLine());
        int[] truck_weights = new int[truck_count];
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < truck_count; i++) {
            truck_weights[i] = Integer.parseInt(input.nextToken());
        }

        // solution
        int result = solution(bridge_length, weight, truck_weights);

        // output
        System.out.println(result);
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0; int curWeight = 0; int i = 0;
        Queue<Truck> q = new LinkedList<>();

        while (true) {
            /*System.out.println("time: " + time);
            System.out.println("curWeight: " + curWeight);
            System.out.println("i: " + i);
            for (Truck each : q) {
                System.out.println(each.weight + " " + each.dir);
            }
            System.out.println();*/
            if (i == truck_weights.length) {
                break;
            }
            else {
                // check we can go to bridge
                if (curWeight + truck_weights[i] <= weight) {
                    q.add(new Truck(truck_weights[i], 0));
                    curWeight += truck_weights[i++];
                }

                // can not in
                if (q.peek().dir == bridge_length - 1) {
                    curWeight -= q.poll().weight;
                }

                // move 1 block
                for (Truck each : q) {
                    each.dir++;
                }

                time++;
            }
        }

        return time + bridge_length;
    }
}
