package basic.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
7
1 0 2 0 1 0 1
0 2 0 0 0 0 0
0 0 1 0 0 1 0
0 0 0 0 1 2 2
0 0 0 0 0 1 0
0 0 2 1 0 2 1
0 0 1 2 2 0 2
 */

public class SWEAMagnetic {

    static int n;
    static int[][] table;
    static final int N = 1;
    static final int S = 2;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());

            table = new int[n][n];
            for (int y = 0; y < n; y++) {
                StringTokenizer input = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < n; x++) {
                    table[y][x] = Integer.parseInt(input.nextToken());
                }
            }

            cnt = 0;

            deadlock();

            answer.append("#" + test_case + " " + cnt + "\n");
        }
        System.out.println(answer);
    }

    public static void deadlock() {
        Stack<Integer> s = new Stack<>();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int next = table[y][x];
                if (next == N) {
                    if (s.size() < 1) {
                        s.push(next);
                    }
                } else if (next == S) {
                    if (s.size() >= 1) {
                        s.pop();
                        cnt++;
                    }
                }
            }
            s.clear();
        }
    }
}
