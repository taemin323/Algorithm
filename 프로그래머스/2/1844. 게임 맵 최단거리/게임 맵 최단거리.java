import java.util.*;

class Solution {
    boolean[][] visited;
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    int n;
    int m;
    int answer = 0;
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        
        bfs(0,0,maps);
        
        if(answer == 0) return -1;
        
        return answer;
    }
    
    void bfs(int r, int c, int[][] maps) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c , 1});
        visited[r][c] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curDist = cur[2];
            
            if(curR == n-1 && curC == m-1) {
                answer = curDist;
            }
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                
                if(!visited[nr][nc] && maps[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc, curDist+1});
                }
            }
        }
        
    }
}