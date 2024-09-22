import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int DESTINATION = 2;
    static final int CAN_MOVE = 1;
    static final int DONT_MOVE = 0;
    static final int VISITED = -1;
    static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int n, m;
    static int[][] map;
    static int[][] distanceMap;
    static int[] startLocation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer inputSize = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(inputSize.nextToken());
        m = Integer.parseInt(inputSize.nextToken());

        map = new int[n][m];
        distanceMap = new int[n][m];
        for (int r = 0; r < n; r++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < m; c++) {
                int landState = Integer.parseInt(input.nextToken());
                if (landState == DESTINATION) {
                    startLocation = new int[]{r, c};
                }
                map[r][c] = landState;
            }
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                sb.append(distanceMap[r][c]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs() {
        int distance = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(startLocation);
        map[startLocation[0]][startLocation[1]] = VISITED;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int l = 0; l < 4; l++) {
                    int nextR = cur[0] + dir[l][0];
                    int nextC = cur[1] + dir[l][1];

                    if (checkBoundary(nextR, nextC)) {
                        continue;
                    }

                    int nextState = map[nextR][nextC];
                    if (nextState == DONT_MOVE) {
                        continue;
                    }

                    if (nextState == VISITED) {
                        continue;
                    }

                    distanceMap[nextR][nextC] = distance;
                    map[nextR][nextC] = VISITED;
                    q.offer(new int[]{nextR, nextC});
                }
            }
            distance++;
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (map[r][c] == CAN_MOVE) {
                    distanceMap[r][c] = -1;
                }
            }
        }
    }

    public static boolean checkBoundary(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= m;
    }
}
