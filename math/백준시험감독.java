package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준시험감독 {

    static int mainSupervisorCover;
    static int subSupervisorCover;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] room = new int[n];

        StringTokenizer takers = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            room[i] = Integer.parseInt(takers.nextToken());
        }

        StringTokenizer supervisorCover = new StringTokenizer(br.readLine());
        mainSupervisorCover = Integer.parseInt(supervisorCover.nextToken());
        subSupervisorCover = Integer.parseInt(supervisorCover.nextToken());

        long result = solution(room);
        System.out.println(result);
    }

    public static long solution(int[] room) {
        long sum = 0;
        for (int taker : room) {
            sum += calculateSupervisor(taker);
        }
        return sum;
    }

    public static long calculateSupervisor(int taker) {
        long mainNeed = 1;
        if (taker <= mainSupervisorCover) return mainNeed;
        else {
            double subNeed = (double) (taker - mainSupervisorCover) / subSupervisorCover;
            return mainNeed + (long) Math.ceil(subNeed);
        }
    }
}
