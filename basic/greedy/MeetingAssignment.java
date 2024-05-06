package basic.greedy;
/*
[[ 회의실 배정 ]]
Java 알고리즘 강의 문제
백준 문제

 */
import java.util.*;
public class MeetingAssignment {
    static class Meeting implements Comparable<Meeting>{
        public int start;
        public int end;
        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.end == o.end) return this.start - o.start;
            return this.end - o.end;
        }
    }
    static int n;
    static ArrayList<Meeting> meetings;
    static int max;
    public void greedy() {
        Collections.sort(meetings);
        int end = 0;

        for (int i = 0; i < n; i++) {
            if (meetings.get(i).start >= end) {
                end = meetings.get(i).end;
                max++;
            }
        }
    }
    public static void main(String[] args) {
        MeetingAssignment m = new MeetingAssignment();
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        meetings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            meetings.add(new Meeting(in.nextInt(), in.nextInt()));
        }
        max = 0;

        m.greedy();
        System.out.println(max);
    }
}