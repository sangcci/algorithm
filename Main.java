/*
[[  ]]

 */
import java.util.*;

public class Main {

    public int[][] solution(int[][] arr) {

        return arr;
    }

    public static void main(String[] args) {
        //Main m = new Main();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int[][] arr = new int[N][2];
        for(int i = 0; i < arr.length; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }

        for(int i = 0; i < N; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }
}