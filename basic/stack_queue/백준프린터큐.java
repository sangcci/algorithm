package basic.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준프린터큐 {

    static int n, i;

    static class Document {

        int name;
        int important;

        public Document(int name, int important) {
            this.name = name;
            this.important = important;
        }
    }

    static Queue<Document> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(input.nextToken());
            i = Integer.parseInt(input.nextToken());
            StringTokenizer inputDoc = new StringTokenizer(br.readLine(), " ");
            q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                int important = Integer.parseInt(inputDoc.nextToken());
                q.offer(new Document(i, important));
            }

            int answer = playPrinter();
            output.append(answer + "\n");
        }
        System.out.println(output);
    }

    public static int playPrinter() {
        int seq = 0;
        int max = getMax();
        while (true) {
            Document cur = q.peek();
            if (cur.important != max) {
                q.poll();
                q.offer(cur);
            } else {
                q.remove();
                seq++;
                if (cur.name == i) {
                    return seq;
                }
                max = getMax();
            }
        }
    }

    public static int getMax() {
        return q.stream().max((o1, o2) -> o1.important - o2.important).get().important;
    }
}
