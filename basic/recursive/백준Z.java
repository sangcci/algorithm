package basic.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준Z {

    static int n, r, c;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(input.nextToken());
        r = Integer.parseInt(input.nextToken());
        c = Integer.parseInt(input.nextToken());

        recursion(n, 0, r, c);

        System.out.println(answer);
    }

    public static void recursion(int level, int cnt, int curR, int curC) {
        // test print
        /*System.out.println("level: " + level);
        System.out.println("cnt: " + cnt);
        System.out.println("r: " + curR + " c: " + curC);*/

        // escape
        if (level == 0) {
            answer = cnt;
        } else {
            int side = (int) Math.pow(2, level);
            int tmp = side / 2;
            // 1 사분면
            if (curR < tmp && curC < tmp) {
                //System.out.println("1사분면 통과");
                recursion(level - 1, cnt, curR, curC);
            }
            // 2 사분면
            else if (curR < tmp && curC >= tmp) {
                //System.out.println("2사분면 통과");
                cnt += (int) (((Math.pow(2, level) * Math.pow(2, level)) / 4) * 1);
                int nextC = curC - ((int) Math.pow(2, level) / 2);
                recursion(level - 1, cnt, curR, nextC);
            }
            // 3 사분면
            else if (curR >= tmp && curC < tmp) {
                //System.out.println("3사분면 통과");
                cnt += (int) (((Math.pow(2, level) * Math.pow(2, level)) / 4) * 2);
                int nextR = curR - ((int) Math.pow(2, level) / 2);
                recursion(level - 1, cnt, nextR, curC);
            }
            // 4 사분면
            else if (curR >= tmp && curC >= tmp) {
                //System.out.println("4사분면 통과");
                cnt += (int) (((Math.pow(2, level) * Math.pow(2, level)) / 4) * 3);
                int nextR = curR - ((int) Math.pow(2, level) / 2);
                int nextC = curC - ((int) Math.pow(2, level) / 2);
                recursion(level - 1, cnt, nextR, nextC);
            }
        }
    }
}
