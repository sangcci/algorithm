package greedy;
/*
[[ 최대 수입 스케쥴 ]]
Java 알고리즘 강의 그리디

 */
import java.util.*;
public class MaxIncome {
    static class Income implements Comparable<Income> {
        public int money;
        public int day;
        public Income(int money, int day) {
            this.money = money;
            this.day = day;
        }
        @Override
        public int compareTo(Income o) {
            return o.day - this.day;
        }
    }
    static int n;
    static ArrayList<Income> incomes;
    static int cnt; // 최대 일 수
    static int max; // 구하고자 하는 값
    public void greedy() {
        Collections.sort(incomes);
        /*
        for (Income income : incomes) {
            System.out.println(income.money + " " + income.day);
        }
         */
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        int idx = 0;

        for (int i = cnt; i > 0; i--) {
            /*
            System.out.println("day 몇일 차: " + i);
            System.out.println("idx: " + idx);
            System.out.println("현재 pQ 상태");
            for (Integer money : pQ) {
                System.out.println(money);
            }
             */
            while (idx < n) {
                Income income = incomes.get(idx);
                if (income.day < i) {
                    break;
                } else {
                    //System.out.println("queue 추가: " + income.money + " " + income.day);
                    pQ.offer(income.money);
                    idx++;
                }
            }
            /*
            System.out.println("추가된 이후 pQ 상태");
            for (Integer money : pQ) {
                System.out.println(money);
            }
             */

            if (!pQ.isEmpty()) {
                max += pQ.poll();
            }
            /*
            System.out.println("max: " + max);
            System.out.println("--------");
             */
        }
    }

    public static void main(String[] args) {
        MaxIncome m = new MaxIncome();
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        incomes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int money = in.nextInt();
            int day = in.nextInt();
            incomes.add(new Income(money, day));
            cnt = Math.max(day, cnt);
        }
        max = 0;

        m.greedy();
        System.out.println(max);
    }
}