package greedy;

/*
[[ 씨름 선수 ]]
Java 알고리즘 강의문제
 */
import java.util.ArrayList;
import java.util.Scanner;
public class Wrestler {
    static class Person {
        public int height;
        public int weight;
        public Person(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }
    }
    static int n;
    static ArrayList<Person> persons;
    static int max;
    static int result;
    public void greedy() {
        persons.sort((o1, o2) -> o2.height - o1.height);
        max = persons.get(0).weight;
        result = 1;

        for (int i = 1; i < persons.size(); i++) {
            int current = persons.get(i).weight;
            if (current > max) {
                result++;
                max = current;
            }
        }
    }
    public static void main(String[] args) {
        Wrestler m = new Wrestler();
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        persons = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            persons.add(new Person(in.nextInt(), in.nextInt()));
        }

        m.greedy();

        System.out.println(result);
    }
}