package basic.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA농작물수확하기 {

    static int n;
    static int[][] farm;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());

            farm = new int[n][n];
            for (int y = 0; y < n; y++) {
                String[] inputs = br.readLine().split("");
                for (int x = 0; x < n; x++) {
                    farm[y][x] = Integer.parseInt(inputs[x]);
                }
            }
            sum = 0;

            // up part
            int lt = 1, rt = n-2, mid = n/2;
            int row = mid - 1;
            //System.out.println("up part start");
            //System.out.println("lt: " + lt + " rt: " + rt + " row: " + row);
            while (lt < rt || row >= 0) {
                for (int col = lt; col <= rt; col++) {
                    sum += farm[row][col];
                }
                lt++; rt--; row--;
            }

            // bottom part
            lt = 1; rt = n-2;
            row = mid + 1;
            //System.out.println("bottom part start");
            //System.out.println("lt: " + lt + " rt: " + rt + " row: " + row);
            while (lt < rt || row <= n-1) {
                for (int col = lt; col <= rt; col++) {
                    sum += farm[row][col];
                }
                lt++; rt--; row++;
            }

            // mid part
            for (int col = 0; col < n; col++) {
                sum += farm[mid][col];
            }

            output.append("#" + test_case + " " + sum + "\n");
        }
        System.out.println(output);
    }
}
