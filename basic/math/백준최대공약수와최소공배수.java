package basic.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준최대공약수와최소공배수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        int n1 = Integer.parseInt(input.nextToken());
        int n2 = Integer.parseInt(input.nextToken());

        // Greatest Common Measure with euclidean algorithm
        /*int min = Math.min(n1, n2);
        int gcm = 0;
        for (int i = 1; i <= min; i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                gcm = Math.max(gcm, i);
            }
        }*/

        int a = n1, b = n2;
        while (a % b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        int gcm = b;

        // Least Common Multiple
        int lsm = gcm * (n1 / gcm) * (n2 / gcm);

        // output
        System.out.println(gcm);
        System.out.println(lsm);
    }
}
