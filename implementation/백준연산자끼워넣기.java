package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준연산자끼워넣기 {

    // const
    static final int PLUS = 0;
    static final int MINUS = 1;
    static final int MULTIPLY = 2;
    static final int DIVIDE = 3;

    // input
    static int n;
    static int[] num;
    static int[] operatorCount;

    // output
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.println(max);
        System.out.println(min);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        num = new int[n];
        StringTokenizer nums = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(nums.nextToken());
        }

        operatorCount = new int[4];
        StringTokenizer operators = new StringTokenizer(br.readLine(), " ");
        operatorCount[PLUS] = Integer.parseInt(operators.nextToken());
        operatorCount[MINUS] = Integer.parseInt(operators.nextToken());
        operatorCount[MULTIPLY] = Integer.parseInt(operators.nextToken());
        operatorCount[DIVIDE] = Integer.parseInt(operators.nextToken());
    }

    public static void bfs() {
        Queue<Operator> q = new LinkedList<>();
        int level = 0;
        q.offer(new Operator(operatorCount, new int[n-1]));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0 ; i < size; i++) {
                Operator cur = q.poll();
                int[] curCount = cur.remainOperatorCount;
                int[] curOpers = cur.operators;
                if (level == n-1) {
                    // 계산하기
                    int result = calculateFormulas(curOpers);
                    // 최대 최소 비교하기
                    setMinMax(result);
                } else {
                    // 연산자 넣기
                    for (int oper = 0; oper < 4; oper++) {
                        if (curCount[oper] != 0) {
                            q.offer(setOper(curOpers.clone(), curCount.clone(), level, oper));
                        }
                    }
                }
            }
            level++;
        }
    }

    public static Operator setOper(int[] operators, int[] operatorCount, int index, int op) {
        operators[index] = op;
        operatorCount[op] -= 1;
        return new Operator(operatorCount, operators);
    }

    public static int calculateFormulas(int[] operators) {
        int result = num[0];
        for (int i = 0; i < n-1; i++) {
            result = cal(result, num[i + 1], operators[i]);
        }
        return result;
    }

    public static int cal(int num1, int num2, int op) {
        if (op == PLUS) {
            return num1 + num2;
        } else if (op == MINUS) {
            return num1 - num2;
        } else if (op == MULTIPLY) {
            return num1 * num2;
        } else if (op == DIVIDE) {
            if (num1 < 0 && num2 > 0) {
                num1 = Math.abs(num1);
                return -(num1/num2);
            } else if (num1 > 0 && num2 < 0) {
                num2 = Math.abs(num2);
                return -(num1/num2);
            } else if (num1 < 0 && num2 < 0) {
                num1 = Math.abs(num1);
                num2 = Math.abs(num2);
                return num1/num2;
            } else {
                return num1/num2;
            }

        }
        throw new IllegalArgumentException();
    }

    public static void setMinMax(int result) {
        max = Math.max(max, result);
        min = Math.min(min, result);
    }

    public static void testOutput() {
        Arrays.stream(num).forEach(System.out::println);
        for (int oper = 0; oper < 4; oper++) {
            System.out.println(oper + " " + operatorCount[oper]);
        }
    }

    public static void testCurOperator(int[] test) {
        Arrays.stream(test).forEach(each -> {
            System.out.print(each + " ");
        });
        System.out.println();
    }
}

class Operator {
    int[] remainOperatorCount;
    int[] operators;

    public Operator(int[] remainOperatorCount, int[] operators) {
        this.remainOperatorCount = remainOperatorCount;
        this.operators = operators;
    }
}
