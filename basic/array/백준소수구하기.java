package basic.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준소수구하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        int m = Integer.parseInt(input.nextToken());
        int n = Integer.parseInt(input.nextToken());
        boolean[] prime = new boolean[n+1];

        // 에라토스테네스의 체
        prime[1] = true;
        int find = (int) Math.sqrt(n);
        for (int i = 2; i <= find; i++) {
            if (prime[i]) continue;
            // prime 거르기
            for (int num = i + i; num <= n; num += i) {
                // not prime
                if (!prime[num] && num % i == 0) {
                    prime[num] = true;
                }
            }
        }

        // output
        for (int i = m; i <= n; i++) {
            if (!prime[i]) {
                System.out.println(i);
            }
        }

    }
}
