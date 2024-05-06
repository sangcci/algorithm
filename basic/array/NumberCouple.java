package basic.array;

import java.util.*;
/*
[[ 숫자 짝꿍 (배열 탐색) ]]
프로그래머스 1단계 문제
 */
public class NumberCouple {
    public String solution(String X, String Y) {
        StringBuilder answer = new StringBuilder();

        char[] xArr = X.toCharArray();
        char[] yArr = Y.toCharArray();
        Arrays.sort(xArr);
        Arrays.sort(yArr);

        int xpt = 0;
        int ypt = 0;

        while((xpt < xArr.length) && (ypt < yArr.length)) {
            if(xArr[xpt] == yArr[ypt]) {
                answer.append(xArr[xpt]);
                xpt++;
                ypt++;
            } else {
                if((int)xArr[xpt] > (int)yArr[ypt]) {
                    ypt++;
                } else {
                    xpt++;
                }
            }
        }
        answer.reverse();

        if(answer.toString().isEmpty()) {
            answer.append("-1");
            return answer.toString();
        }
        if(answer.indexOf("0") == 0) {
            return "0";
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        NumberCouple m = new NumberCouple();
        Scanner in = new Scanner(System.in);

        String X = in.next();
        String Y = in.next();

        System.out.println(m.solution(X, Y));
    }
}
