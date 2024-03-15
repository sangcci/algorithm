package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준주사위굴리기 {
    static final int WALL = -1;
    static final int EAST = 1;
    static final int WEST = 2;
    static final int NORTH = 3;
    static final int SOUTH = 4;

    static int[] moveY = {0, 0, 0, -1, 1}; // index 0은 빈 값, 접근 안함
    static int[] moveX = {0, 1, -1, 0, 0}; // index 0은 빈 값, 접근 안함

    static int[][] dice;
    static int[][] board;
    static int[] orders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // board 초기화
        StringTokenizer init = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(init.nextToken());
        int m = Integer.parseInt(init.nextToken());
        board = new int[n+2][m+2];
        Arrays.fill(board[0], WALL);
        Arrays.fill(board[n+1], WALL);
        for (int i = 0; i < board.length; i++) {
            board[i][0] = WALL;
            board[i][m+1] = WALL;
        }

        // 주사위 초기화
        dice = new int[4][3];

        // 주사위 위치 초기화
        int curY = Integer.parseInt(init.nextToken()) + 1;
        int curX = Integer.parseInt(init.nextToken()) + 1;

        // 명령 갯수 초기화
        int k = Integer.parseInt(init.nextToken());

        // board 칸에 쓰여 있는 수 초기화
        for (int y = 1; y <= n; y++) {
            StringTokenizer boardNumbers = new StringTokenizer(br.readLine(), " ");
            for (int x = 1; x <= m; x++) {
                board[y][x] = Integer.parseInt(boardNumbers.nextToken());
            }
        }

        // 명령 초기화
        orders = new int[k];
        StringTokenizer orderNumbers = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            orders[i] = Integer.parseInt(orderNumbers.nextToken());
        }

        solution(curY, curX);
    }

    static void solution(int curY, int curX) {
        /*
         주사위 명령대로 굴리기
         */
        for (int orderDirect : orders) {
            /*
             벽이 있을 땐, 주사위도 굴리지 말고 그냥 명령 무시
             */
            int nextY = curY + moveY[orderDirect];
            int nextX = curX + moveX[orderDirect];
            if (board[nextY][nextX] == WALL) continue;

            /*
             주사위 방향대로 1칸 굴리기
             */
            roll(orderDirect);

            /*
             복사하기
             */
            copy(nextY, nextX);

            /*
             주사위 상단 값 출력하기
             */
            System.out.println(dice[1][1]);

            /*
             현재 주사위 위치 업데이트
             */
            curY = nextY;
            curX = nextX;
        }
    }

    static void copy(int nextY, int nextX) {
        if (board[nextY][nextX] != 0) {
            dice[3][1] = board[nextY][nextX];
            board[nextY][nextX] = 0;
        } else {
            board[nextY][nextX] = dice[3][1];
            //dice[3][1] = 0;
        }
    }

    static void roll(int direction) {
        if (direction == EAST) {
            swap(1, 2, 3, 1);
            swap(1, 1, 1, 2);
            swap(1, 0, 1, 1);
        } else if (direction == WEST) {
            swap(1, 0, 3, 1);
            swap(1, 1, 1, 0);
            swap(1, 2, 1, 1);
        } else if (direction == NORTH) {
            swap(0, 1, 1, 1);
            swap(1, 1, 2, 1);
            swap(2, 1, 3, 1);
        } else if (direction == SOUTH) {
            swap(2, 1, 3, 1);
            swap(1, 1, 2, 1);
            swap(0, 1, 1, 1);
        }
    }

    static void swap(int ay, int ax, int by, int bx) {
        int temp = dice[ay][ax];
        dice[ay][ax] = dice[by][bx];
        dice[by][bx] = temp;
    }

    static void testPrint(int[][] test) {
        for (int y = 0; y < test.length; y++) {
            System.out.println();
            for (int x = 0; x < test[0].length; x++) {
                System.out.print(test[y][x] + " ");
            }
        }
    }

    static void testDiceSet() {
        dice[0][1] = 2;
        dice[1][0] = 4;
        dice[1][1] = 1;
        dice[1][2] = 3;
        dice[2][1] = 5;
        dice[3][1] = 6;
    }
}
