import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int NOT_YET = 0;
    static final int ALREADY_RIPE = 1;
    static final int NONE = -1;
    static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static List<int[]> tomatoLocations = new ArrayList<>();
    static int n, m;
    static int[][] boxes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer inputSize = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(inputSize.nextToken());
        n = Integer.parseInt(inputSize.nextToken());
        boxes = new int[n][m];
        for (int r = 0; r < n; r++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < m; c++) {
                int info = Integer.parseInt(input.nextToken());
                if (info == ALREADY_RIPE) {
                    tomatoLocations.add(new int[]{r, c});
                }
                boxes[r][c] = info;
            }
        }

        int result = bfs();

        if (isAllRipe()) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }

    }

    public static boolean isAllRipe() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (boxes[r][c] == NOT_YET) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int bfs() {
        int day = 0;
        Queue<int[]> q = new LinkedList<>();
        tomatoLocations.forEach(q::offer);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                /*if (boxes[cur[0]][cur[1]] == ALREADY_RIPE || boxes[cur[0]][cur[1]] == NONE) {
                    continue;
                }*/
                for (int j = 0; j < 4; j++) {
                    int nextR = cur[0] + dir[j][0];
                    int nextC = cur[1] + dir[j][1];
                    if (checkBoundary(nextR, nextC)
                            && boxes[nextR][nextC] == NOT_YET) {
                        boxes[nextR][nextC] = ALREADY_RIPE;
                        q.offer(new int[]{nextR, nextC});
                    }
                }
            }
            day++;
        }

        return day - 1;
    }

    public static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

}
