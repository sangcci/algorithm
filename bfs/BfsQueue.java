package bfs;
/*
[[ BFS 구현 - Queue ]]

 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int value;
    Node lt;
    Node rt;
    public Node(int value) {
        this.value = value;
        lt = null;
        rt = null;
    }
}
public class BfsQueue {
    public Node node;
    public void BFS(int end) {
        Queue<Node> q = new LinkedList<>();
        int level = 0;

        q.offer(node);
        while(!q.isEmpty()) {
            // Level별 출력
            System.out.print("level : " + level + " = ");
            for(int i = 0; i < q.size(); i++) {
                Node n = q.poll();
                System.out.print(n.value + " ");

                if(n.lt != null) q.offer(n.lt);
                if(n.rt != null) q.offer(n.rt);
            }
            // Level 검사
            if(level > end) {
                return;
            }
            level++;
            System.out.println();
        }
    }

    public void init(BfsQueue m) {
        m.node = new Node(1);

        m.node.lt = new Node(2);
        m.node.rt = new Node(3);

        m.node.lt.lt = new Node(4);
        m.node.lt.rt = new Node(5);

        m.node.rt.lt = new Node(6);
        m.node.rt.rt = new Node(7);
    }

    public static void main(String[] args) {
        BfsQueue m = new BfsQueue();
        Scanner in = new Scanner(System.in);

        m.init(m);
        m.BFS(2);
    }
}
