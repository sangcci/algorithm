package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준감시 {
    static final int BLANK = 0;
    static final int WALL = 6;
    static int[][] rooms;
    static List<Cctv> cctvs;
    static int[] directs;
    static int cctvCount;
    static int answer = 999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sizeInput = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(sizeInput.nextToken());
        int m = Integer.parseInt(sizeInput.nextToken());

        rooms = new int[n][m];
        cctvs = new ArrayList<>();

        for (int y = 0; y < n; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < m; x++) {
                int cur = Integer.parseInt(st.nextToken());
                rooms[y][x] = cur;
                if (cur >= 1 && cur <= 5) cctvs.add(new Cctv(cur, y, x));
            }
        }
        cctvCount = cctvs.size();

        directs = new int[cctvCount];

        permutation(0);
        System.out.println(answer);
    }

    public static void permutation(int level) {
        if (level == cctvCount) {
            // copy
            int[][] copyRooms = copy(rooms);

            // search
            for (int i = 0; i < cctvs.size(); i++) {
                Cctv cctv = cctvs.get(i);
                int curDir = directs[i];
                if (cctv.num == 1) {
                    if (curDir == 0) searchEast(copyRooms, cctv.y, cctv.x);
                    else if (curDir == 1) searchSouth(copyRooms, cctv.y, cctv.x);
                    else if (curDir == 2) searchWest(copyRooms, cctv.y, cctv.x);
                    else if (curDir == 3) searchNorth(copyRooms, cctv.y, cctv.x);
                } else if (cctv.num == 2) {
                    if (curDir == 0) {
                        searchEast(copyRooms, cctv.y, cctv.x);
                        searchWest(copyRooms, cctv.y, cctv.x);
                    } else if (curDir == 1) {
                        searchSouth(copyRooms, cctv.y, cctv.x);
                        searchNorth(copyRooms, cctv.y, cctv.x);
                    }
                } else if (cctv.num == 3) {
                    if (curDir == 0) {
                        searchNorth(copyRooms, cctv.y, cctv.x);
                        searchEast(copyRooms, cctv.y, cctv.x);
                    } else if (curDir == 1) {
                        searchEast(copyRooms, cctv.y, cctv.x);
                        searchSouth(copyRooms, cctv.y, cctv.x);
                    } else if (curDir == 2) {
                        searchSouth(copyRooms, cctv.y, cctv.x);
                        searchWest(copyRooms, cctv.y, cctv.x);
                    } else if (curDir == 3) {
                        searchWest(copyRooms, cctv.y, cctv.x);
                        searchNorth(copyRooms, cctv.y, cctv.x);
                    }
                } else if (cctv.num == 4) {
                    if (curDir == 0) {
                        searchWest(copyRooms, cctv.y, cctv.x);
                        searchNorth(copyRooms, cctv.y, cctv.x);
                        searchEast(copyRooms, cctv.y, cctv.x);
                    } else if (curDir == 1) {
                        searchNorth(copyRooms, cctv.y, cctv.x);
                        searchEast(copyRooms, cctv.y, cctv.x);
                        searchSouth(copyRooms, cctv.y, cctv.x);
                    } else if (curDir == 2) {
                        searchEast(copyRooms, cctv.y, cctv.x);
                        searchSouth(copyRooms, cctv.y, cctv.x);
                        searchWest(copyRooms, cctv.y, cctv.x);
                    } else if (curDir == 3) {
                        searchSouth(copyRooms, cctv.y, cctv.x);
                        searchWest(copyRooms, cctv.y, cctv.x);
                        searchNorth(copyRooms, cctv.y, cctv.x);
                    }
                } else if (cctv.num == 5) {
                    searchEast(copyRooms, cctv.y, cctv.x);
                    searchSouth(copyRooms, cctv.y, cctv.x);
                    searchWest(copyRooms, cctv.y, cctv.x);
                    searchNorth(copyRooms, cctv.y, cctv.x);
                }
            }

            // count
            int min = searchZero(copyRooms);

            // compare
            answer = Math.min(answer, min);
        } else {
            for (int i = 0; i < 4; i++) {
                directs[level] = i;
                permutation(level + 1);
            }
        }
    }

    public static int[][] searchEast(int[][] rooms, int startY, int startX) {
        int nextY = startY;
        int nextX = startX + 1;
        while (true) {
            if (nextY < 0 || nextY >= rooms.length || nextX < 0 || nextX >= rooms[0].length) {
                break;
            }
            if (rooms[nextY][nextX] == WALL) {
                break;
            }
            rooms[nextY][nextX] = -1;
            nextX += 1;
        }
        return rooms;
    }

    public static int[][] searchWest(int[][] rooms, int startY, int startX) {
        int nextY = startY;
        int nextX = startX - 1;
        while (true) {
            if (nextY < 0 || nextY >= rooms.length || nextX < 0 || nextX >= rooms[0].length) {
                break;
            }
            if (rooms[nextY][nextX] == WALL) {
                break;
            }
            rooms[nextY][nextX] = -1;
            nextX -= 1;
        }
        return rooms;
    }

    public static int[][] searchNorth(int[][] rooms, int startY, int startX) {
        int nextY = startY - 1;
        int nextX = startX;
        while (true) {
            if (nextY < 0 || nextY >= rooms.length || nextX < 0 || nextX >= rooms[0].length) {
                break;
            }
            if (rooms[nextY][nextX] == WALL) {
                break;
            }
            rooms[nextY][nextX] = -1;
            nextY -= 1;
        }
        return rooms;
    }

    public static int[][] searchSouth(int[][] rooms, int startY, int startX) {
        int nextY = startY + 1;
        int nextX = startX;
        while (true) {
            if (nextY < 0 || nextY >= rooms.length || nextX < 0 || nextX >= rooms[0].length) {
                break;
            }
            if (rooms[nextY][nextX] == WALL) {
                break;
            }
            rooms[nextY][nextX] = -1;
            nextY += 1;
        }
        return rooms;
    }
    
    public static int searchZero(int[][] rooms) {
        int count = 0;
        for (int y = 0; y < rooms.length; y++) {
            for (int x = 0; x < rooms[0].length; x++) {
                if (rooms[y][x] == 0) count++;
            }
        }
        return count;
    }

    public static int[][] copy(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                copy[y][x] = arr[y][x];
            }
        }
        return copy;
    }

    public static void printArray(int[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            System.out.println();
            for (int x = 0; x < arr[0].length; x++) {
                System.out.print(arr[y][x] + " ");
            }
        }
        System.out.println();
    }

}
class Cctv {
    int num;
    int y;
    int x;

    public Cctv(int num, int y, int x) {
        this.num = num;
        this.y = y;
        this.x = x;
    }
}