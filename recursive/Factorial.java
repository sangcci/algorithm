package recursive;
/*
[[ 팩토리얼 - 재귀 ]]
필드값을 공유하기 위해서는 return 값에 DFS(재귀함수)를 넣는 방법을 고려해보자
 */
import java.util.Scanner;

public class Factorial {
    public int DFS(int N) {

        if(N == 1) return 1;
        else {
            return N * DFS(N-1);
        }
    }


    public static void main(String[] args) {
        Factorial m = new Factorial();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        System.out.println(m.DFS(N));
    }
}
