/**
* 1 <= n <= 200
* computer[i][i]는 항상 1
*/
import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, visited, computers);
                answer++;
            }
        }        
        return answer;
    }
    
    private static void bfs(int idx, boolean[] visited, int[][] computers) {
        Queue<Integer> q = new LinkedList<>();
        q.add(idx);
        visited[idx] = true;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < computers.length; i++) {
                if(!visited[i] && computers[cur][i] == 1) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }
    
}