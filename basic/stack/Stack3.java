package basic.stack;

/*
[[ 크레인 인형뽑기(카카오) ]]
절차
moves에서 pos 계산
board에서 0이 아닌 수 꺼내고 0으로 고치기
stack에 있는 마지막 값과 비교 후 같으면 pop, answer에 2개 추가

유의
- 0은 들어갈 수 없다 -> 조건 활용
- board에서 빼낸 다음 그 곳을 0으로 고쳐야 됨
- 꺼낸 건 stack에 있는 마지막 값과 비교
- 실수하지 말자
 */

import java.util.Scanner;
import java.util.Stack;

public class Stack3 {
    public int solution(char[][] board, int[] moves) {
        int answer = 0;
        Stack<Character> s = new Stack<>();
        s.add('"');

        for(int pos : moves) {
            int choice = pos - 1;

            for(int j = 0; j < board.length; j++) {
                if(board[j][choice] != '0') {
                    if(board[j][choice] == s.peek()) {
                        s.pop();
                        answer += 2;
                    }
                    else {
                        s.add(board[j][choice]);
                    }
                    board[j][choice] = '0';
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args)  {
        Stack3 m = new Stack3();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        char[][] board = new char[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                board[i][j] = in.next().charAt(0);
            }
        }

        N = in.nextInt();
        int[] moves = new int[N];
        for(int i = 0; i < N; i++) {
            moves[i] = in.nextInt();
        }

        System.out.println(m.solution(board, moves));
    }
}
