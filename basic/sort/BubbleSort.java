package basic.sort;

import java.util.Scanner;

/*
[[ 버블 정렬 ]]
13 5 11 7 23 15

1. 첫번째와 두번째 비교, 두번째와 세번째 비교 ... 끝까지
2. 끝에 인덱스 고정! 다음에는 첫번째 두번째 비교 ... 끝 인덱스 제외
3. 반복..

유의
- index 참조 넘지 않도록 조심 -> 에초에 rt를 arr.length -1로 설정하여 BoundOutOfException을 방지
*/

public class BubbleSort {
    public int[] solution(int[] arr) {
        int[] answer;
        int rt = arr.length - 1;
        int tmp = 0;

        while (rt > 0) {
            for (int idx = 0; idx < rt; idx++) {
                if (arr[idx] > arr[idx + 1]) {
                    tmp = arr[idx + 1];
                    arr[idx + 1] = arr[idx];
                    arr[idx] = tmp;
                }
            }
            rt--;
        }

        answer = arr;
        return answer;
    }


    public static void main(String[] args) {
        BubbleSort m = new BubbleSort();
        Scanner in = new Scanner(System.in);

        int count = in.nextInt();

        int[] arr = new int[count];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        for (int i : m.solution(arr)) {
            System.out.print(i + " ");
        }
    }
}
