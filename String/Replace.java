package String;
/*
[[ 영어가 싫어요 - 프로그래머스 입문 ]]

1. 맨 앞에 문자를 비교(5글자까지)
2. 그 문자 내용을 삭제하고 꺼냄 -> answer엔 숫자 추가
반복

이짓 하기에는 너무 힘듬 -> 무조껀 앞에서부터 꺼내서 처리해야 된다고 생각하지 말자

그냥 대입해서 있는거 바꾸면 될 일
=> replace("one", 1) 메서드 사용
 */
import java.util.Scanner;

public class Replace {
    public long solution(String numbers) {

        String[] ZeroToNine = {"zero", "one", "two", "three", "four", "five"
                ,"six", "seven", "eight", "nine"};

        for(int i = 0; i < ZeroToNine.length; i++) {
            numbers = numbers.replaceAll(ZeroToNine[i], String.valueOf(i));
        }

        return Long.parseLong(numbers);
    }

    public static void main(String[] args) {
        Replace m = new Replace();
        Scanner in = new Scanner(System.in);

        String numbers = in.next();

        System.out.print(m.solution(numbers));
    }
}
