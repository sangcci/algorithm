package basic.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 프로그래머스기능개발 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] progresses = new int[n];
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            progresses[i] = Integer.parseInt(input.nextToken());
        }
        int[] speeds = new int[n];
        StringTokenizer input2 = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            speeds[i] = Integer.parseInt(input2.nextToken());
        }

        프로그래머스기능개발 s = new 프로그래머스기능개발();
        int[] result = s.solution(progresses, speeds);

        StringBuilder sb = new StringBuilder();
        for (int each : result) {
            sb.append(each + " ");
        }
        sb.append("\n");
        System.out.println(sb);
    }

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            q.offer(i);
        }

        while (!q.isEmpty()) {
            // add speed on time
            for (int i : q) {
                progresses[i] += speeds[i];
            }
            // find 100% over
            int cnt = 0;
            while (!q.isEmpty()) {
                int index = q.peek();
                if (progresses[index] >= 100) {
                    cnt++;
                    q.poll();
                } else {
                    break;
                }
            }
            if (cnt >= 1) {
                answer.add(cnt);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}