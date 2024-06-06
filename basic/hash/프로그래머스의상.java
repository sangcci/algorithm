package basic.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 프로그래머스의상 {

    public static void main(String[] args) throws IOException {
        // init
        프로그래머스의상 m = new 프로그래머스의상();

        // input
        String[][] clothes = m.input();

        // business
        int answer = m.solution(clothes);

        // output
        System.out.println(answer);
    }

    String[][] input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[][] inputArr = new String[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            inputArr[i][0] = input.nextToken();
            inputArr[i][1] = input.nextToken();
        }
        return inputArr;
    }

    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();

        for (String[] cloth : clothes) {
            String kind = cloth[1];
            map.put(kind, map.getOrDefault(kind, 0) + 1);
        }

        // calculate combination count
        int cnt = 1;
        for (String kind : map.keySet()) {
            cnt *= (map.get(kind) + 1);
        }
        cnt--;

        return cnt;
    }
}
