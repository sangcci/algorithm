package basic.search;
/*
[[ 로또의 최고 순위와 최저 순위 ]]
프로그래머스 1단계 문제
 */
import java.util.Scanner;

public class Lottos {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        // Map<Integer, Boolean> map = new HashMap<>();
        int min = 7;
        // 같은 로또 번호가 있는지 탐색
        // 성능 좋은 Search 알고리즘을 사용
        // 혹은 Hash가 적용된 Map 사용
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
                if(lottos[i] == win_nums[j]) min--;
            }
        }
        // 0의 갯수 파악
        int max = min;
        for(int i = 0; i < 6; i++) {
            if(lottos[i] == 0) max--;
        }

        if(min == 7) min = 6;
        if(max == 7) max = 6;

        answer[0] = max;
        answer[1] = min;
        return answer;
    }

    public static void main(String[] args) {
        Lottos m = new Lottos();
        Scanner in = new Scanner(System.in);

        int[] lottos = new int[6];
        for(int i = 0; i < lottos.length; i++) lottos[i] = in.nextInt();
        int[] win_nums = new int[6];
        for(int i = 0; i < win_nums.length; i++) win_nums[i] = in.nextInt();

        for(int e : m.solution(lottos, win_nums))
            System.out.print(e + " ");

    }
}
