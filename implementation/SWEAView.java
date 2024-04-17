package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEAView {

    static final int FLOOR = 1;
    static final int SKY = 0;
    static int n; // building count
    static int[] buildingFloorCount;
    static int[][] buildings;
    static int count = 0;

    /*
14
0 0 3 5 2 4 9 0 6 4 0 6 0 0
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        buildings = new int[255][n];
        buildingFloorCount = new int[n];

        // make buildings
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        for (int x = 0; x < n; x++) {
            int building = Integer.parseInt(input.nextToken());
            buildingFloorCount[x] = building;
            for (int floor = 254; floor >= 254 - building + 1; floor--) {
                buildings[floor][x] = FLOOR;
            }
        }

        int answer = compare();

        System.out.println(answer);
    }

    public static int compare() {
        for (int x = 2; x < n-2; x++) {
            for (int y = 254; y >= 254 - buildingFloorCount[x] + 1; y--) {
                boolean left = false;
                boolean right = false;
                if (buildings[y][x+1] == SKY && buildings[y][x+2] == SKY) right = true;

                if (buildings[y][x-1] == SKY && buildings[y][x-2] == SKY) left = true;

                if (right && left) count++;
            }
        }
        return count;
    }

    public static void testPrint(int[][] arr) {
        for (int y = 0; y < 255; y++) {
            System.out.println();
            for (int x = 0; x < arr[0].length; x++) {
                System.out.print(arr[y][x] + " ");
            }
        }
        System.out.println();
    }
}
