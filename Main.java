
/*
구간별로 나누고(two pointer + sliding window) 구간별 매출액 종류를 구한다(HashMap).

원래는 put을 사용해 매출액 하나하나를 Key값으로 하여
중복이 발생할 경우 카운트가 2 이상이 되므로 이를 이용해서
걸러내려 했는데

애초에 Key가 몇개 있는지 비교하면 되는 일이라서
한번 비교하고 clear하면 끝. -> clear하면 timeout 발생, 비효율적
sliding window의 장점인 탐색의 중복 방지 활용
 */

/*
대각선으로 숫자 채우기

배열에 있는 수를 터미널에 출력시키기 위해서는
Enter 개행마다 출력하는 방법밖에 없다.
그래서 처음에는 1 2 4 7.. 이렇게 단계적으로 올라가기 때문에
이에 대한 규칙을 찾아서 대입하려 했지만
너무 복잡했다.

배열의 index를 조절함에 따라
그 자리에 숫자를 넣는 방식으로 하면 쉽다.
 */
import java.util.*;

class Main {
    public String solution(char[] brackets) {
        String answer = "";

        return answer;
    }

    public static void main(String[] args)  {
        Main m = new Main();
        Scanner in = new Scanner(System.in);

    }
}