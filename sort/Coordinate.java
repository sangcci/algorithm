package sort;

/*
[[ 좌표 정렬 ]]
2차원 배열을 만들어 뒷칸은 2칸으로 고정
첫번째부터 비교하여 정렬
이어서 두번째 원소 비교 및 정렬

오답이 나옴.. -> 직접 알고리즘을 구현하려 하지말고 라이브러리 기능을 적극 활용하자

좌표 구현할 떄 2가지 방법
1. 좌표값 2개를 담은 클래스(Comparable 인터페이스를 구현)를 만들어 CompareTo 메서드 구현
2. Arrays.sort()에 인자로 Comparator 객체 구현. 이 때 람다를 사용하던지 익명클래스 생성
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Coordinate {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int[][] arr = new int[N][2];
        for(int i = 0; i < arr.length; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });

        for(int i = 0; i < N; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }
}
