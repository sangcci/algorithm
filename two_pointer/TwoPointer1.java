package two_pointer;

/*
    two pointer algorithm
    두개의 pointer를 두어 사용
    여기서는 배열의 index들을 2개 사용
 */
import java.util.*;

class TwoPointer1 {
    public int[] solution(int[] arr1, int[] arr2) {
        int arr1_index = 0;
        int arr2_index = 0;

        int ansLen = arr1.length + arr2.length - 2;
        int[] answer = new int[ansLen];

        for (int i = 0; i < ansLen; i++) {
            if (arr1[arr1_index] <= arr2[arr2_index]) {
                answer[i] = arr1[arr1_index];
                arr1_index++;
            } else {
                answer[i] = arr2[arr2_index];
                arr2_index++;
            }
        }
        return answer;
    }

    public int[] inputInt(Scanner s) {
        int num = s.nextInt();
        int[] arr = new int[num + 1];
        for (int i = 0; i < num; i++) {
            arr[i] = s.nextInt();
        }
        // 배열 마지막 칸 생성
        arr[num] = 101;
        return arr;
    }

    public static void main(String[] args) {
        TwoPointer1 m = new TwoPointer1();
        Scanner s = new Scanner(System.in);

        int[] arr1 = m.inputInt(s);
        int[] arr2 = m.inputInt(s);

        int[] answer = m.solution(arr1, arr2);

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}