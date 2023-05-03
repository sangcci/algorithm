package sort;

import java.util.Scanner;

/*
[[ 삽입 정렬 ]]
13 5 11 7 23 15

1. 뒤에를 인덱스로 둔다.
2. 앞에꺼와 비교 후 자기보다 작으면 위치 바꿈

while 쓰고 안에서 for을 하는게 보기 좋은듯?
 */

public class InsertionSort {
    public int[] solution(int[] arr) {
        int[] answer;

        int rt = 1;
        int tmp = 0;

        while(rt < arr.length) {
            for(int lt = 0; lt < rt; lt++) {
                if(arr[rt] < arr[lt]) {
                    tmp = arr[rt];
                    arr[rt] = arr[lt];
                    arr[lt] = tmp;
                }
            }
            rt++;
        }

        answer = arr;
        return answer;
    }


    public static void main(String[] args) {
        InsertionSort m = new InsertionSort();
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
