package basic.stack;

import java.util.*;
/*
[[ 교육과정 설계 ]]

 */
public class DesignEducation {
    // init
    static String must;
    static String my_class;
    public String solution() {
        String answer = "NO";
        // init
        Stack<Character> stack = new Stack<>();
        for(int i = must.length()-1; i >= 0; i--) {
            stack.push(must.charAt(i));
        }
        //test output
        //System.out.println("basic.stack: " + basic.stack);
        //System.out.println("peek: " + basic.stack.peek());

        /*
         logic
         비교: 필수 과목 하나씩 비교. basic.stack 맨위에 값과 맞지 않는다면 안됨.
         */
        for(int i = 0; i < my_class.length(); i++) {
            if(my_class.charAt(i) == stack.peek()) {
                stack.pop();
            }
            // result
            if(stack.size() == 0) {
                answer = "YES";
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        DesignEducation m = new DesignEducation();
        Scanner in = new Scanner(System.in);

        must = in.next();
        my_class = in.next();

        System.out.println(m.solution());
    }
}