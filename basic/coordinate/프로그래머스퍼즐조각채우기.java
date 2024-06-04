package basic.coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 프로그래머스퍼즐조각채우기 {

    int size, cnt;
    List<List<Point>> blanks, shapes;
    static class Point implements Comparable<Point> {
        int y, x;
        Point(int y, int x) {this.y = y; this.x = x;}

        @Override
        public int compareTo(Point o) {
            if (this.y == o.y) {
                return this.x - o.x;
            } else {
                return this.y - o.y;
            }
        }
    }
    static final int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        프로그래머스퍼즐조각채우기 m = new 프로그래머스퍼즐조각채우기();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // input
        int[][] game_board = m.inputArr2D(br);
        int[][] table = m.inputArr2D(br);
        m.size = game_board.length;

        // main logic
        int answer = m.solution(game_board, table);

        // output
        System.out.println(answer);
    }

    int[][] inputArr2D(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] arr2D = new int[n][n];
        for (int r = 0; r < n; r++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < n; c++) {
                arr2D[r][c] = Integer.parseInt(input.nextToken());
            }
        }
        return arr2D;
    }

    int solution(int[][] game_board, int[][] table) {
        // 1. game_board 빈칸 추출
        blanks = new ArrayList<>();
        boolean[][] visited = new boolean[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (!visited[y][x] && game_board[y][x] == 0) {
                    List<Point> blank = findShape(y, x, game_board, 0, visited);
                    blanks.add(blank);
                }
            }
        }
        // 2. table 도형 추출
        shapes = new ArrayList<>();
        visited = new boolean[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (!visited[y][x] && table[y][x] == 1) {
                    List<Point> shape = findShape(y, x, table, 1, visited);
                    shapes.add(shape);
                }
            }
        }

        // 3. 빈칸과 도형이 일치하는지 비교
        cnt = 0;
        boolean[] visited_shape = new boolean[shapes.size()];
        Arrays.fill(visited_shape, false);
        for (List<Point> blank : blanks) {
            // blank 좌표 정규화 (java는 call by address라서 굳이 반환 안해도 됨)
            normalize(blank);

            shape: for (int i = 0; i < shapes.size(); i++) {
                // 이전에 통과했던 도형은 이후 비교 X
                if (visited_shape[i]) {
                    continue;
                }
                List<Point> shape = shapes.get(i);
                for (int rotate = 0; rotate < 4; rotate++) {
                    // shape 좌표 정규화
                    normalize(shape);

                    // 비교
                    if (compare(blank, shape)) {
                        cnt += shape.size();
                        visited_shape[i] = true;
                        break shape;
                    }

                    // 회전
                    rotate(shape);
                }
            }
        }

        return cnt;
    }

    List<Point> findShape(int y, int x, int[][] board, int find, boolean[][] visited) {
        List<Point> shape = new ArrayList<>();
        shape.add(new Point(0, 0));

        // bfs
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(y, x));
        visited[y][x] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point cur = q.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nextY = cur.y + move[dir][0];
                    int nextX = cur.x + move[dir][1];
                    if (checkBoundary(nextY, nextX) && !visited[nextY][nextX]
                            && board[nextY][nextX] == find) {
                        visited[nextY][nextX] = true;
                        shape.add(new Point(nextY - y ,nextX - x));
                        q.offer(new Point(nextY, nextX));
                    }
                }
            }
        }

        return shape;
    }

    boolean checkBoundary(int y, int x) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    void normalize(List<Point> points) {
        Collections.sort(points);
        int stdY = points.get(0).y;
        int stdX = points.get(0).x;
        for (Point p : points) {
            p.y -= stdY;
            p.x -= stdX;
        }
    }

    boolean compare(List<Point> blank, List<Point> shape) {
        boolean isSame = true;
        if (blank.size() != shape.size()) {
            isSame = false;
        } else {
            for (int i = 0; i < blank.size(); i++) {
                if (blank.get(i).y != shape.get(i).y) {
                    isSame = false;
                    break;
                }
                if (blank.get(i).x != shape.get(i).x) {
                    isSame = false;
                    break;
                }
            }
        }
        return isSame;
    }

    void rotate(List<Point> shape) {
        for (Point p : shape) {
            int tmp = p.y;
            p.y = p.x;
            p.x = -tmp;
        }
    }

    void testPrint(List<Point> points) {
        StringBuilder sb = new StringBuilder();
        for (Point p : points) {
            sb.append(p.y + " " + p.x + "\n");
        }
        System.out.println(sb);
    }
}