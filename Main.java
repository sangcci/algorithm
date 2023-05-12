/*
[[  ]]

 */
import java.util.*;

public class Main {

    public int solution(int N, int M, int[] singsMinute) {
        int answer = 0;

        return answer;
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int M = in.nextInt();

        int[] singsMinute = new int[N];
        for(int i = 0; i < singsMinute.length; i++) {
            singsMinute[i] = in.nextInt();
        }

        System.out.print(m.solution(N, M, singsMinute));
    }
}