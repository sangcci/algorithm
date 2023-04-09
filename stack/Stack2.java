package stack;

import java.util.Scanner;
import java.util.Stack;
/*
[[괄호문자제거]]
1. stack의 괄호 여부로만 판단하기
- '('이 등장하는 순간 ')'가 나올때까지 문자 저장 X
- stack은 괄호 판별용. stack이 비워있을 때 다음 문자가 알파벳이면 answer에 추가
- 처음은 무조건 stack이 비워져 있을수 밖에 없다. -> c(다음 문자)가 '('인지 알파벳인지 추가 로직 필요
2. stack에 문자까지 저장
stack.pop()의 리턴값을 고려한 알고리즘
- ')'가 아니라면 알파벳이든 '('든 무조껀 추가
- ')'가 오면 while(stack.pop!= '(')계속 pop => ()안에 문자까지 다 날아간 셈
*/
public class Stack2 {
    public String solution(char[] brackets) {
        String answer = "";
        Stack<Character> s = new Stack<>();

        for(char c : brackets) {
            if(s.isEmpty() && c != '(') answer += c;
            else {
                if(c == '(') s.add(c);
                else if (c == ')') s.pop();
            }
        }
        return answer;
    }

    public static void main(String[] args)  {
        Stack2 m = new Stack2();
        Scanner in = new Scanner(System.in);

        char[] array = in.next().toCharArray();
        System.out.println(m.solution(array));
    }
}