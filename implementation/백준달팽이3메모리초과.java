package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준달팽이3메모리초과 {

    public static void main(String[] args) throws IOException {
        //long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int m = Integer.parseInt(token.nextToken());
        int n = Integer.parseInt(token.nextToken());
        int[][] road = new int[m + 2][n + 2];
        Arrays.fill(road[0], 1);
        Arrays.fill(road[m+1], 1);
        for(int i = 0; i < m + 2; i++) {
            road[i][0] = 1;
            road[i][n+1] = 1;
        }

        solution(road);
        /*long end = System.currentTimeMillis();
        System.out.println(end-start);*/
    }

    public static void solution(int[][] road) {
        State[] states = new State[4];
        states[0] = new State("right", 1, 0);
        states[1] = new State("bottom", 0, 1);
        states[2] = new State("left", -1, 0);
        states[3] = new State("up", 0, -1);

        int cur = 0;
        int[] curLoc = {1, 1}; // y, x
        road[curLoc[0]][curLoc[1]] = 1;
        int carved = 0;

        while (true) {
            road[curLoc[0]][curLoc[1]] = 1;
            int nextX = curLoc[1] + states[cur].x;
            int nextY = curLoc[0] + states[cur].y;
            if (road[nextY][nextX] == 0) { // 아직 가보지 않아서 갈 수 있을 경우
                curLoc[1] = nextX;
                curLoc[0] = nextY;
            }
            else {
                nextX = curLoc[1] + states[(cur + 1) % 4].x;
                nextY = curLoc[0] + states[(cur + 1) % 4].y;
                if (road[nextY][nextX] == 0) { // 아직 가보지 않아서 갈 수 있을 경우
                    cur = (cur + 1) % 4;
                    carved++;
                    curLoc[1] = nextX;
                    curLoc[0] = nextY;
                }
                else break;
            }
        }

        String answer = "";
        answer += carved + "\n";
        answer += (curLoc[0]) + " " + (curLoc[1]);
        System.out.println(answer);
    }
}

class State {
    String name;
    int x;
    int y;

    public State(String right, int x, int y) {
        this.name = right;
        this.x = x;
        this.y = y;
    }
}
