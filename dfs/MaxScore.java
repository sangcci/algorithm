package dfs;
/*
[[ 최대 점수 구하기 DFS ]]

 */
import java.util.Scanner;

public class MaxScore {
    static int N, M; // 문제 갯수, 제한 시간
    static int[][] prob; // 문제 점수, 걸리는 시간
    static int answer = 0;
    public void dfs(int score, int time, int level) {
        if (time > M || level > N) return; // low latency
        if (level == N) {
            if (time <= M && score > answer) { // max score
                // System.out.println(score + " " + time + " " + level);
                answer = score;
            }
        } else {
            // System.out.println(score + " " + time + " " + level);
            dfs(score + prob[level][0], time + prob[level][1], level + 1);
            dfs(score, time, level + 1);
        }
    }

    public static void main(String[] args) {
        MaxScore m = new MaxScore();
        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        M = in.nextInt();

        // init
        prob = new int[N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                prob[i][j] = in.nextInt();
            }
        }

        // dfs
        m.dfs(0, 0, 0);

        // output answer
        System.out.print(answer);
    }
}
