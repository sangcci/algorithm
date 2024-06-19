package basic.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 프로그래머스더맵게 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] scoville = new int[n];
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            scoville[i] = Integer.parseInt(input.nextToken());
        }
        int k = Integer.parseInt(br.readLine());

        int result = new 프로그래머스더맵게().solution(scoville, k);

        System.out.println(result);
    }

    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.stream(scoville).forEach(pq::add);

        int cnt = 0;
        while (true) {
            int peek = pq.peek();
            if (peek >= K) {
                break;
            } else {
                if (pq.size() == 1) {
                    cnt = -1;
                    break;
                }
                int leastK = pq.poll();
                int secondK = pq.poll();
                int next = leastK + (secondK * 2);
                pq.offer(next);
            }
            cnt++;
        }
        return cnt;
    }
}
