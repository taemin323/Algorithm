import java.util.*;

class Solution {
    int[][] tree;
    boolean[] visited;
    int N;
    int answer = Integer.MAX_VALUE;
     
    public int solution(int n, int[][] wires) {
        N = n;
        tree = new int[n+1][n+1];
        visited = new boolean[n+1];
        
        
        //tree 구성
        for(int i = 0; i < wires.length; i++) {
            int from = wires[i][0];
            int to = wires[i][1];
            
            tree[from][to] = 1;
            tree[to][from] = 1;
        }
        
        dfs(1);
        return answer;
    }
    
    public int dfs(int cur) {
        visited[cur] = true;
        int child = 1;
        
        for(int i = 1; i <= N; i++) {
            if(!visited[i] && tree[cur][i] == 1) {
                visited[i] = true;
                child += dfs(i);
            }
        }
        
        answer = Math.min(answer, Math.abs(child - (N-child)));
        return child;
    }
}