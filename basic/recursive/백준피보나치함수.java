package basic.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준피보나치함수 {

    static int[] cnt_0, cnt_1;
    static int[] memoization;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) {
                output.append(1 + " " + 0 + "\n");
            } else if (n == 1) {
                output.append(0 + " " + 1 + "\n");
            } else {
                fibonacci(n);
                output.append(cnt_0[n] + " " + cnt_1[n] + "\n");
            }
        }
        System.out.println(output);
    }

    public static void fibonacci(int n) {
        cnt_0 = new int[n+1];
        cnt_1 = new int[n+1];

        cnt_0[0] = 1; cnt_1[0] = 0;
        cnt_0[1] = 0; cnt_1[1] = 1;

        for (int i = 2; i <= n; i++) {
            cnt_0[i] = cnt_0[i-1] + cnt_0[i-2];
            cnt_1[i] = cnt_1[i-1] + cnt_1[i-2];
        }
    }
}
