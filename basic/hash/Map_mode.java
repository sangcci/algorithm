package basic.hash;

import java.util.*;

/*
[[ 프로그래머스 - 최빈값 구하기 ]]

절차
숫자와 횟수 간의 관계가 key-value 관계인 것 같았다.
Map을 이용하여 숫자와 횟수를 한 세트로 묶어 저장

Map이 없으면 배열을 한개 더 만들어서 이를 대입해야 한다.
배열을 만들 때, 배열의 크기는 최대 숫자보다 +1을 해야 한다.
이후 배열의 인덱스 값을 숫자, 인덱스 안의 값을 횟수로 하여 구한다.

유의
- 최빈값이 영어로 mode임
- 원소값이 0부터 시작하기 때문에 조건을 2개 나둬야 할듯
 */
public class Map_mode {
    public int solution(int[] array) {
        int answer = 0;
        int max_count = 0;
        int mode_key = -1;
        int mode_value = 0;

        Map<Integer, Integer> mode = new HashMap<>();

        for(Integer number : array) {
            int count = mode.getOrDefault(number, 0) + 1;
            mode.put(number, count);
            /*
            if(mode.getOrDefault(number, 0) == 0) {
                mode.put(number, 1);
            }
            else {
                mode.put(number, mode.get(number) + 1);
            }
             */

            if(max_count < count) {
                max_count = count;
                answer = number;
            }
            else if(max_count == count) {
                answer = -1;
            }

        }
        /*
        Set<Integer> integers = mode.keySet();

        for(int i : integers) {
            if(mode_value < mode.get(i)) {
                mode_key = i;
                mode_value = mode.get(i);
            }
        }

        for(int i : integers) {
            if((mode.get(mode_key) == mode.get(i)) && mode_key != i) {
                mode_key = -1;
            }
        }

        answer = mode_key;

         */
        return answer;
    }


    public static void main(String[] args) {
        Map_mode m = new Map_mode();
        Scanner in = new Scanner(System.in);

        int size = in.nextInt();

        int[] array = new int[size];
        for(int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }

        System.out.println(m.solution(array));

    }
}