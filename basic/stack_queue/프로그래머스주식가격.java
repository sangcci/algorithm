package basic.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 프로그래머스주식가격 {

    static class Stock {

        int price;
        int time;

        public Stock(int price, int time) {
            this.price = price;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] prices = new int[n];
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(input.nextToken());
        }

        int[] result = new 프로그래머스주식가격().solution(prices);

        StringBuilder sb = new StringBuilder();
        Arrays.stream(result).forEach(each -> {
            sb.append(each).append(" ");
        });
        System.out.println(sb);
    }

    public int[] solution(int[] prices) {
        Queue<Stock> q = new LinkedList<>();
        for (int i = 0; i < prices.length; i++) {
            q.offer(new Stock(prices[i], i));
        }

        List<Integer> times = new ArrayList<>();

        while (!q.isEmpty()) {
            Stock cur = q.poll();
            if (cur.time == prices.length - 1) {
                break;
            }
            int time = 0;
            for (int i = cur.time + 1; i < prices.length; i++) {
                if (cur.price <= prices[i]) {
                    time++;
                } else {
                    time++;
                    break;
                }
            }
            times.add(time);

        }
        times.add(0);
        return times.stream().mapToInt(i -> i).toArray();
    }
}
