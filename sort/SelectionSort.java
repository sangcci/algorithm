package sort;

import java.util.Scanner;

/*
[[ 선택 정렬 ]]
13 5 11 7 23 15
1. 첫번째 인덱스 비교하여 교체할거 교체
2. 인덱스 +1 하여 비교, 첫번째 인덱스 값은 고정됨.
3. 반복

유의
- 인덱스를 idx로 두고 인덱스 다음 값을 비교해야 하므로 for문에 i = idx+1로 잡는다.

*/
public class SelectionSort {
    public int[] solution(int[] arr) {
        int[] answer;
        int idx = 0;
        int tmp = 0;

        while(idx < arr.length) {
            for(int i = idx+1; i < arr.length; i++) {
                if(arr[i] < arr[idx]) {
                    tmp = arr[idx];
                    arr[idx] = arr[i];
                    arr[i] = tmp;
                }
            }
            idx++;
        }

        answer = arr;

        return answer;
    }


    public static void main(String[] args) {
        SelectionSort m = new SelectionSort();
        Scanner in = new Scanner(System.in);

        int count = in.nextInt();

        int[] arr = new int[count];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        for(int i : m.solution(arr)) {
            System.out.print(i + " ");
        }
    }
}
