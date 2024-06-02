import java.util.ArrayList;
import java.util.List;

public class 프로그래머스여행경로 {

    List<List<String>> paths;
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        paths = new ArrayList<>();
        visited = new boolean[tickets.length];

        // search path
        List<String> initPath = new ArrayList<>();
        initPath.add("ICN");
        dfs(0, initPath, tickets);

        //testPrintDimension(paths);

        // find alpabet first
        List<String> result = compare();

        // convert primitive type
        String[] answer = new String[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    void dfs(int level, List<String> path, String[][] tickets) {
        if (level == tickets.length) {
            paths.add(new ArrayList<>(path));
        } else {
            for (int i = 0; i < tickets.length; i++) {
                if (!visited[i] && tickets[i][0].equals(path.get(path.size()-1))) {
                    visited[i] = true;
                    path.add(tickets[i][1]);
                    dfs(level + 1, path, tickets);
                    visited[i] = false;
                    path.remove(path.size()-1);
                }
            }
        }
    }

    List<String> compare() {
        List<String> tmp = paths.get(0);
        for (List<String> path : paths) {
            for (int i = 0; i < path.size(); i++) {
                String str_cur = tmp.get(i);
                String str_new = path.get(i);
                if (str_new.compareTo(str_cur) > 0) {
                    break;
                }
                if (str_new.compareTo(str_cur) < 0) {
                    tmp = path;
                    break;
                }
            }
        }
        return tmp;
    }

    void testPrintDimension(List<List<String>> lists) {
        StringBuilder sb = new StringBuilder();
        for (List<String> list : lists) {
            sb.append("size: " + list.size() + "\n");
            for (String each : list) {
                sb.append(each + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    void testPrint(boolean[] arr) {
        StringBuilder sb = new StringBuilder();
        for (boolean each : arr) {
            sb.append(each + " ");
        }
        sb.append("\n");
        System.out.println(sb);
    }

    void testPrint(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String each : list) {
            sb.append(each + " ");
        }
        sb.append("\n");
        System.out.println(sb);
    }
}