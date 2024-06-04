package basic.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class 프로그래머스전화번호목록 {

    public static void main(String[] args) {
        프로그래머스전화번호목록 m = new 프로그래머스전화번호목록();

        String[] phone_book = m.input();

        boolean answer = m.solution(phone_book);

        System.out.println(answer);
    }

    String[] input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] phone_book = null;
        try {
            int n = Integer.parseInt(br.readLine());
            phone_book = new String[n];
            for (int i = 0; i < n; i++) {
                phone_book[i] = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phone_book;
    }

    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();
        for (String phone : phone_book) {
            set.add(phone);
        }

        for (int i = 0; i < phone_book.length; i++) {
            //System.out.println("phone " + phone_book[i]);
            for (int j = 0; j < phone_book[i].length(); j++) {
                String head = phone_book[i].substring(0, j);
                //System.out.println(head);
                if (set.contains(head)) {
                    return false;
                }
            }
        }
        return true;
    }
}
