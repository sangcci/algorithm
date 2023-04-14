package stack;

/*
후위연산식의 특징 분석
- 숫자가 나오다가 연산자가 나오면 전의 2개 숫자를 빼서 계산 => stack을 활용해보자

방식
숫자는 stack에 넣고 연산자는 pop해서 계산

유의점
- 연산자를 구별하기 위해 switch case문 작성(break문 필수!)
- 자바에서 char를 int로 변환하는 방법(엉뚱한 값이 나올 수 있으므로 로직 분석 잘 하자)
    -> Character.getNumericValue()
- 숫자인지 아닌지 판별하는 메서드 존재 -> Character.isDigit()
 */

import java.util.*;

public class Stack4 {
    public int solution(String postfix) {
        int answer = 0;
        int operand_A = 1;
        int operand_B = 1;

        char[] postfixArray = postfix.toCharArray();
        Stack<Integer> s = new Stack<>();
        for(char c : postfixArray) {
            if(Character.isDigit(c)) s.add(Character.getNumericValue(c));
            else {
                operand_A = s.pop();
                operand_B = s.pop();

                switch (c) {
                    case '+' : answer = operand_B + operand_A;
                        break;
                    case '-' : answer = operand_B - operand_A;
                        break;
                    case '*' : answer = operand_B * operand_A;
                        break;
                    case '/' : answer = operand_B / operand_A;
                        break;
                }
                s.add(answer);
            }
        }
        return answer;
    }


    public static void main(String[] args)  {
        Stack4 m = new Stack4();
        Scanner in = new Scanner(System.in);

        String postfix = in.next();
        System.out.println(m.solution(postfix));
    }
}
