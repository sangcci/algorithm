package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준스타트와링크 {

    static int n;
    static int[] members;
    static int[][] abilities;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        divideTeamInDfs(0, 0, new boolean[n]);
        System.out.println(min);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        members = new int[n];
        for (int i = 0; i < n; i++) {
            members[i] = i + 1;
        }

        abilities = new int[n][n];
        for (int y = 0; y < n; y++) {
            StringTokenizer abilityInput = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < n; x++) {
                abilities[y][x] = Integer.parseInt(abilityInput.nextToken());
            }
        }
    }

    /*
     팀 선정 (dfs 활용)
     */
    public static void divideTeamInDfs(int level, int count, boolean[] chooseMembers) {
        if (count == n/2) {
            int[] start = new int[n/2];
            int[] link = new int[n/2];
            for (int i = 0, startIndex = 0, linkIndex = 0; i < chooseMembers.length; i++) {
                if (chooseMembers[i] == true) {
                    start[startIndex++] = i + 1;
                } else {
                    link[linkIndex++] = i + 1;
                }
            }
            int startAbilitySum = calculateTeamAbilityInDfs(0, 0, new boolean[start.length], start, 0);
            int linkAbilitySum = calculateTeamAbilityInDfs(0, 0, new boolean[link.length], link, 0);
            int diff = Math.abs(startAbilitySum - linkAbilitySum);
            min = Math.min(min, diff);
        } else if (level >= n) return;
        else {
            chooseMembers[level] = true;
            divideTeamInDfs(level + 1, count + 1, chooseMembers);
            chooseMembers[level] = false;
            divideTeamInDfs(level + 1, count, chooseMembers);
        }
    }

    /*
     팀 능력 계산 (dfs 활용)
     */
    public static int calculateTeamAbilityInDfs(int level, int count, boolean[] chosenTeam, int[] team, int sum) {
        if (count == 2) {
            int[] chosen = new int[2];
            for (int i = 0, chosenIndex = 0; i < chosenTeam.length; i++) {
                if (chosenTeam[i] == true) {
                    chosen[chosenIndex++] = team[i];
                }
            }
            int a = chosen[0] - 1;
            int b = chosen[1] - 1;
            sum += abilities[a][b] + abilities[b][a];
        } else if (level >= n/2) return sum;
        else {
            chosenTeam[level] = true;
            sum = calculateTeamAbilityInDfs(level + 1, count + 1, chosenTeam, team, sum);
            chosenTeam[level] = false;
            sum = calculateTeamAbilityInDfs(level + 1, count, chosenTeam, team, sum);
        }
        return sum;
    }
}
