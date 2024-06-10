package basic.stack_queue;

import java.util.Scanner;
import java.util.Stack;
/*
[[ 쇠막대기 ]]

 */
public class IronStick {
    public int solution(String s) {
        // init
        int count = 0;
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        /*
         순서
         - 레이저가 나올 시 이전까지 stack에 있던 막대기 수만큼 count 더하기
         - 막대기가 나올 시 막대기가 끝났음을 알고 count++
         판단 로직
         - 레이저: 이전 인덱스 문자가 '('(여는 괄호)일 때
         - 막대기: 이전 인덱스 문자가 ')'(닫는괄호)일 때
         - '('는 무조껀 stack에 삽입
         */
        for (int i = 0; i < arr.length; i++) {
            if (i != 0 && arr[i] == ')') {
                if (arr[i - 1] == '(') { // 레이저
                    stack.pop();
                    count += stack.size();
                } else if (arr[i - 1] == ')') { // 막대기
                    stack.pop();
                    count++;
                }
            } else {
                stack.push(arr[i]);
            }
            // test output
            //System.out.print(basic.stack.toString());
        }
        return count;
    }

    public static void main(String[] args) {
        IronStick m = new IronStick();
        Scanner in = new Scanner(System.in);

        String s = in.next();
        int answer = m.solution(s);
        System.out.println(answer);
    }
}