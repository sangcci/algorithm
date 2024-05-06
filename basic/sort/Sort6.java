package basic.sort;
/*
[[ 장난꾸러기 ]]
120 125 152 130 135 135 143 127 160

정렬된 배열과 비교

얕은 복사와 깊은 복사를 이해해야 함
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sort6 {
    public ArrayList<Integer> solution(int[] arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        int[] compareArr = arr.clone();

        Arrays.sort(compareArr);

        for(int i = 0; i < arr.length; i++) {
            if(compareArr[i] != arr[i]) {
                answer.add(i+1);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Sort6 m = new Sort6();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        for(Integer e : m.solution(arr)) {
            System.out.print(e + " ");
        }
    }
}
