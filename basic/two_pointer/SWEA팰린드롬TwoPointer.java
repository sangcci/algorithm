package basic.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA팰린드롬TwoPointer {

    static int n;
    static char[][] board;
    static int cnt;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        StringBuilder answer = new StringBuilder();
        board = new char[8][8];
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());

            for (int y = 0; y < 8; y++) {
                String[] input = br.readLine().split("");
                for (int x = 0; x < 8; x++) {
                    board[y][x] = input[x].charAt(0);
                }
            }
            cnt = 0;

            palindrome();

            answer.append("#" + test_case + " " + cnt + "\n");
        }
        System.out.println(answer);
    }

    public static void palindrome() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8-n+1; x++) {
                flag = true;
                for (int i = 0; i < n/2; i++) {
                    if (board[y][x+i] != board[y][(x+n-1)-i]) flag = false;
                }
                if (flag) cnt++;
            }
        }

        for (int y = 0; y < 8-n+1; y++) {
            for (int x= 0; x < 8; x++) {
                flag = true;
                for (int i = 0; i < n/2; i++) {
                    if (board[y+i][x] != board[(y+n-1)-i][x]) flag = false;
                }
                if (flag) cnt++;
            }
        }
    }

}
