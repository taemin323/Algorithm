import java.util.*;

class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    
    public int solution(int[][] maps) {
        int answer = -1;
        
        int n = maps.length;
        int m = maps[0].length;
        
        boolean[][] visited = new boolean[n][m];
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0,0,0});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curDist = cur[2];
            
            if(curR == n-1 && curC == m-1) {
                answer = curDist+1;
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
        return answer;
    }
}