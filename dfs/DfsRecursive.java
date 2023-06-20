package dfs;
/*
[[ DFS (깊이 우선 탐색) - 재귀 ]]

 */
import java.util.Scanner;

class Node {
    int value;
    Node lt, rt;
    public Node(int value) {
        this.value = value;
        lt = null;
        rt = null;
    }
}

public class DfsRecursive {
    public Node root;
    public void DFS(Node n) {
        if(n == null) return;
        else {
            DFS(n.lt);
            DFS(n.rt);
            System.out.print(n.value + " ");
        }
    }

    public static void main(String[] args) {
        DfsRecursive m = new DfsRecursive();
        Scanner in = new Scanner(System.in);

        m.root = new Node(1);
        m.root.lt = new Node(2);
        m.root.rt = new Node(3);

        m.root.lt.lt = new Node(4);
        m.root.lt.rt = new Node(5);

        m.root.rt.lt = new Node(6);
        m.root.rt.rt = new Node(7);

        m.DFS(m.root);
    }
}
