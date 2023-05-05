/*
[[ ]]

 */
import java.util.*;

public class Main {
    public String solution(int[] arr) {
        String answer = "";
        return answer;
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        int[] arr = new int[N];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        System.out.print(m.solution(arr));
    }
}