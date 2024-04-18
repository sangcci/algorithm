package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA최대상금 {

    static int[] nums;
    static int n;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        String answer = "";
        for (int test_case = 1; test_case <= T; test_case++)
        {
            // input
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            nums = Arrays.stream(input.nextToken().split("")).mapToInt(Integer::parseInt).toArray();
            n = Integer.parseInt(input.nextToken());

            // back tracking
            dfs(0, 0);

            // is already perfect number
            if (max == 0) {
                swapOnPerfectNum(nums, n, nums.length-1);
                max = parseNum(nums);
            }

            answer += "#" + test_case + " " + max + "\n";
            max = 0;
        }

        System.out.println(answer);
    }

    public static void dfs(int count, int cur) {
        if (count == n) {
            int num = parseNum(nums);
            max = Math.max(max, num);
        } else if (cur == nums.length - 1) {
            int remain = n - count;
            swapOnPerfectNum(nums, remain, cur);
            // skip
            dfs(n, cur);
        } else {
            for (int i = cur; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] <= nums[j]) {
                        swap(i, j);
                        dfs(count + 1, i);
                        swap(j, i);
                    }
                }
            }
        }
    }

    public static void swap(int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    public static void swapOnPerfectNum(int[] nums, int count, int cur) {
        if (searchSame(nums)) {
            // 그대로 넘김
        } else {
            if (count % 2 == 1) { // 홀수일 경우 바꿈
                swap(cur, cur-1);
            } else {} // 짝수일 경우 바꾸지 않음
        }
    }

    public static boolean searchSame(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            int lt = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                int rt = nums[j];
                if (lt == rt) return true;
            }
        }
        return false;
    }

    public static int parseNum(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int each : nums) {
            sb.append(each);
        }
        return Integer.parseInt(sb.toString());
    }

    public static void testPrint(int[] arr) {
        for (int each : arr) {
            System.out.print(each + " ");
        }
        System.out.println();
    }
}
