package basic.graph;

import java.util.ArrayList;
import java.util.Scanner;
/*
[[ 경로 탐색 (DFS) - 인접리스트 ]]

 */
public class SearchPath2 {
    public static ArrayList<ArrayList<Integer>> graph;
    public static int[] chkArr;
    public static int N, M, answer;
    public static StringBuilder s = new StringBuilder();
    public void DFS(int v) {
        if(v == N) {
            answer++;
            System.out.println(s);
        } else {
            for(int nv : graph.get(v)) {
                if (chkArr[nv] == 0) {
                    chkArr[nv] = 1;
                    s.append(nv);
                    DFS(nv);
                    chkArr[nv] = 0;
                    s.deleteCharAt(s.length() - 1);
                }
            }
        }
    }
    public static void main(String[] args) {
        SearchPath2 m = new SearchPath2();
        Scanner in = new Scanner(System.in);

        // basic.graph 선언
        N = in.nextInt();
        graph = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<Integer>());
        }
        // basic.graph 초기값 할당
        M = in.nextInt();
        for(int i = 0; i < M; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph.get(a).add(b);
        }
        // chkArr 초기화
        chkArr = new int[N + 1];
        // 탐색 시작
        chkArr[1] = 1;
        answer = 0;
        s.append(1);
        m.DFS(1);
        // 출력
        System.out.println("answer = " + answer);
    }
}
