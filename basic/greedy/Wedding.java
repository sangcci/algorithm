package basic.greedy;
/*
[[ 결혼식 ]]
Java 알고리즘 강의 basic.greedy
 */
import java.util.*;
public class Wedding {
    static class Time implements Comparable<Time> {
        public int time;
        public char status;
        public Time(int time, char status) {
            this.time = time;
            this.status = status;
        }
        @Override
        public int compareTo(Time o) {
            if (this.time == o.time) return this.status - o.status;
            return this.time - o.time;
        }
    }
    static int n;
    static ArrayList<Time> times;
    static int max;
    public void greedy() {
        Collections.sort(times);
        for (Time time : times) {
            System.out.println(time.time + " " + time.status);
        }
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            Time time = times.get(i);
            if (time.status == 'e') {
                cnt--;
            } else {
                cnt++;
            }
            max = Math.max(max, cnt);
        }
    }
    public static void main(String[] args) {
        Wedding m = new Wedding();
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        times = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            times.add(new Time(in.nextInt(), 's'));
            times.add(new Time(in.nextInt(), 'e'));
        }
        max = Integer.MIN_VALUE;

        m.greedy();
        System.out.println(max);
    }
}