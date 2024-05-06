package basic.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 백준4195친구네트워크 {
    static Map<String, Integer> mapper;
    static int[] unf;
    static int[] count;
    static int key;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 시도 횟수
        for (int i = 0; i < n; i++) {
            solution(br);
        }
    }

    private static void solution(BufferedReader br) throws IOException {
        int f = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        key = 1;
        mapper = new HashMap<>();
        unf = new int[f * 2 + 1];
        for (int i = 1; i <= unf.length-1; i++) {
            unf[i] = i;
        }
        count = new int[f * 2 + 1];
        Arrays.fill(count, 1);
        // 친구 관계 수
        for (int j = 0; j < f; j++) {
            StringTokenizer token = new StringTokenizer(br.readLine(), " ");
            String a = token.nextToken();
            addName(a);
            String b = token.nextToken();
            addName(b);
            builder.append(union(mapper.get(a), mapper.get(b))).append("\n");
            /*
            System.out.println("현재 unf 상태");
            Arrays.stream(unf).forEach(each -> System.out.print(each + " "));
            System.out.println();
            System.out.println("현재 count 상태");
            Arrays.stream(count).forEach(each -> System.out.print(each + " "));
            System.out.println();
            System.out.println("-------");
            */
        }
        System.out.print(builder);
    }

    private static void addName(String name) {
        if (!mapper.containsKey(name)) {
            mapper.put(name, key++);
        }
    }

    public static int union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (a < b) {
                unf[b] = a;
                count[a] += count[b];
                return count[a];
            } else {
                unf[a] = b;
                count[b] += count[a];
                return count[b];
            }
        }
        return count[a];
    }
    public static int find(int me) {
        if (me == unf[me]) return me; // == unf.get(me)
        else return unf[me] = find(unf[me]);
    }
}
