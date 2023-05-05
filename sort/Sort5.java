package sort;
/*
[[ 중복 확인 - 탐색 알고리즘 ]]

처음 인덱스부터 순차적으로 비교 탐색 -> Timeout 발생 => O(n^2)

HashMap쓰는 방법도 존재 -> count 세고 다시 count값이 2 이상인 걸 고르는 방법 => O(n) 가능

정렬해서 탐색하면? -> 정렬 후 옆에 있는 값만 비교 =>  O(nlogn) + O(n) 레전드
이 때, 정렬은 O(nlogn)인 정렬을 사용해야 한다. Quick sort, Merge sort ...
Arrays 클래스에서 제공하는 오름차순 정렬 sort()메서드 사용
*/

import java.util.Arrays;
import java.util.Scanner;

public class Sort5 {
    public String solution(int[] arr) {
        String answer = "U";

        Arrays.sort(arr);

        for(int i = 0; i < arr.length-1; i++) {
            if(arr[i] == arr[i+1]) {
                answer = "D";
            }
        }

        /*
        for(int i = 0; i < arr.length; i++) {
            for(int j = i+1; j < arr.length; j++) {
                if(arr[i] == arr[j]) answer = "D";
            }
        }
         */

        return answer;
    }

    public static void main(String[] args) {
        Sort5 m = new Sort5();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        int[] arr = new int[N];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        System.out.print(m.solution(arr));
    }
}
