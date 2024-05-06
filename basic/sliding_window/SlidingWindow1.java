package basic.sliding_window;

/*
sliding window algorithm
runtime error를 피하기 위한 전략
 */
import java.util.*;

class SlidingWindow1 {
    public int solution(int[] arr, int k) {
        int max_salesprice = 0;
        int first_index = 0;
        int last_index = k - 1;

        // 첫번째 매출액
        int next_salesprice = 0;
        for (int i = 0; i <= last_index; i++) {
            next_salesprice += arr[i];
        }

        // first_index 제거, last_index 추가
        while (last_index <= (arr.length - k)) {
            if (max_salesprice < next_salesprice) {
                max_salesprice = next_salesprice;
            }
            next_salesprice -= arr[first_index++];
            next_salesprice += arr[++last_index];
        }
        return max_salesprice;
    }

    public static void main(String[] args) {
        SlidingWindow1 m = new SlidingWindow1();
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int k = s.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        System.out.println(m.solution(arr, k));
    }
}