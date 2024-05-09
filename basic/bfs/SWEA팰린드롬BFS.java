package basic.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA팰린드롬BFS {

    static int n;
    static char[][] board;

    static class Node {

        int y, x;
        char c;

        public Node(int y, int x, char c) {
            this.y = y;
            this.x = x;
            this.c = c;
        }
    }

    static int[][] UD = {{-1, 0}, {1, 0}};
    static int[][] LR = {{0, -1}, {0, 1}};
    static final int UP_DOWN = 3;
    static final int LEFT_RIGHT = 4;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());

            board = new char[8][8];
            for (int y = 0; y < 8; y++) {
                String[] input = br.readLine().split("");
                for (int x = 0; x < 8; x++) {
                    board[y][x] = input[x].charAt(0);
                }
            }
            cnt = 0;

            if (n == 1) {
                cnt = 64;
            } else if (n % 2 == 1) {
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        int end = calEndLevel(n);
                        Node n1 = new Node(y, x, board[y][x]);
                        Node n2 = new Node(y, x, board[y][x]);
                        bfs(n1, n2, end, UP_DOWN);
                        bfs(n1, n2, end, LEFT_RIGHT);
                    }
                }
            } else {
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        int end = calEndLevel(n);
                        // up-down
                        Node n1 = new Node(y, x, board[y][x]);
                        if (checkBoundary(y+1, x) && board[y+1][x] == board[y][x]) {
                            Node n2 = new Node(y+1, x, board[y+1][x]);
                            bfs(n1, n2, end, UP_DOWN);
                        }
                        // left-right
                        if (checkBoundary(y, x+1) && board[y][x+1] == board[y][x]) {
                            Node n2 = new Node(y, x+1, board[y][x+1]);
                            bfs(n1, n2, end, LEFT_RIGHT);
                        }
                    }
                }
            }

            answer.append("#" + test_case + " " + cnt + "\n");
        }
        System.out.println(answer);
    }

    public static int calEndLevel(int n) {
        if (n % 2 == 1) {
            return n / 2;
        } else {
            return n / 2 - 1;
        }
    }

    public static void bfs(Node n1, Node n2, int end, int dir) {
        Queue<Node> q = new LinkedList<>();
        int level = 0;
        q.offer(n1);
        q.offer(n2);
        while (!q.isEmpty()) {
            if (level == end) {
                cnt++;
                break;
            }
            Node cur1 = q.poll();
            Node cur2 = q.poll();
            if (dir == UP_DOWN) {
                int nextY = cur1.y + UD[0][0];
                int nextX = cur1.x + UD[0][1];
                if (checkBoundary(nextY, nextX)) {
                    q.offer(new Node(nextY, nextX, board[nextY][nextX]));
                }
                nextY = cur2.y + UD[1][0];
                nextX = cur2.x + UD[1][1];
                if (checkBoundary(nextY, nextX)) {
                    q.offer(new Node(nextY, nextX, board[nextY][nextX]));
                }
            } else if (dir == LEFT_RIGHT) {
                int nextY = cur1.y + LR[0][0];
                int nextX = cur1.x + LR[0][1];
                if (checkBoundary(nextY, nextX)) {
                    q.offer(new Node(nextY, nextX, board[nextY][nextX]));
                }
                nextY = cur2.y + LR[1][0];
                nextX = cur2.x + LR[1][1];
                if (checkBoundary(nextY, nextX)) {
                    q.offer(new Node(nextY, nextX, board[nextY][nextX]));
                }
            }

            if (q.size() != 2) {
                break;
            } else {
                cur1 = q.poll();
                cur2 = q.poll();
                if (cur1.c != cur2.c) {
                    break;
                } else {
                    q.offer(cur1);
                    q.offer(cur2);
                }
            }

            level++;
        }
    }

    public static boolean checkBoundary(int y, int x) {
        return y >= 0 && y < 8 && x >= 0 && x < 8;
    }

    public static void testPrint(char[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
