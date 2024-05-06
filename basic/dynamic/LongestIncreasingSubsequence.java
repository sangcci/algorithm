package basic.dynamic;

/*
[[ 최장 증가 부분 수열(LIS) ]]
Java 알고리즘 강의
 */
import java.util.*;
public class LongestIncreasingSubsequence {
    public int dynamic(int[] initArr) {
        int[] dy = new int[initArr.length];

        if (initArr[1] > initArr[0]) {
            dy[1] = dy[0]++;
        }
        dy[0] = 1;
        for (int i = 1; i < initArr.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (initArr[i] > initArr[j]) {
                    dy[i] = Math.max(dy[i], dy[j] + 1);
                } else {
                    dy[i] = Math.max(dy[i], 1);
                }
            }
        }
        Arrays.stream(dy).forEach((e) -> System.out.print(e + " "));
        System.out.println();
        return Arrays.stream(dy).max().orElseThrow(NoSuchElementException::new);
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence m = new LongestIncreasingSubsequence();
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] initArr = new int[n];
        for (int i = 0; i < n; i++) {
            initArr[i] = in.nextInt();
        }

        int result = m.dynamic(initArr);
        System.out.println(result);

    }
}
