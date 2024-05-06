package basic.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class 백준뱀 {

    public static final int WALL = 1;
    public static final int APPLE = 2;
    public static final int BLANK = 0;
    static int[] moveX = {1, 0, -1, 0};
    static int[] moveY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n+2][n+2];
        Arrays.fill(board[0], 1);
        Arrays.fill(board[n+1], 1);
        for (int i = 1; i <= n; i++) {
            board[i][0] = 1;
            board[i][n+1] = 1;
        }
        //printBoard(board);

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer appleLoc = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(appleLoc.nextToken());
            int x = Integer.parseInt(appleLoc.nextToken());
            board[y][x] = 2;
        }
        //printBoard(board);

        int l = Integer.parseInt(br.readLine());
        char[] direct = new char[10001];
        for (int i = 0; i < l; i++) {
            StringTokenizer snakeDirect = new StringTokenizer(br.readLine(), " ");
            int sec = Integer.parseInt(snakeDirect.nextToken());
            char move = snakeDirect.nextToken().charAt(0);
            direct[sec] = move;
        }

        gameStart(board, direct);
    }

    public static void gameStart(int[][] board, char[] direct) {
        int seconds = 1;
        int dir = 0;
        int[] firstLocation = {1, 1};
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(firstLocation);

        game: while (seconds <= 10000) {
            /*
             움직이기
             */
            int[] curHead = deque.getLast();
            int nextY = curHead[0] + moveY[dir];
            int nextX = curHead[1] + moveX[dir];
            //System.out.println("nextY: " + nextY + " nextX: " + nextX);

            // 다음 위치가 벽이라면
            if (board[nextY][nextX] == WALL) break game;
            // 자기 자신의 몸이라면
            for (int[] snakeLoc : deque) {
                if (nextY == snakeLoc[0] && nextX == snakeLoc[1]) break game;
            }
            // 사과가 있다면
            if (board[nextY][nextX] == APPLE) {
                // 몸이 늘어남 -> 머리값 붙임
                int[] head = {nextY, nextX};
                deque.addLast(head);
                // 사과는 없어짐
                board[nextY][nextX] = BLANK;
            }
            // 둘 다 아니라면
            else {
                // 몸이 늘어나지 않고 그대로 진행 -> 머리 값 붙임, 꼬리 제거
                deque.removeFirst();
                int[] head = {nextY, nextX};
                deque.addLast(head);
            }

            /*
             방향 틀기
             */
            if (direct[seconds] == 'D') {
                dir = (dir+1) % 4;
            } else if (direct[seconds] == 'L') {
                dir--;
                if (dir < 0) {
                    dir = 3;
                }
            }

            //printBoard(board);
            //System.out.println(seconds + "초");
            //System.out.println("움직인 결과");
            //printSnakeLoc(deque);

            /*
             시간 증가
             */
            seconds++;

        }
        System.out.println(seconds);
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static void printSnakeLoc(Deque<int[]> snakeLoc) {
        snakeLoc.forEach(each ->
                System.out.println(each[0] + " " + each[1]));
    }
}