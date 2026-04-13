import java.util.*;

class Solution {
    List<List<Integer>> list;
    boolean[] visited;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        
        visited = new boolean[n+1];
        
        list = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i = 0; i < wires.length; i++) {
            int from = wires[i][0];
            int to = wires[i][1];
            
            list.get(from).add(to);
            list.get(to).add(from);
        }
        
        dfs(1, n);
        return answer;
    }
    
    int dfs(int idx, int n) {
        visited[idx] = true;
        
        int child = 1;// 자기 자신 포함
        
        for(Integer i : list.get(idx)) {
            if(!visited[i] && list.get(idx).contains(i)) {
                visited[i] = true;
                
                child += dfs(i, n);
            }
        }
        
        answer = Math.min(answer, Math.abs(child - (n - child)));
        return child;
    }
}