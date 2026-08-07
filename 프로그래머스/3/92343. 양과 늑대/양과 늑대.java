import java.util.*;

class Solution {
    int max = Integer.MIN_VALUE;
    int[] lion = new int[11];
    int[] answer = {-1};
    List<Integer>[] tree;
    
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        tree = new ArrayList[n];
        
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            
            tree[parent].add(child);
        }
        
        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);
        
        dfs(0,0,0,nextNodes,info);
        return max;
    }
    
    void dfs(int idx, int sh, int wo, List<Integer> nextNodes, int[] info) {
        if(info[idx] == 0) sh++;
        else wo++;
        
        if(sh <= wo) return;
        
        max = Math.max(max, sh);
        
        List<Integer> newNodes = new ArrayList<>(nextNodes);
        newNodes.remove(Integer.valueOf(idx));
        
        for(int i : tree[idx]) {
            newNodes.add(i);
        }
        
        for(int i : newNodes) {
            dfs(i, sh, wo, newNodes, info);
        }
    }
}