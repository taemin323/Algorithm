import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        return bfs(1, n, graph);
    }
    
    int bfs(int start, int n, List<Integer>[] graph) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        
        q.add(start);
        visited[start] = true;
        
        int answer = 0;
        
        while(!q.isEmpty()) {
            int qSize = q.size();
            answer = qSize;
            
            for(int i = 0; i < qSize; i++) {
                int cur = q.poll();
                
                for(int next : graph[cur]) {
                    if(!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
        }
        
        return answer;
    }
}