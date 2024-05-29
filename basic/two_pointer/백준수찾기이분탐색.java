package basic.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준수찾기이분탐색 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer inputArr = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inputArr.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] sol = new int[m];
        StringTokenizer inputSol = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            sol[i] = Integer.parseInt(inputSol.nextToken());
        }

        // sort
        Arrays.sort(arr);

        // binary search
        for (int each : sol) {
            boolean canFind = false;
            int lt = 0;
            int rt = arr.length-1;
            while (lt <= rt) {
                int mid = (lt + rt) / 2;
                if (arr[mid] == each) {
                    System.out.println(1);
                    canFind = true;
                    break;
                } else if (arr[mid] > each) {
                    rt = mid - 1;
                } else if (arr[mid] < each) {
                    lt = mid + 1;
                }
            }
            if (!canFind) {
                System.out.println(0);
            }
        }

    }
}
