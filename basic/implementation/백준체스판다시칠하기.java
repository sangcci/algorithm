package basic.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준체스판다시칠하기 {

    static int n, m, min;
    static char[][] board, sample, sampleW, sampleB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(input.nextToken());
        m = Integer.parseInt(input.nextToken());
        board = new char[n][m];
        for (int r = 0; r < n; r++) {
            String[] chess = br.readLine().split("");
            for (int c = 0; c < m; c++) {
                board[r][c] = chess[c].charAt(0);
            }
        }
        sampleB = new char[8][8];
        char ch = 'B';
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                sampleB[r][c] = ch;
                if (ch == 'B') ch = 'W';
                else if (ch == 'W') ch = 'B';
            }
            if (ch == 'B') ch = 'W';
            else if (ch == 'W') ch = 'B';
        }
        sampleW = new char[8][8];
        ch = 'W';
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                sampleW[r][c] = ch;
                if (ch == 'B') ch = 'W';
                else if (ch == 'W') ch = 'B';
            }
            if (ch == 'B') ch = 'W';
            else if (ch == 'W') ch = 'B';
        }
        /*testPrint(sampleB);
        testPrint(sampleW);*/
        min = Integer.MAX_VALUE;

        for (int r = 0; r <= n - 8; r++) {
            for (int c = 0; c <= m - 8; c++) {
                sample = makeSample(r, c);
                //testPrint(sample);
                find();
            }
        }

        System.out.println(min);
    }

    private static void find() {
        int cntB = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (sample[r][c] != sampleB[r][c])
                    cntB++;
            }
        }
        int cntW = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (sample[r][c] != sampleW[r][c])
                    cntW++;
            }
        }
        min = Math.min(min, Math.min(cntB, cntW));
    }

    public static char[][] makeSample(int startR, int startC) {
        char[][] sample = new char[8][8];
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                sample[r][c] = board[startR + r][startC + c];
            }
        }
        return sample;
    }



    public static void testPrint(char[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                System.out.print(arr[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
