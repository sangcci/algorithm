package basic.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 프로그래머스폰켓몬 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        프로그래머스폰켓몬 m = new 프로그래머스폰켓몬();
        // input
        int[] nums = m.input(br);

        // logic
        int answer = m.solution(nums);

        // output
        System.out.println(answer);
    }

    int[] input(BufferedReader br) throws IOException {
        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(input.nextToken());
        }
        return arr;
    }

    int solution(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int cnt = nums.length / 2;
        if (cnt > map.size()) {
            cnt = map.size();
        }
        return cnt;
    }
}
