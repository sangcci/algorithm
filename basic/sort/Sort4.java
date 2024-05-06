package basic.sort;

import java.util.Scanner;

/*
[[ 캐시 - Least Recently Used (카카오 변형문제) ]]

들어가는 순서를 알아야 한다.
캐시에 들어있는 작업들을 다 basic.search 해야함

Cache Miss와 Cache Hit를 잘 알아야 한다.

<배열 오른쪽으로 이동 방법>
         <-----i
cache | | | | | |
c[i] = c[i-1]

그 후 맨 처음 값을 설정해줘야 함
c[0] = p
 */

public class Sort4 {
    public int[] solution(int S, int[] pros) {
        int[] answer;
        int[] cache = new int[S];
        int rt; // cache miss

        for(int p : pros) {
            rt = S-1;
            // cache hit인지 miss인지 판단
            for(int i = 0; i < cache.length; i++) {
                if(cache[i] == p) {
                    rt = i; // index 시작 위치
                }
            }
            // 옮기기
            for(int i = rt; i > 0; i--) {
                cache[i] = cache[i-1];
            }
            cache[0] = p;
        }

        answer = cache;
        return answer;
    }

    public static void main(String[] args) {
        Sort4 m = new Sort4();
        Scanner in = new Scanner(System.in);

        int S = in.nextInt();
        int N = in.nextInt();

        int[] arr = new int[N];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        for(int i : m.solution(S, arr)) {
            System.out.print(i + " ");
        }
    }
}
