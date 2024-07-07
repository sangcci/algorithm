package basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 프로그래머스모음사전 {

    List<String> sequences;
    char[] chars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        int result = new 프로그래머스모음사전().solution(word);
        System.out.println(result);
    }

    public int solution(String word) {
        // init
        chars = new char[]{'A', 'E', 'I', 'O', 'U'};
        sequences = new ArrayList<>();

        // dfs
        dfs("", 0);

        // find
        int answer = sequences.indexOf(word);
        return answer;
    }

    public void dfs(String s, int level) {
        sequences.add(s);
        if (level == 5) {
            // nothing
        } else {
            for (int i = 0; i < 5; i++) {
                dfs(s + chars[i], level + 1);
            }
        }
    }
}
