package bfs;

import java.util.*;
/*
[[ 송아지 찾기 ]]

 */
public class FindCalf {
    public int pos;
    public int[] dis = {5, 1, -1};
    int cnt = 0;
    int[] chk;

    public int BFS(int S, int E) {
        Queue<Integer> q = new LinkedList<>();
        chk = new int[10001];

        q.offer(S);
        while(!q.isEmpty() && pos <= 10000) {
            int len = q.size();
            for(int i = 0; i < len; i++) {
                pos = q.poll();

                for(int d : dis) {
                    int nx = pos + d;
                    if(nx == E) {
                        return cnt+1;
                    }
                    if(nx >= 1 && nx <= 10000 && chk[nx] == 0) {
                        chk[nx] = 1;
                        q.offer(nx);
                    }
                }
            }
            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) {
        FindCalf m = new FindCalf();
        Scanner in = new Scanner(System.in);

        int S = in.nextInt();
        int E = in.nextInt();

        System.out.println(m.BFS(S, E));
    }
}