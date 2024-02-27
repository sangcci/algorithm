package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준구슬탈출2 {

    static int[] moveX = {0, 1, 0, -1};
    static int[] moveY = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sizes = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(sizes.nextToken());
        int m = Integer.parseInt(sizes.nextToken());
        char[][] board = new char[n][m];
        Marble initMarble = new Marble();
        initMarble.count = 0;
        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (chars[j] == 'R') {
                    initMarble.Ry = i;
                    initMarble.Rx = j;
                }
                if (chars[j] == 'B') {
                    initMarble.By = i;
                    initMarble.Bx = j;
                }
                board[i][j] = chars[j];
            }
        }
        boolean[][][][] checkArr = new boolean[n][m][n][m];

        bfs(board, initMarble, checkArr);
    }

    public static void bfs(char[][] board, Marble initMarble, boolean[][][][] checkArr) {
        int answer = -1;
        Queue<Marble> q = new LinkedList<>();
        q.offer(initMarble);

        bfs:
        while (!q.isEmpty()) {
            Marble cur = q.poll();
            if (cur.count + 1 > 10) {
                //System.out.println("count 10 초과");
                break bfs;
            }
            //System.out.println("------");
            //System.out.println("현재 시도 : ");
            //System.out.println("기울인 횟수 : " + cur.count);
            //System.out.println("R: " + cur.Ry + " " + cur.Rx + " B: " + cur.By + " " + cur.Bx);
            // 기울이기
            tilt:
            for (int i = 0; i < 4; i++) {
                //System.out.println("현재 기울이기 방향");
                //System.out.println(i);
                // R 구슬 움직이기
                boolean isFallR = false;
                int nextRy = cur.Ry;
                int nextRx = cur.Rx;
                moveR:
                while (true) {
                    //System.out.println("R구슬 이동 경로");
                    //System.out.println(nextRy + " " + nextRx);
                    if (board[nextRy + moveY[i]][nextRx + moveX[i]] == '#') {
                        break moveR;
                    }
                    if (board[nextRy + moveY[i]][nextRx + moveX[i]] == 'O') {
                        isFallR = true;
                        break moveR;
                    }
                    nextRy += moveY[i];
                    nextRx += moveX[i];
                }

                // B 구슬 움직이기
                boolean isFallB = false;
                int nextBy = cur.By;
                int nextBx = cur.Bx;
                moveB:
                while (true) {
                    //System.out.println("B구슬 이동 경로");
                    //System.out.println(nextBy + " " + nextBx);
                    if (board[nextBy + moveY[i]][nextBx + moveX[i]] == '#') {
                        break moveB;
                    }
                    if (board[nextBy + moveY[i]][nextBx + moveX[i]] == 'O') {
                        isFallB = true;
                        break moveB;
                    }
                    nextBy += moveY[i];
                    nextBx += moveX[i];
                }

                // 구멍에 빠질 경우
                if (isFallR && !isFallB) {
                    //System.out.println("구슬을 만났다!!");
                    answer = cur.count + 1;
                    break bfs;
                }
                if (isFallR && isFallB) {
                    continue tilt;
                }
                if (isFallB) {
                    continue tilt;
                }

                // 위치가 겹칠 때 조정하기
                if (nextRx == nextBx && nextRy == nextBy) {
                    if (i == 0) { // 위쪽
                        if (cur.Ry > cur.By) {
                            nextRy -= moveY[i];
                        } else {
                            nextBy -= moveY[i];
                        }
                    } else if (i == 1) { // 오른쪽
                        if (cur.Rx > cur.Bx) {
                            nextBx -= moveX[i];
                        } else {
                            nextRx -= moveX[i];
                        }
                    } else if (i == 2) { // 아래쪽
                        if (cur.Ry > cur.By) {
                            nextBy -= moveY[i];
                        } else {
                            nextRy -= moveY[i];
                        }
                    } else if (i == 3) { // 왼쪽
                        if (cur.Rx > cur.Bx) {
                            nextRx -= moveX[i];
                        } else {
                            nextBx -= moveX[i];
                        }
                    }
                }

                // 한번 더 움직이기, B는 들려도 R은 안들렸을 수도 있음
                if (!checkArr[nextRy][nextRx][nextBy][nextBx]) {
                    checkArr[nextRy][nextRx][nextBy][nextBx] = true;
                    //System.out.println("저장! " + "R: " + nextRy + " " + nextRx + " B: " + nextBy + " " + nextBx);
                    Marble nextMarble = new Marble(cur.count + 1, nextRx, nextRy, nextBx, nextBy);
                    q.offer(nextMarble);
                }
            }
        }

        System.out.println(answer);
    }
}

class Marble {

    int count;
    int Rx;
    int Ry;
    int Bx;
    int By;

    public Marble() {
    }

    public Marble(int count, int rx, int ry, int bx, int by) {
        this.count = count;
        Rx = rx;
        Ry = ry;
        Bx = bx;
        By = by;
    }
}
