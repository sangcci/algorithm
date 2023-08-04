package stack;
/**
 [[ 프로그래머스 2단계 뒤에 있는 큰 수 찾기 ]]
 유형: 탐색 문제 (Stack 이용)
 numbers 원소 수만큼 반복
 - 한 사이클
 - 뒷 큰수 찾기, 찾으면 result 배열에 추가
 - 만일 뒷큰수가 없다면 -1
 - 마지막 원소는 무조껀 -1
 -> 가능. 하지만 최악의 경우 O(n^2)이라 비효율적, 테스트 통과X
 시간복잡도가 좀 더 낮아야 한다.

 - 반대로 비교하면 어떨까? 배열의 맨 마지막부터 비교하면 어떨까?
 - 비교하고 젤 큰 수를 따로 max에 저장
 - 그 다음 사이클부터는 바로 앞값과 max만 비교하면 됨.
 - O(n)으로 가능할듯
 -> 논리 상 말이 안됨. 제일 가까이에 있는 수를 찾을 수가 없다.

 - Stack 이용
 - Stack을 이용하여 numbers 원소를 점차 쌓아가면서 동시에 비교
 */
import java.util.*;
class Node {
    int index;
    int value;
    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }
}
public class 뒤에있는큰수찾기 {
    public int[] solution(int[] numbers) {
        // init
        int[] result = new int[numbers.length];
        Arrays.fill(result, -1);
        Stack<Node> s = new Stack<>();
        s.push(new Node(0, numbers[0]));

        // search
        for (int i = 1; i < numbers.length; i++) {
            while (!s.isEmpty() && numbers[i] > s.peek().value) {
                result[s.peek().index] = numbers[i];
                s.pop();
            }
            s.push(new Node(i, numbers[i]));
        }
        return result;
    }
}