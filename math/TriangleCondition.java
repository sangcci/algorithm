package math;
/*
[[ 삼각형의 완성 조건 ]]

젤 큰 변 길이가 나머지 두 변 길이의 합 보다 작아야 한다
1. n1>n2, n1<n2 경우 나눔 -> 그냥 정렬 했음.
2. n1>n2 기준으로 => n1+n2 > n3이 될 수 있는 경우의 수 > n1-n2

1. 6 < n3+3 => n2 < n3+n1 => n3 > n2-n1
2. n3 < 6+3 => n3 < n1+n2
3. => n2-n1 < n3 < n1+n2
 */
import java.util.Scanner;

public class TriangleCondition {
    public int solution(int n1, int n2) {
        int count = 0;
        int tmp;

        if(n1 < n2) {
            tmp = n1;
            n1 = n2;
            n2 = tmp;
        }

        for(int i = 1; i < n1+n2; i++) {
            if ((i > n1-n2) && (i < n1+n2)) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        TriangleCondition m = new TriangleCondition();
        Scanner in = new Scanner(System.in);

        int n1 = in.nextInt();
        int n2 = in.nextInt();

        System.out.print(m.solution(n1, n2));
    }
}
