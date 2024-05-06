package basic.search;

import java.util.Arrays;
import java.util.Scanner;

public class DicisionAlgorithm2 {
    public int solution(int[] sections, int count) {
        int answer = 0;
        Arrays.sort(sections);
        int lt = 1;
        int rt = sections[sections.length-1] - sections[0];

        while(lt<=rt) {
            int mid = (lt+rt)/2;
            if(isHorseCount(sections, mid) >= count) {
                answer = mid;
                lt = mid +1;
            } else {
                rt = mid -1;
            }
        }
        return answer;
    }

    public int isHorseCount(int[] sections, int dist) {
        int count = 1;
        int ep = sections[0];
        int arrLength = sections.length;

        for(int i = 1; i < arrLength; i++) {
            if((sections[i]-ep) >= dist) {
                count++;
                ep = sections[i];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        DicisionAlgorithm2 m = new DicisionAlgorithm2();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int C = in.nextInt();

        int[] sections = new int[N];
        for(int i = 0; i < sections.length; i++) {
            sections[i] = in.nextInt();
        }
        System.out.print(m.solution(sections, C));
    }
}
