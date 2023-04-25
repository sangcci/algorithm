
/*
[[ 쇠막대기 (한국정보올림피아드) ]]
특징
- '('가 연속으로 온다면 막대를 표현 -> 막대 갯수 세어야 함
- '(' 다음으로 ')'가 온다면 짜르기 -> 쌓인 막대를 다 자르는 것이므로 2 나누기
- ')' 이후로 ')'가 온다면 막대의 끝을 표현
 */

import java.util.*;

/*
[[  ]]

 */
public class Main {
    public int[] solution(int[] arr) {
        int[] answer;

        return answer;
    }


    public static void main(String[] args) {
        Main m = new Main();
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