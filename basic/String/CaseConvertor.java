package basic.String;

import java.util.Scanner;

public class CaseConvertor {

    public String convertCase(String str) {
        StringBuilder builder = new StringBuilder();

        for (char each : str.toCharArray()) {
            if (each >= 'a' && each <= 'z') {
                String upperCase = String.valueOf(each).toUpperCase();
                builder.append(upperCase);
            } else if (each >= 'A' && each <= 'Z') {
                String lowerCase = String.valueOf(each).toLowerCase();
                builder.append(lowerCase);
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CaseConvertor caseConvertor = new CaseConvertor();

        String str = in.next();
        String answer = caseConvertor.convertCase(str);
        System.out.println(answer);
    }
}
