package hash;
import java.util.*;
/*
[[ 영어 끝말잇기 ]]
프로그래머스 2단계
 */
public class WordPlay {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        HashSet<String> chkStr = new HashSet<>();
        chkStr.add(words[0]);

        for(int i = 1; i < words.length; i++) {
            String cw = words[i];
            String pw = words[i-1];
            char cwFst = cw.charAt(0);
            char pwLst = pw.charAt(pw.length() - 1);

            if(cwFst != pwLst || chkStr.contains(cw)) {
                answer[0] = (i%n) + 1;
                answer[1] = (i/n) + 1;
                return answer;
            }

            chkStr.add(cw);
        }
        return answer;
    }
    public static void main(String[] args) {
        WordPlay m = new WordPlay();
        Scanner in = new Scanner(System.in);

        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int[] a = m.solution(3, words);
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}