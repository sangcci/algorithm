package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SWEA보물상자비밀번호 {
    static int n;
    static int k;
    static String nums;
    static Set<Integer> boxPasswords;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(input.nextToken());
            k = Integer.parseInt(input.nextToken());

            nums = br.readLine();
            boxPasswords = new HashSet<>();

            for (int i = 0; i < n/4; i++) {
                divide();
                rotate();
            }
            int num = chooseMax();

            answer.append("#")
                    .append(test_case)
                    .append(" ")
                    .append(num)
                    .append("\n");
        }
        System.out.println(answer);
    }

    public static void divide() {
        for (int start = 0, end = (n / 4); end <= n; start += (n/4), end += (n/4)) {
            // make num
            String num = nums.substring(start, end);

            // to Decimal
            int changedNum = toDecimal(num);

            // add SET
            boxPasswords.add(changedNum);
        }
    }

    public static int toDecimal(String num) {
        int changed = 0;
        int length = num.length();
        for (int i = 0, cnt = length -1; i < length && cnt >= 0; i++, cnt--) {
            String s = num.substring(i, i + 1);
            if (s.equals("A")) s = "10";
            else if (s.equals("B")) s = "11";
            else if (s.equals("C")) s = "12";
            else if (s.equals("D")) s = "13";
            else if (s.equals("E")) s = "14";
            else if (s.equals("F")) s = "15";

            int n = (Integer.parseInt(s)) * (int)Math.pow(16, cnt);
            changed += n;
        }
        return changed;
    }

    public static void rotate() {
        char[] numsArray = nums.toCharArray();
        for (int i = numsArray.length-1; i > 0; i--) {
            char tmp = numsArray[i-1];
            numsArray[i-1] = numsArray[i];
            numsArray[i] = tmp;
        }
        nums = String.valueOf(numsArray);
    }

    public static int chooseMax() {
        List<Integer> collected = boxPasswords.stream().collect(Collectors.toList());
        collected.sort(Comparator.reverseOrder());
        return collected.get(k-1);
    }
}
