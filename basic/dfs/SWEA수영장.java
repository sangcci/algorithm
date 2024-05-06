package basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA수영장 {
    static int[] fee;
    static int[] plan;
    static final int DAY = 0;
    static final int ONE_MONTH = 1;
    static final int THREE_MONTH = 2;
    static final int ONE_YEAR = 3;
    static int min = 3001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            fee = new int[4];
            StringTokenizer inputA = new StringTokenizer(br.readLine());
            for (int i = 0; i < fee.length; i++) {
                fee[i] = Integer.parseInt(inputA.nextToken());
            }

            plan = new int[12];
            StringTokenizer inputB = new StringTokenizer(br.readLine());
            for (int i = 0; i < plan.length; i++) {
                plan[i] = Integer.parseInt(inputB.nextToken());
            }

            dfs(0, 0);

            answer.append("#")
                    .append(test_case)
                    .append(" ")
                    .append(min)
                    .append("\n");

            min = 3001;
        }

        System.out.println(answer);
    }

    public static void dfs(int money, int month) {
        if (month >= 12) {
            min = Math.min(min, money);
        } else {
            // 이번 달에 수영장을 이용하지 않는다면
            if (plan[month] == 0) {
                dfs(money, month+1);
            } else { // 이번 달에 수영장을 이용한다면
                // 3달 사기
                dfs(money + fee[THREE_MONTH], month + 3);
                // 1달 사기
                dfs(money + fee[ONE_MONTH], month + 1);
                // 1일치 한달 사기
                dfs(money + (fee[DAY] * plan[month]), month + 1);
                // 1년치
                if (month == 0) {
                    dfs(money + fee[ONE_YEAR], month + 12);
                }
            }
        }
    }
}
