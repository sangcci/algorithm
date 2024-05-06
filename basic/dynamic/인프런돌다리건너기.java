package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 인프런돌다리건너기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] answers = new int[n+1];
        answers[0] = 1;
        answers[1] = 2;
        for (int i = 2; i <= n; i++) {
            answers[i] = answers[i - 2] + answers[i - 1];
        }

        System.out.println(answers[n]);
    }
}
