package basic.dynamic;
/*
[[ 돌다리 건너기 ]]
Java 알고리즘 강의
 */
import java.util.*;
public class CrossTheStoneBridge {
    public int dynamic(int n) {
        int[] dynamicTable = new int[n+2];

        dynamicTable[1] = 1;
        dynamicTable[2] = 2;

        for (int i = 3; i <= n+1; i++) {
            dynamicTable[i] = dynamicTable[i-2] + dynamicTable[i-1];
        }
        return dynamicTable[n+1];
    }

    public static void main(String[] args) {
        CrossTheStoneBridge m = new CrossTheStoneBridge();
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        System.out.println(m.dynamic(n));
    }
}
