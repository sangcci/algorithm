package basic.String;

import java.util.Scanner;

public class FindChar {

    public int countSameWord(String str, String exChar) {
        str = str.toLowerCase();
        exChar = exChar.toLowerCase();
        int result = 0;

        for (char each : str.toCharArray()) {
            if (each == exChar.charAt(0)) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FindChar findChar = new FindChar();

        String str = in.next();
        String exChar = in.next();

        int answer = findChar.countSameWord(str, exChar);
        System.out.println(answer);
    }
}
