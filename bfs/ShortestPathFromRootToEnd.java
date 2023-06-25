package bfs;

import java.util.LinkedList;
import java.util.Queue;
/*
[[ Tree 말단 노드까지의 가장 짧은 경로 ]]

 */
public class ShortestPathFromRootToEnd {
    Node node;

    public int BFS(Node n) {
        Queue<Node> q = new LinkedList<>();
        int level = 0;

        q.offer(n);
        while(!q.isEmpty()) {
            int len = q.size();
            for(int i = 0; i < len; i++) {
                Node poll = q.poll();
                if(poll.lt == null && poll.rt == null) return level;
                if(poll.lt != null) q.offer(poll.lt);
                if(poll.rt != null) q.offer(poll.rt);
            }
            level++;
        }
        return -1;

    }

    public void init() {
        node = new Node(1);
        node.lt = new Node(2);
        node.rt = new Node(3);
        node.lt.lt = new Node(4);
        node.lt.rt = new Node(5);
    }
    public static void main(String[] args) {
        ShortestPathFromRootToEnd m = new ShortestPathFromRootToEnd();

        m.init();
        System.out.println(m.BFS(m.node));
    }
}