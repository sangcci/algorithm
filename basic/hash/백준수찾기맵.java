package basic.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 백준수찾기맵 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        StringTokenizer inputArr = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int each = Integer.parseInt(inputArr.nextToken());
            map.put(each, map.getOrDefault(each, 0) + 1);
        }

        int m = Integer.parseInt(br.readLine());
        int[] sol = new int[m];
        StringTokenizer inputSol = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            sol[i] = Integer.parseInt(inputSol.nextToken());
        }

        // match
        for (int each : sol) {
            if (map.getOrDefault(each, 0) > 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
