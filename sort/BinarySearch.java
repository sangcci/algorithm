package sort;

/*
[[ 이분 검색 ]]
12 23 32 57 65 81 87 99

idx 하나만 놓고보면 안됨.
lt, rt를 놓고 mid를 구하고 rt를 수정하던가 lt를 수정해야 함

mid를 기준으로 M값과 비교
arr[mid]도 비교했으므로 이값도 고려해서 mid -1, mid +1함
 */
import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public int solution(int M, int[] arr) {
        int lt = 0;
        int rt = arr.length -1;
        int mid = (lt + rt) /2;

        Arrays.sort(arr);

        while(lt <= rt) {
            if(M == arr[mid]) return mid +1;
            else if(M < arr[mid]) rt = mid -1;
            else if(M > arr[mid]) lt = mid +1;

            mid = (lt + rt) /2;
        }

        return -1;
    }

    public static void main(String[] args) {
        BinarySearch m = new BinarySearch();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int M = in.nextInt();

        int[] arr = new int[N];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        System.out.print(m.solution(M, arr));
    }
}
