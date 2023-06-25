package bfs;

import java.util.Scanner;
/*
[[ 경로 탐색 (DFS) ]]

 */
public class SearchPath {
    static int N, M, answer;
    static int[] chkArr;
    static int[][] graph;
    static StringBuilder s;
    public void DFS(int v) {
        if(v == N) {
            answer++;
            System.out.println("1" + s);
        }
        else{
            for(int i = 1; i <= N; i++) {
                if(graph[v][i] == 1 && chkArr[i] == 0) {
                    chkArr[i] = 1;
                    s.append(i);
                    DFS(i);
                    chkArr[i] = 0;
                    s.deleteCharAt(s.length()-1);
                }
            }
        }
    }
    public static void main(String[] args) {
        SearchPath m = new SearchPath();
        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        M = in.nextInt();
        graph = new int[N+1][M+1];
        for(int i = 0; i < M; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph[a][b] = 1;
        }
        chkArr = new int[N+1];
        answer = 0;
        s = new StringBuilder();

        chkArr[1] = 1;
        m.DFS(1);
        System.out.println("answer = " + answer);
    }
}