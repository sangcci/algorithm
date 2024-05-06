package basic.array;

/*
대각선으로 숫자 채우기

배열 - 반복되는 동작 + 시작점

배열에 있는 수를 터미널에 출력시키기 위해서는
Enter 개행마다 출력하는 방법밖에 없다.
그래서 처음에는 1 2 4 7.. 이렇게 단계적으로 올라가기 때문에
이에 대한 규칙을 찾아서 대입하려 했지만
너무 복잡했다.

배열의 index를 조절함에 따라
그 자리에 숫자를 넣는 방식으로 하면 된다.
대각선을 따라 대입하는 반복동작 + 위에서 오른쪽 아래로 시작점
 */
import java.util.Scanner;

public class Array2D {
    public int[][] solution(int row, int column) {
        int[][] arr = new int[row][column];

        int row_start = 0;
        int column_start = 0;
        int tmp = 1;

        // step 1
        for(int i = 0; i < column; i++) {
            // -> 이동
            row_start = 0;
            column_start = i;
            // 대각선에서 화살표 끝까지 가야돼
            while(column_start >= 0 && row_start <= (row-1)) {
                arr[row_start][column_start] = tmp;

                row_start++;
                column_start--;
                tmp++;
            }
        }

        // step 2
        for(int j = 1; j < row; j++) {
            // ↓ 이동 : column 고정, row++
            row_start = j;
            column_start = (column - 1);
            // 화살표 시작점부터 끝까지 index 이동하면서 대입
            while(column_start > (column - row) && row_start <= row - 1) {
                arr[row_start][column_start] = tmp;

                row_start++;
                column_start--;
                tmp++;
            }
        }

        return arr;
    }

    public static void main(String[] args)  {
        Array2D m = new Array2D() ;
        Scanner in = new Scanner(System.in);

        int row = in.nextInt();
        int column = in.nextInt();

        int[][] answer = m.solution(row, column);

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }
}
