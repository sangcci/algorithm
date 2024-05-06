package basic.array;
/*
[[ 안전지대 ]]
2차원 배열을 이용해 구현

요구사항 - 위험지역이 없는 (0인) 곳을 카운트하자

유효성 검사 - 지뢰 겉에부분이 indexOutOfBoundException 발생 여부 판단해야 함
이를 위해 함수를 넣어서 최대한 중복없게, 역할을 나누어 구현

 */
import java.util.Scanner;

public class SafeZone {
    public boolean validateIndexBound(int end, int i, int j) {
        if((i > end) || (i < 0)) return false;
        else if((j > end) || (j < 0)) return false;
        return true;
    }

    public void putBumb(int[][] board, int end, int i, int j) {
        if(validateIndexBound(end, i, j)) {
            board[i][j] = 1;
        }
    }

    public int solution(int[][] board) {
        int answer = 0;
        int end = board.length-1;

        int[][] copyBoard = new int[end+1][end+1];
        for(int i = 0; i <= end; i++) {
            copyBoard[i] = board[i].clone();
        }
        for(int i = 0; i <=  end; i++) {
            for(int j = 0; j <= end; j++) {
                if(board[i][j] == 1) {
                    putBumb(copyBoard, end, i-1, j-1);
                    putBumb(copyBoard, end, i-1, j);
                    putBumb(copyBoard, end, i-1, j+1);
                    putBumb(copyBoard, end, i, j-1);
                    putBumb(copyBoard, end, i, j+1);
                    putBumb(copyBoard, end, i+1, j-1);
                    putBumb(copyBoard, end, i+1, j);
                    putBumb(copyBoard, end, i+1, j+1);
                }
            }
        }

        for(int i = 0; i <= end; i++) {
            for(int j = 0; j <= end; j++) {
                System.out.print(copyBoard[i][j] + " ");
                if(copyBoard[i][j] == 0) answer++;
            }
            System.out.println();
        }

        return answer;
    }

    public static void main(String[] args) {
        SafeZone m = new SafeZone();
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
