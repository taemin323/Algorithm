import java.util.*;

class Solution {
    int maxSheep = 0;
    List<Integer>[] tree;
    
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        tree = new ArrayList[n];
        
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        
        //트리 저장
        for(int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            
            tree[parent].add(child);
        }
        
        //방문할 노드 목록
        List<Integer> nodes = new ArrayList<>();
        nodes.add(0);
        
        dfs(0,0,0,nodes,info);
        
        return maxSheep;    
    }
    
    void dfs(int cur, int sheep, int wolf, List<Integer> nodes, int[] info) {
        //현재 노드가 양인지 늑대인지에 따라
        if(info[cur] == 0) sheep++;
        else wolf++;
        
        //가지치기
        if(sheep <= wolf) return;
        
        maxSheep = Math.max(maxSheep, sheep);
        
        //방문할 노드 목록 새로 만들기
        List<Integer> newNodes = new ArrayList<>(nodes);
        //현재 노드는 방문할 노드가 아니니 삭제
        newNodes.remove(Integer.valueOf(cur));
        
        //현재 노드의 자식 노드들 추가
        for(int child : tree[cur]) {
            newNodes.add(child);
        }
        
        //새로운 노드 목록에서 dfs
        for(int i : newNodes) {
            dfs(i, sheep, wolf, newNodes, info);
        }
    }
}