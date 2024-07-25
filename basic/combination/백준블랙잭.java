package basic.combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준블랙잭 {

    static int[] cards;
    static int n, m, answer, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(input.nextToken());
        m = Integer.parseInt(input.nextToken());

        cards = new int[n];
        StringTokenizer cardInput = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(cardInput.nextToken());
        }

        answer = 0;
        min = Integer.MAX_VALUE;

        repeat();

        System.out.println(answer);
    }

    public static void repeat() {
        for (int fst = 0; fst < n; fst++) {
            for (int snd = fst + 1; snd < n; snd++) {
                for (int trd = snd + 1; trd < n; trd++) {
                    int sum = cards[fst] + cards[snd] + cards[trd];
                    if (sum <= m && (m - sum) < min) {
                        min = m - sum;
                        answer = sum;
                    }
                }
            }
        }
    }
}

/*
     21
     양의 정수
     N장의 카드 모두 보임
     딜러 M 외침
     M N장 중에서 3장 골라야 함. M을 넘지 않는데 최대한 가깝게 만들어야 함

     완탐?
     1억 = 1초
     100 * 99 * 98 = 약 90만

     탐색하려는 컬렉션이 매 탐색마다 변하지 않고 고정된다면 그냥 반복문 구현 가능
     하지만 매 차례마다 달라진다면, 재귀로 구현. 갯수가 정해지지 않은 거임.
     여기서 카드 팩이 3개 있다면, 재귀로 구현, 카드 팩이 1개로 같다면 반복 가능.
     */