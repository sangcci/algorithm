package basic.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 프로그래머스디스크컨트롤러 {

    static class Job {

        int startTime, processingTime;

        public Job(int startTime, int processingTime) {
            this.startTime = startTime;
            this.processingTime = processingTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            jobs[i][0] = Integer.parseInt(input.nextToken());
            jobs[i][1] = Integer.parseInt(input.nextToken());
        }

        int result = new 프로그래머스디스크컨트롤러().solution(jobs);
        System.out.println(result);
    }

    public int solution(int[][] jobs) {
        List<Job> jobList = new ArrayList<>();
        for (int[] job : jobs) {
            jobList.add(new Job(job[0], job[1]));
        }
        jobList.sort((o1, o2) -> o1.startTime - o2.startTime);

        int answer = 0;
        int curTime = 0; int nextJobIdx = 0; int count = 0;
        PriorityQueue<Job> pq = new PriorityQueue<>((o1, o2) -> o1.processingTime - o2.processingTime);

        while (count < jobList.size()) {
            while (nextJobIdx < jobList.size() && jobList.get(nextJobIdx).startTime <= curTime) {
                pq.offer(jobList.get(nextJobIdx));
                nextJobIdx++;
            }

            if (pq.isEmpty()) {
                curTime = jobList.get(nextJobIdx).startTime;
            } else {
                Job job = pq.poll();
                curTime += job.processingTime;
                answer += curTime - job.startTime;
                count++;
            }
        }

        return answer / jobs.length;
    }
}
