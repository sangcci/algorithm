package basic.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEAFlatten {

    static int[] boxes;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= 10; test_case++) {
            count = Integer.parseInt(br.readLine());

            boxes = new int[100];
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < boxes.length; i++) {
                boxes[i] = Integer.parseInt(input.nextToken());
            }

            dump();

            int diff = calDiffHeight();

            answer.append("#")
                    .append(test_case)
                    .append(" ")
                    .append(diff)
                    .append("\n");
        }

        System.out.println(answer);
    }

    public static void dump() {
        while (count > 0) {
            // basic.sort
            Arrays.sort(boxes);

            // move box
            boxes[0]++;
            boxes[boxes.length-1]--;

            // count --
            count--;
        }
        // 마지막으로 basic.sort 해야 함
        Arrays.sort(boxes);
    }

    public static int calDiffHeight() {
        return boxes[boxes.length-1] - boxes[0];
    }

    public static void testPrint(int[] arr) {
        for (int each : arr) {
            System.out.print(each + " ");
        }
        System.out.println();
    }
}
