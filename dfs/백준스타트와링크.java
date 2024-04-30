package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준스타트와링크 {

    static int n;
    static int[][] S;
    static int[] members;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        S = new int[n][n];
        for (int y = 0; y < n; y++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < n; x++) {
                S[y][x] = Integer.parseInt(input.nextToken());
            }
        }
        members = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            members[i] = i;
        }
        int[] start = new int[n / 2];
        for (int i = 0; i < 2 / n; i++) {
            start[i] = 0;
        }
        min = Integer.MAX_VALUE;

        dfs(0, start, 0);

        System.out.println(min);
    }

    public static void dfs(int level, int[] start, int next) {
        if (level >= n / 2) {
            int[] link = createLinkTeam(start);
            int diff = Math.abs(sum(start) - sum(link));
            min = Math.min(min, diff);
        } else {
            for (int i = next + 1; i <= n; i++) {
                start[level] = members[i];
                dfs(level + 1, start, i);
                start[level] = 0;
            }
        }
    }

    public static int[] createLinkTeam(int[] start) {
        List<Integer> link = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            boolean flag = false;
            for (int j = 0; j < start.length; j++) {
                if (members[i] == start[j]) {
                    flag = true;
                }
            }
            if (!flag) {
                link.add(members[i]);
            }
        }
        return link.stream().mapToInt(each -> each).toArray();
    }

    public static int sum(int[] team) {
        int sum = 0;
        for (int i = 0; i < team.length; i++) {
            for (int j = 0; j < team.length; j++) {
                sum += S[team[i] - 1][team[j] - 1];
            }
        }
        return sum;
    }

    public static void testPrint(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
