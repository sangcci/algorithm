package basic.prime_number;

/*
recursion으로 구현
O(n^2) for문 2번 써야되서
에레토스테네스의 체 -> 1/2로 줄어듬
 */
import java.util.*;

public class PrimeRecursion {
    public int solution(int n) {
        int cnt = 0;
        if (n == 1)
            return 0;
        else {
            solution(n - 1);

            for (int i = 2; i < n; i++) {
                if (n % i == 0)
                    cnt++;
            }
            if (cnt == 0)
                System.out.print(n + " ");
        }
        return 0;
    }

    public static void main(String[] args) {
        PrimeRecursion m = new PrimeRecursion();
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        m.solution(n);
    }
}