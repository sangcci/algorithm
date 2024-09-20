import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final Set<Integer> set = new HashSet<>();
    private static final List<Integer> oneToTwenty = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            String command = input.nextToken();
            int x = 0;
            if (input.hasMoreTokens()) {
                x = Integer.parseInt(input.nextToken());
            }

            String result = play(command, x);

            if (!result.isEmpty()) {
                sb.append(result);
            }
        }

        System.out.println(sb);
    }

    public static String play(String command, int x) {
        switch (command) {
            case "add": {
                set.add(x);
                break;
            }
            case "remove": {
                set.remove(x);
                break;
            }
            case "check": {
                if (set.contains(x)) {
                    return "1\n";
                } else {
                    return "0\n";
                }
            }
            case "toggle": {
                if (set.contains(x)) {
                    set.remove(x);
                } else {
                    set.add(x);
                }
                break;
            }
            case "all": {
                set.clear();
                set.addAll(oneToTwenty);
                break;
            }
            case "empty": {
                set.clear();
                break;
            }
        }
        return "";
    }
}
