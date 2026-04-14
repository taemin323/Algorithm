import java.util.*;

class Solution {
    List<HashSet<Integer>> graph = new ArrayList<>();
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        for(int i = 0; i < n; i ++) {
            graph.add(new HashSet<>());
        }
        
        visited = new boolean[n+1];
        
        for(int i = 0; i < n; i++) {
            int curIdx = i;
            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                
                if(computers[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
            
        }
        
        int answer = 0;
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i);
                answer++;
            }    
        }
            
        return answer;
    }
    
    void dfs(int idx) {
        for(int i : graph.get(idx)) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i);
            }
        }
    }
}