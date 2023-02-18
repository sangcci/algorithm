package stack;

import java.util.ArrayList;
import java.util.Scanner;

public class Stack1 {
    public String solution(char[] brackets) {
        String answer = "YES";
        ArrayList<Character> stack = new ArrayList<>();

        for(char b : brackets) {
            if(b == ')') {
                if(stack.size() != 0) {
                    stack.remove(stack.lastIndexOf('('));
                }
            } else {
                stack.add('(');
            }
        }
        if(stack.size() != 0) {
            answer = "NO";
        }

        return answer;
    }

    public static void main(String[] args)  {
        Stack1 m = new Stack1();
        Scanner in = new Scanner(System.in);

        char[] chars = in.next().toCharArray();
        System.out.println(m.solution(chars));
    }
}
