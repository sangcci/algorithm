package array;
/*
[[ 문자열 나누기 ]]
프로그래머스 1단계

 */
import java.util.Scanner;

public class DivideString {
    public int solution(String s) {
        char[] strArr = s.toCharArray();
        char x = strArr[0];
        int count = 1;
        int mine = 0;
        int diff = 0;

        for(int i = 0; i < strArr.length-1; i++) {
            if(x == strArr[i]) mine++;
            else diff++;

            if(mine == diff) {
                count++;
                mine = 0;
                diff = 0;
                x = strArr[i+1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        DivideString m = new DivideString();
        Scanner in = new Scanner(System.in);

        String s = in.next();
        m.solution(s);
    }
}
