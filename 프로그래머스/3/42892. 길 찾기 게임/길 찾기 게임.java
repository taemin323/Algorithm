import java.util.*;

class Solution {
    class Node {
        int x;
        int y;
        int idx;
        Node left;
        Node right;
        
        public Node(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }
    
    Node root;
    List<Integer> frontList = new ArrayList<>();
    List<Integer> backList = new ArrayList<>();
    
    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        Node[] nodes = new Node[n];
        
        // 노드 생성
        for(int i = 0; i < n; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
        }
        
        // 정렬
        Arrays.sort(nodes, (a,b) -> {
            if(a.y == b.y) return a.x - b.x;
            return b.y - a.y;
        });
        
        // 트리 구성
        root = nodes[0];
        for(int i = 1; i < n; i++) {
            insert(root, nodes[i]);
        }
        
        // 순회
        front(root);
        back(root);
        
        int[][] answer = new int[2][n];
        
        for(int i = 0; i < n; i++) {
            answer[0][i] = frontList.get(i);
            answer[1][i] = backList.get(i);
        }
        
        return answer;
    }
    
    private void insert(Node parent, Node child) {
        if(child.x < parent.x) {
            if(parent.left == null) parent.left = child;
            else insert(parent.left, child);
        } else {
            if(parent.right == null) parent.right = child;
            else insert(parent.right, child);
        }
    }
    
    private void front(Node node) {
        if(node == null) return;
        frontList.add(node.idx);
        front(node.left);
        front(node.right);
    }
    
    private void back(Node node) {
        if(node == null) return;
        back(node.left);
        back(node.right);
        backList.add(node.idx);
    }
}