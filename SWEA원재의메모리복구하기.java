import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA원재의메모리복구하기 {

    static char[] init;
    static char[] original;
    static int firstIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            init = br.readLine().toCharArray();
            original = new char[init.length];
            for (int i = 0; i < init.length; i++) {
                original[i] = '0';
            }

            // find first 1
            for (int i = 0; i < init.length; i++) {
                if (init[i] == '1') {
                    firstIdx = i;
                    break;
                }
            }
            int result = repairBits();

            output.append("#" + test_case + " " + result + "\n");
        }
        System.out.println(output);
    }

    public static int repairBits() {
        int cnt = 0;
        for (int i = firstIdx; i < original.length; i++) {
            if (init[i] != original[i]) {
                changeBit(original, i);
                cnt++;
                //System.out.println("count!");
            }
            //testPrint(original);

            if (isEquals(init, original)) {
                break;
            }
        }
        return cnt;
    }

    public static void changeBit(char[] bits, int idx) {
        char start;
        if (bits[idx] == '1') start = '0';
        else start = '1';

        for (int i = idx; i < bits.length; i++) {
            bits[i] = start;
        }
    }

    public static boolean isEquals(char[] a, char[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void testPrint(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i] + " ");
        }
        System.out.println(sb);
    }
}
