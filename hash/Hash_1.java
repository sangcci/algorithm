package hash;

/*
key value 형태인 Map 컬랙션 중
HashMap을 사용
HashMap은 Key를 중복 저장할 수 없으므로
해당 문제에 적합
 */
import java.util.*;

class Hash_1 {
    public char solution(int n, String result) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int max = 0;
        char max_key = ' ';

        for (char c : result.toCharArray()) {
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }
        for (char key : hashMap.keySet()){
            if(hashMap.get(key) > max) {
                max = hashMap.get(key);
                max_key = key;
            }
        }
        return max_key;
    }

    public static void main(String[] args) {
        Hash_1 m = new Hash_1();
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        String result = s.next();
        System.out.println(m.solution(n, result));
    }
}
