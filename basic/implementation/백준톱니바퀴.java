package basic.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준톱니바퀴 {

    static final int N = 0;
    static final int S = 1;
    static final int CLOCKWISE_DIRECTION = 1;
    static final int COUNTERCLOCKWISE_DIRECTION = -1;
    static final int LEFT = 1;
    static final int RIGHT = 2;


    static int[][] cogs = new int[5][8];
    static int[][] play;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < play.length; i++) {
            int[] turnDir = new int[5]; // 각 톱니 번호대로 방향 저장
            boolean[] visited = new boolean[5];
            int num = play[i][0];
            int dir = play[i][1];
            // 톱니바퀴 돌릴지 말지
            turnDir[num] = dir;
            turn(num, visited, turnDir);
            // 각 톱니 돌리기
            for (int n = 1; n <= 4; n++) {
                swap(cogs[n], turnDir[n]);
            }
        }
        System.out.println(sum());
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 4; i++) {
            Integer[] cogInputs = Arrays.stream(br.readLine().split(""))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            for (int j = 0; j < 8; j++) {
                cogs[i][j] = cogInputs[j];
            }
        }

        int k = Integer.parseInt(br.readLine());
        play = new int[k][2];
        for (int i = 0; i < k; i++) {
            StringTokenizer playInput = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 2; j++) {
                play[i][j] = Integer.parseInt(playInput.nextToken());
            }
        }
    }

    public static void turn(int number, boolean[] visited, int[] turnDir) {
        if (number <= 0 || number >= 5) return;
        else {
            int cur = number;
            int nextLeft = number - 1;
            int nextRight = number + 1;
            // keep going left
            if (nextLeft != 0) {
                if (!visited[nextLeft]) {
                    if (shouldTurn(cogs[cur], cogs[nextLeft], LEFT)) {
                        turnDir[nextLeft] = -turnDir[cur];
                        visited[nextLeft] = true;
                        turn(nextLeft, visited, turnDir);
                    }
                }
            }
            // keep going right
            if (nextRight != 5) {
                if (!visited[nextRight]) {
                    if (shouldTurn(cogs[cur], cogs[nextRight], RIGHT)) {
                        turnDir[nextRight] = -turnDir[cur];
                        visited[nextRight] = false;
                        turn(nextRight, visited, turnDir);
                    }
                }
            }
        }
    }

    public static boolean shouldTurn(int[] cur, int[] next, int dir) {
        // is same pole
        if (dir == LEFT) {
            return cur[6] != next[2];
        } else if (dir == RIGHT) {
            return cur[2] != next[6];
        }
        return false;
    }

    public static void swap(int[] cog, int direction) {
        if (direction == CLOCKWISE_DIRECTION) {
            for (int i = cog.length-1; i > 0; i--) {
                int tmp = 0;
                tmp = cog[i];
                cog[i] = cog[i-1];
                cog[i-1] = tmp;
            }
        }
        if (direction == COUNTERCLOCKWISE_DIRECTION) {
            for (int i = 0; i < cog.length-1; i++) {
                int tmp = 0;
                tmp = cog[i];
                cog[i] = cog[i+1];
                cog[i+1] = tmp;
            }
        }
    }

    public static int sum() {
        int sum = 0;
        if (cogs[1][0] == S) sum += 1;
        if (cogs[2][0] == S) sum += 2;
        if (cogs[3][0] == S) sum += 4;
        if (cogs[4][0] == S) sum += 8;
        return sum;
    }

    public static void testOutput(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void testOutput(int[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
    }
}
