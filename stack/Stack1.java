package stack;

/*
[[괄호 문제]]

예외 상황
- ()) : ')'가 나와서 괄호가 끝맺음을 했는데 바로 ')'가 나오는 경우 => 바로 NO 처리후 리턴
- ()( : 괄호가 끝맺음을 한 후 '('가 나올 경우 => 그대로 진행

 */

import java.util.Scanner;
import java.util.Stack;

public class Stack1 {
    public String solution(char[] brackets) {
        String answer = "";
        Stack<Character> s = new Stack<>();

        for(char c : brackets) {
            if(c == '(') s.add(c);
            else {
                if(s.isEmpty()) return answer = "NO";
                else s.pop();
            }
        }
        if(s.isEmpty()) answer = "YES";
        else answer = "NO";

        return answer;
    }

    public static void main(String[] args)  {
        Stack1 m = new Stack1();
        Scanner in = new Scanner(System.in);

        char[] chars = in.next().toCharArray();
        System.out.println(m.solution(chars));
    }
}
