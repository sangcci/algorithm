import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n + 1];
        Arrays.fill(students, 1);

        for (int lost_num : lost) {
            students[lost_num]--;
        }
        for (int reserve_num : reserve) {
            students[reserve_num]++;
        }
        //printToConsole(students);

        for (int i = 1; i < students.length; i++) {
            if (students[i] == 2) {
                if (i-1 >= 0 && students[i-1] == 0) {
                    students[i]--;
                    students[i-1]++;
                } else if (i+1 < students.length && students[i+1] == 0) {
                    students[i]--;
                    students[i+1]++;
                }
            }
            //printToConsole(students);
        }

        int result = 0;
        for (int i = 1; i < students.length; i++) {
            if (students[i] != 0) {
                result++;
            }
        }

        return result;
    }
}