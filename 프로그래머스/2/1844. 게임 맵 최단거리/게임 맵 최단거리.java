import java.util.*;

class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    int n;
    int m;
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        
        return bfs(0,0,maps);
    }
    
    int bfs(int r, int c, int[][] maps) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c , 1});
        maps[r][c] = 0;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curDist = cur[2];
            
            if(curR == n-1 && curC == m-1) return curDist;
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                
                if(maps[nr][nc] == 1) {
                    maps[nr][nc] = 0;
                    q.offer(new int[] {nr, nc, curDist+1});
                }
            }
        }
        return -1;
    }
}