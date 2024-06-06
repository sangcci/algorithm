package basic.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 프로그래머스베스트앨범 {

    static class Sing implements Comparable<Sing> {

        int cnt;
        int num;

        public Sing(int cnt, int num) {
            this.cnt = cnt;
            this.num = num;
        }

        @Override
        public int compareTo(Sing o) {
            if (this.cnt == o.cnt) {
                return this.num - o.num;
            } else {
                return o.cnt - this.cnt;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // init
        프로그래머스베스트앨범 m = new 프로그래머스베스트앨범();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        String[] genres = m.input(br);
        int[] plays = Arrays.stream(m.input(br)).mapToInt(Integer::parseInt).toArray();

        // logic
        int[] result = m.solution(genres, plays);

        // output
        StringBuilder sb = new StringBuilder();
        for (int each : result) {
            sb.append(each + " ");
        }
        System.out.println(sb);
    }

    String[] input(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());

        String[] arr = new String[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = st.nextToken();
        }

        return arr;
    }

    int[] solution(String[] genres, int[] plays) {
        // init
        Map<String, Integer> playSums = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            // play sum init
            int sum = playSums.getOrDefault(genres[i], 0);
            playSums.put(genres[i], sum + plays[i]);
        }

        // sort playSums and sings
        List<String> genreNames = new ArrayList<>();
        for (String name : playSums.keySet()) {
            genreNames.add(name);
        }
        genreNames.sort((o1, o2) -> playSums.get(o2).compareTo(playSums.get(o1)));

        // search
        List<Integer> best = new ArrayList<>();
        for (String genre : genreNames) {
            // find sing
            List<Sing> sings = new ArrayList<>();
            for (int i = 0; i < genres.length; i++) {
                if (genre.equals(genres[i])) {
                    sings.add(new Sing(plays[i], i));
                }
            }

            // sort
            Collections.sort(sings);

            // choice
            int end = 1;
            if (sings.size() >= 2) {
                end = 2;
            }
            for (int i = 0; i < end; i++) {
               best.add(sings.get(i).num);
            }
        }

        // parsing and return
        return best.stream().mapToInt(i -> i).toArray();
    }
}
