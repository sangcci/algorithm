import java.util.*;

public class Main {

    public int solution(int[][] board) {
        int answer = 0;

        return answer;
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        int[][] board = new int[N][N];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                board[i][j] = in.nextInt();
            }
        }
        System.out.print(m.solution(board));
    }
}