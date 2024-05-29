package basic.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class 백준스택수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        List<Character> answers = new ArrayList<>();

        // search
        Stack<Integer> s = new Stack<>();
        int i = 0, j = 0;

        boolean flag = true;
        seq: while (i < nums.length && j < sorted.length) {
            // pop
            if (sorted[j] == nums[i]) {
                s.push(sorted[j]); answers.add('+'); j++;
                while (!s.empty() && s.peek() == nums[i]) {
                    s.pop(); answers.add('-'); i++;
                    // escape condition
                    if (i < nums.length && s.contains(nums[i]) && s.peek() != nums[i]) {
                        flag = false;
                        break seq;
                    }
                }
            }
            // push
            else {
                s.push(sorted[j]); answers.add('+'); j++;
            }
        }

        // output
        if (flag) {
            for (char each : answers) {
                System.out.println(each);
            }
        } else {
            System.out.println("NO");
        }
    }
}
