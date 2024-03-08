package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준2048Easy {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] board = new int[size][size];
        for (int i = 0; i < size; i++) {
            StringTokenizer inputNum = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(inputNum.nextToken());
            }
        }
        System.out.println(bfs(board));
    }

    public static int bfs(int[][] board) {
        int max = 0;
        Queue<int[][]> q = new LinkedList<>();
        q.offer(board);
        int level = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[][] curBoard = q.poll();
                if (level > 5) {
                    max = Math.max(max, findMax(curBoard));
                    continue;
                }
                // 방향 대로 움직이기
                for (int direction = 0; direction < 4; direction++) {
                    int[][] nextBoard = moveDirection(copy(curBoard), direction);
                    q.offer(nextBoard);
                }
            }
            level++;
        }
        return max;
    }

    public static int[][] copy(int[][] arr) {
        if (arr == null) return null;
        int[][] copy = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i].clone();
        }
        return copy;
    }

    public static int[][] moveDirection(int[][] board, int direction) {
        Queue<Integer> q = new LinkedList<>();

        if (direction == 0) {
            for (int i = 0; i < board.length; i++) {
                // 움직이기 1. Q에 움직일거 넣기
                for (int j = 0; j < board.length; j++) {
                    if (board[j][i] != 0) {
                        q.offer(board[j][i]);
                    }
                    board[j][i] = 0; // 기존 board는 0으로 다 초기화
                }

                // 움직이기 2. 움직이기
                List<Integer> moved = move(q);

                // 움직이기 3. 다 움직인거 board에 대입하기
                int size = moved.size();
                for (int j = 0; j < size; j++) {
                    board[j][i] = moved.get(j);
                }
            }
        } else if (direction == 1) {
            for (int i = 0; i < board[0].length; i++) {
                // 움직이기 1. Q에 움직일거 넣기
                for (int j = board.length-1; j >= 0; j--) {
                    if (board[i][j] != 0) {
                        q.offer(board[i][j]);
                    }
                    board[i][j] = 0;
                }

                // 움직이기 2. 움직이기
                List<Integer> moved = move(q);

                // 움직이기 3. 다 움직인거 board에 대입하기
                int size = moved.size();
                for (int j = board[0].length-1, index = 0; j >= 0 && index < size; j--, index++) {
                    board[i][j] = moved.get(index);
                }
            }
        } else if (direction == 2) {
            for (int i = 0; i < board[0].length; i++) {
                // 움직이기 1. Q에 움직일거 넣기
                for (int j = board.length-1; j >= 0; j--) {
                    if (board[j][i] != 0) {
                        q.offer(board[j][i]);
                    }
                    board[j][i] = 0; // 기존 board는 0으로 다 초기화
                }

                // 움직이기 2. 움직이기
                List<Integer> moved = move(q);

                // 움직이기 3. 다 움직인거 board에 대입하기
                int size = moved.size();
                for (int j = board.length-1, index = 0; j >= 0 && index < size; j--, index++) {
                    board[j][i] = moved.get(index);
                }
            }
        } else if (direction == 3) {
            for (int i = 0; i < board[0].length; i++) {
                // 움직이기 1. Q에 움직일거 넣기
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] != 0) {
                        q.offer(board[i][j]);
                    }
                    board[i][j] = 0;
                }

                // 움직이기 2. 움직이기
                List<Integer> moved = move(q);

                // 움직이기 3. 다 움직인거 board에 대입하기
                int size = moved.size();
                for (int j = 0; j < size; j++) {
                    board[i][j] = moved.get(j);
                }
            }
        }
        return board;
    }

    private static List<Integer> move(Queue<Integer> q) {
        List<Integer> moved = new ArrayList<>();
        if (q.isEmpty()) { // 다 0이라면
            return moved;
        }
        if (q.size() == 1) { // 1개 빼고 다 0이라면
            moved.add(q.poll());
            return moved;
        }

        int cur = q.poll();
        while (true) {
            int next = q.poll();
            if (q.isEmpty()) {
                if (cur == next) {
                    moved.add(cur + next);
                } else {
                    if (cur != 0) {
                        moved.add(cur);
                    }
                    moved.add(next);
                }
                break;
            } else {
                if (cur == next) {
                    moved.add(cur + next);
                    cur = 0;
                } else {
                    if (cur != 0) moved.add(cur);
                    cur = next;
                }
            }
        }
        return moved;
    }

    public static int findMax(int[][] board) {
        int max = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                max = Math.max(max, board[y][x]);
            }
        }
        return max;
    }

    public static void printBoard(int[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                System.out.print(board[y][x] + " ");
            }
            System.out.println();
        }
    }
}