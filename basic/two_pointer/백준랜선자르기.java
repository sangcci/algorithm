package basic.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준랜선자르기 {

    static int n, k;
    static long answer;
    static long[] lans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(input.nextToken());
        k = Integer.parseInt(input.nextToken());

        lans = new long[n];
        for (int i = 0; i < n; i++) {
            lans[i] = Integer.parseInt(br.readLine());
        }
        answer = 0;

        Arrays.sort(lans);
        long lt = 0, rt = lans[n - 1];
        while (lt <= rt) {
            long mid = (rt + lt) / 2;
            //System.out.println("lt: " + lt + " rt: " + rt);
            //System.out.println("mid: " + mid);
            if (mid == 0) {
                answer = Math.max(answer, 1);
                break;
            }
            if (divideLan(mid) >= k) {
                answer = Math.max(answer, mid);
                lt = mid + 1;
            } else {
                rt = mid - 1;
            }
        }

        System.out.println(answer);
    }

    public static int divideLan(long length) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            long lan = lans[i];
            cnt += (int) (lan / length);
        }
        //System.out.println("cnt: " + cnt);
        return cnt;
    }
}
