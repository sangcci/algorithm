package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Hash_3 {
    public ArrayList<Integer> solution(int N, int K, int[] salesRevenue) {
        int lt = 0;
        int rt = K;
        HashMap<Integer, Integer> types = new HashMap<>();
        ArrayList<Integer> answer = new ArrayList<>();

        // 배열 요소를 재사용하기위해 처음 K-1번째 salesRevenue는 먼저 계산
        for(int i = lt; i < rt; i++) {
            int tmp = types.getOrDefault(salesRevenue[i], 0);
            types.put(salesRevenue[i], tmp + 1);
        }

        while(rt <= N) {
            // key size 계산
            answer.add(types.keySet().size());
            //System.out.println(types);

            if(rt < salesRevenue.length) {
                // rt 추가
                int tmp = types.getOrDefault(salesRevenue[rt], 0);
                types.put(salesRevenue[rt], tmp + 1);

                // lt 제거
                types.put(salesRevenue[lt], types.getOrDefault(salesRevenue[lt], 0) - 1);
                if(types.get(salesRevenue[lt]) == 0)  types.remove(salesRevenue[lt]);
            }
            // rt, lt ++
            rt++;
            lt++;
        }

        return answer;
    }

    public static void main(String[] args)  {
        Hash_3 m = new Hash_3();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int K = in.nextInt();
        int[] salesRevenue = new int[N];
        for(int i = 0; i < N; i++) {
            salesRevenue[i] = in.nextInt();
        }

        for(int i : m.solution(N, K, salesRevenue)) {
            System.out.print(i + " ");
        }
    }
}
