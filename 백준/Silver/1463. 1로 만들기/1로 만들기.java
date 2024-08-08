import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int result = bfs(n);

        System.out.println(result);
    }

    public static int bfs(int num) {
        boolean[] visited = new boolean[num + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(num);
        int level = 0;

        bfs: while (!q.isEmpty()) {
            //System.out.println("-----------");
            //System.out.println("level: " + level);
            int size = q.size();
            //System.out.println("현재 Queue 내부");
            //q.forEach(each -> System.out.print(each + " "));
            //System.out.println();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                //System.out.println("cur: " + cur);
                // check answer
                if (cur == 1) {
                    break bfs;
                }
                // search next
                if (cur == 0) {
                    continue;
                }
                if (cur % 3 == 0 && !visited[cur/3]) {
                    q.offer(cur/3);
                }
                if (cur % 2 == 0 && !visited[cur/2]) {
                    q.offer(cur/2);
                }
                if (!visited[cur-1]) {
                    q.offer(cur-1);
                }
            }
            level++;
        }

        return level;
    }
}
