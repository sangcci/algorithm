package basic.search;
/*
[[ 뮤직비디오(결정알고리즘) ]]
대부분 "~~ 최소 구해라" 하면 그냥 이분검색으로 일정 값 잡고 좁혀나가자
"더 좋은 답을 찾기 위한 과정"
스무고개 느낌임. 이거돼? 안돼? 그럼 이거는 돼?

👉 key point
순서대로 읽으면서 쪼개야 된다. 어느 지점에서 쪼개는지의 기준이 필요
- 1번 곡이 3분짜리, 2번 곡이 8분짜리 이렇게 시간도 곡 순서마다 다르다.
- 쪼개는 방법을 모르겠다... -> 2장에 담을 수 있다? 그럼 3장도 가능하다는 소리!
-> 무조껀 3보다 count 수가 작기만 하면 된다!

❗ 문제의 요구사항 파악
-> DVD의 최소 용량 크기
이를 이분검색으로 좁혀나가는 방식 사용
-> DVD에 몇 장의 DVD가 나오는지 파악하면 용량 크기 판단 가능

최소 가능 크기 -> DVD M장 중 1장 크기 -> 곡 중에서 가장 minute 수가 높은 수
최대 가능 크기 -> DVD 1장 크기중 1장 -> 곡 다 합한 걸 1개에 다 담는거니까 -> minute 수 다 더한 수

❗ 문제 풀기
DVD 장수를 보며 최소 용량인지 아닌지 판단 가능! (DVD 1장에 들어갈만큼 들어갔다면 1장 count)
DVD 크기를 잡고 1번 곡부터 차례대로 minute 수를 더함.
DVD 크기가 n번째 더할 때 크기보다 넘어간다면 -> count++ 및 다시 초기화
이를 통해 count 수를 구한다.

 */
import java.util.Arrays;
import java.util.Scanner;

public class DicisionAlgorithm1 {
    public int solution(int N, int M, int[] singsMinute) {
        int answer = 0;

        int[] sortSingsMinute = singsMinute.clone();
        Arrays.sort(sortSingsMinute);
        int lt = sortSingsMinute[N-1];

        int rt = 0;
        for(Integer i : sortSingsMinute) {
            rt += i;
        }

        // count 수가 M보다 작거나 같아야지만 mid값이 최소값이 되는 것 -> mid가 무조껀 정답이 아니다.
        while(lt <= rt) {
            int mid = (rt + lt)/2;
            if(count(singsMinute, mid) <= M) {
                rt = mid-1;
                answer = mid;
            } else {
                lt = mid+1;
            }
        }
        return answer;
    }

    public int count(int[] singsMinute, int capacity) {
        int totalMinute = 0;
        int count = 1; // 무조껀 1장 이상 필요

        for(int sing : singsMinute) {
            // 쪼개진다는건 무조껀 1장에서 2장이 되는거임
            if(totalMinute + sing > capacity) {
                count++;
                totalMinute = sing; // 새로운 DVD 장이 시작되는것이므로 지금 for문의 sing 값을 넣어줘야 함
            } else totalMinute += sing;
        }
        return count;
    }

    public static void main(String[] args) {
        DicisionAlgorithm1 m = new DicisionAlgorithm1();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int M = in.nextInt();

        int[] singsMinute = new int[N];
        for(int i = 0; i < singsMinute.length; i++) {
            singsMinute[i] = in.nextInt();
        }

        System.out.print(m.solution(N, M, singsMinute));
    }
}
