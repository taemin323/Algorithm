import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        List<Integer>[] winGraph = new ArrayList[n+1];
        List<Integer>[] loseGraph = new ArrayList[n+1];
        
        for(int i = 1; i <= n; i++) {
            winGraph[i] = new ArrayList<>();
            loseGraph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < results.length; i++) {
            int winner = results[i][0];
            int loser = results[i][1];
            winGraph[winner].add(loser);
            loseGraph[loser].add(winner);
        }
        
        int answer = 0;
        
        for(int i = 1; i <= n; i++) {
            int winCnt = bfs(i, winGraph, n);
            int loseCnt = bfs(i, loseGraph, n);
            
            if(winCnt + loseCnt == n-1) answer++;
        }
        return answer;
    }
    
    int bfs(int idx, List<Integer>[] graph, int n) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        q.add(idx);
        visited[idx] = true;
        
        int cnt = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int next : graph[cur]) {
                if(!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
}