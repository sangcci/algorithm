package basic.recursive;
/*
[[ 이진수 출력 - 재귀 ]]

1. 입력 값을 자리수로 나눠서 각각 2^i 곱할려 했는데 안됨, 2진수가 아니라 10진수가 나옴

2. 2로 나눠서 몫 값을 저장
일일이 배열에다 2진수 값을 하나하나 담을 필요 없음
그냥 하나씩 출력하면 될 일, 쉽게 생각하자
 */
import java.util.Scanner;

public class PrintBinary {
    public void DFS(int N) {
        if(N > 0) {
            DFS(N/2);
            System.out.print(N%2);
        }
    }

    public static void main(String[] args) {
        PrintBinary m = new PrintBinary();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        m.DFS(N);
    }
}
