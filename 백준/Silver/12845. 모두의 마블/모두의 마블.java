import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void solution(List<Integer> levels) {
        levels.sort(Collections.reverseOrder());
        int tmp = levels.get(0);
        int answer = 0;

        for (int i = 1; i < levels.size(); i++) {
            answer += tmp + levels.get(i);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        List<Integer> levels = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            levels.add(Integer.parseInt(st.nextToken()));
        }

        solution(levels);
    }
}