package basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA프로세서연결하기 {

    static int n;
    static List<Core> cores;
    static int[][] board;
    static final int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int maxChoiceCore, min;
    static class Core {
        int r, c;
        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());

            board = new int[n][n];
            for (int row = 0; row < n; row++) {
                StringTokenizer input = new StringTokenizer(br.readLine(), " ");
                for (int col = 0; col < n; col++) {
                    board[row][col] = Integer.parseInt(input.nextToken());
                }
            }

            cores = new ArrayList<>();
            for (int row = 1; row < n-1; row++) {
                for (int col = 1; col < n-1; col++) {
                    if (board[row][col] == 1) {
                        for (int dir = 0; dir < 4; dir++) {
                            int r = row + move[dir][0];
                            int c = col + move[dir][1];

                            boolean flag = true;
                            while (!isConnected(r, c)) {
                                if (board[r][c] == 1) {
                                    flag = false;
                                    break;
                                }
                                r += move[dir][0];
                                c += move[dir][1];
                            }
                            if (flag) {
                                cores.add(new Core(row, col));
                                break;
                            }
                        }
                    }
                }
            }
            //testPrint(cores);
            min = Integer.MAX_VALUE;
            maxChoiceCore = 0;

            dfs(0, 0);

            output.append("#" + test_case + " " + min + "\n");
        }
        System.out.println(output);
    }

    public static void dfs(int level, int cnt) {
        //System.out.println("현재 dfs");
        //System.out.println("level: " + level);
        //testPrint(board);
        if (level == cores.size()) {
            int line = 0;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (board[row][col] == 2) {
                        line++;
                    }
                }
            }
            if (cnt > maxChoiceCore) {
                maxChoiceCore = cnt;
                min = line;
            } else if (cnt == maxChoiceCore) {
                min = Math.min(min, line);
            }
        } else {
            Core core = cores.get(level);
            //System.out.println("core.r: " + core.r + " core.c: " + core.c);
            // 상 하 좌 우 탐색
            for (int dir = 0; dir < 4; dir++) {
                //System.out.println("dir: " + dir);
                int row = core.r + move[dir][0];
                int col = core.c + move[dir][1];

                boolean flag = true;
                while (!isConnected(row, col)) {
                    if (board[row][col] == 2 || board[row][col] == 1) {
                        flag = false;
                        break;
                    }

                    board[row][col] = 2;
                    //System.out.println("row: " + row + "col: " + col);
                    //System.out.println("반영!");

                    row += move[dir][0];
                    col += move[dir][1];
                }
                if (flag) {
                    dfs(level + 1, cnt + 1);
                }

                row -= move[dir][0];
                col -= move[dir][1];
                while (!isCore(row, col, core.r, core.c)) {
                    board[row][col] = 0;
                    row -= move[dir][0];
                    col -= move[dir][1];
                }
            }
            // 아예 선택을 안했을 경우
            dfs(level + 1, cnt);
        }
    }

    public static boolean isCore(int r, int c, int CoreR, int CoreC) {
        return r == CoreR && c == CoreC;
    }

    public static boolean isConnected(int r, int c) {
        return r == -1 || r == n || c == -1 || c == n;
    }

    public static void testPrint(List<Core> cores) {
        for (Core core : cores) {
            System.out.println(core.r + " " + core.c);
        }
        System.out.println();
    }

    public static void testPrint(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                System.out.print(arr[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
