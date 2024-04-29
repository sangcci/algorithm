package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA미생물격리 {

    static int n;
    static int m;
    static int k;
    static List<Cluster>[][] map;

    static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Cluster implements Comparable<Cluster> {

        int y;
        int x;
        int cnt;
        int dir;

        public Cluster(int y, int x, int cnt, int dir) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.dir = dir;
        }

        public void move() {
            int nextY = y + move[dir][0];
            int nextX = x + move[dir][1];

            if (isRedZone(nextY, nextX)) {
                //System.out.println("red존 발동");
                changeDir();
                cnt = (int) Math.floor(cnt / 2);
            }
            y = nextY;
            x = nextX;

        }

        private void changeDir() {
            if (dir == 0) {
                dir = 1;
            } else if (dir == 1) {
                dir = 0;
            } else if (dir == 2) {
                dir = 3;
            } else if (dir == 3) {
                dir = 2;
            }
        }

        private boolean isRedZone(int y, int x) {
            return (y == 0) || (y == (n - 1)) || (x == 0) || (x == (n - 1));
        }

        @Override
        public int compareTo(Cluster o) {
            return o.cnt - this.cnt;
        }

        @Override
        public String toString() {
            return "[Cluster] y : " + y + " x : " + x + " n : " + cnt + " dir : " + dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(input.nextToken());
            m = Integer.parseInt(input.nextToken());
            k = Integer.parseInt(input.nextToken());

            map = new ArrayList[n][n];
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    map[y][x] = new ArrayList<>();
                }
            }

            List<Cluster> clusters = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                StringTokenizer ClusterInput = new StringTokenizer(br.readLine(), " ");
                int y = Integer.parseInt(ClusterInput.nextToken());
                int x = Integer.parseInt(ClusterInput.nextToken());
                int n = Integer.parseInt(ClusterInput.nextToken());
                int dir = Integer.parseInt(ClusterInput.nextToken());
                Cluster cluster = new Cluster(y, x, n, dir-1);
                map[y][x].add(cluster);
                clusters.add(cluster);
            }

            int sum = bfs(clusters);

            answer.append("#" + test_case + " " + sum + "\n");
        }

        System.out.println(answer);
    }

    public static int bfs(List<Cluster> clusters) {
        Queue<Cluster> q = new LinkedList<>();
        for (Cluster cluster : clusters) {
            q.offer(cluster);
        }
        int level = 0;
        while (!q.isEmpty()) {
            //System.out.println("level : "+ level);
            // 현재 위치
            //testPrint();
            // m시간만큼만 이동
            if (level == m) break;

            // 이동
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Cluster cur = q.poll();
                map[cur.y][cur.x].remove(cur); // move 전
                //System.out.println("이동 전" + cur);
                cur.move();
                q.offer(cur);
                map[cur.y][cur.x].add(cur); // move 후
                //System.out.println("   이동 후" + cur);
            }

            // 합침
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (map[y][x].size() > 1) {
                        //System.out.println("겹침!" + y + " " + x);
                        // 군집 마리 수 더하기
                        List<Cluster> moved = map[y][x];
                        int n = 0;
                        for (Cluster each : moved) n += each.cnt;
                        // 마리수 제일 높은 군집의 방향으로 정하기
                        Collections.sort(moved);
                        int dir = moved.get(0).dir;
                        // Queue에 있던 기존 군집 제거
                        int s = q.size();
                        for (int i = 0; i < s; i++) {
                            Cluster cur = q.poll();
                            if (cur.y == y && cur.x == x);
                            else q.offer(cur);
                        }
                        // 합친 새로운 군집 추가
                        Cluster newCluster = new Cluster(y, x, n, dir);
                        q.offer(newCluster);
                        map[y][x].clear();
                        map[y][x].add(newCluster);
                    }
                }
            }
            level++;
        }

        // 총 합 구하기
        int sum = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (!map[y][x].isEmpty()) sum += map[y][x].get(0).cnt;
            }
        }
        return sum;
    }

    public static void testPrint() {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (!map[y][x].isEmpty())
                    System.out.print(map[y][x].get(0).cnt + " ");
                else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}


