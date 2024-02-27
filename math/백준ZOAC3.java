package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준ZOAC3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int h = Integer.parseInt(token.nextToken());
        int w = Integer.parseInt(token.nextToken());
        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        int weightCount = 0;
        int heightCount = 0;
        int curX = 1;
        int curY = 1;
        while (curX <= w) {
            weightCount++;
            curX += m+1;
        }
        while (curY <= h) {
            heightCount++;
            curY += n+1;
        }
        System.out.println(weightCount * heightCount);

    }
}
