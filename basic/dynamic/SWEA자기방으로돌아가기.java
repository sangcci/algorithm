package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA자기방으로돌아가기 {

    static int n, time;
    static int[] canUseCorridor;

    static class Student {

        int startNum, endNum;

        public Student(int startNum, int endNum) {
            this.startNum = startNum;
            this.endNum = endNum;
        }

        public void swap() {
            if (startNum > endNum) {
                int tmp = startNum;
                startNum = endNum;
                endNum = tmp;
            }
        }
    }

    static Student[] students;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());
            students = new Student[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer input = new StringTokenizer(br.readLine(), " ");
                int startNum = Integer.parseInt(input.nextToken());
                int endNum = Integer.parseInt((input.nextToken()));
                Student student = new Student(startNum, endNum);
                student.swap();
                students[i] = student;
            }
            canUseCorridor = new int[201];

            time = 1;

            dp();

            output.append("#" + test_case + " " + time + "\n");
        }
        System.out.println(output);
    }

    public static void dp() {
        for (int i = 0; i < n; i++) {
            Student student = students[i];
            int start = calCorridorIndex(student.startNum);
            int end = calCorridorIndex(student.endNum);
            for (int j = start; j <= end; j++) {
                canUseCorridor[j] += 1;
            }
        }

        for (int i = 1; i < canUseCorridor.length; i++) {
            time = Math.max(time, canUseCorridor[i]);
        }
    }

    public static int calCorridorIndex(int num) {
        if (num % 2 == 1) {
            return (num / 2) + 1;
        } else {
            return num / 2;
        }
    }
}
