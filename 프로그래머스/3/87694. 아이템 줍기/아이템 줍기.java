import java.util.*;

class Solution {
    int[][] map;
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        boolean[][] visited = new boolean[102][102];
        
        map = new int[102][102];
        for(int i = 0; i < rectangle.length; i++) {
            int lc = rectangle[i][0]*2;
            int lr = rectangle[i][1]*2;
            int rc = rectangle[i][2]*2;
            int rr = rectangle[i][3]*2;
            
            makeRoute(lc, lr, rc , rr);
        }
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {characterY*2, characterX*2, 0});
        visited[characterY*2][characterX*2] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curDist = cur[2];
            
            if(curR == itemY*2 && curC == itemX*2) {
                answer = curDist;
                return answer/2;
            }
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= 102 || nc < 0 || nc >= 102) continue;
                
                if(!visited[nr][nc] && map[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc, curDist+1});
                }
            }
        }
        
        return 0;
    }
    
    void makeRoute(int lc, int lr, int rc, int rr) {
        for(int i = lr+1; i < rr; i++) {
            for(int j = lc+1; j < rc; j++) {
                map[i][j] = 2;
            }
        }
        
        for(int i = lr; i <= rr; i++) {
            if(map[i][lc] != 2) map[i][lc] = 1;
            if(map[i][rc] != 2) map[i][rc] = 1;
        }
        
        for(int i = lc; i <= rc; i++) {
            if(map[lr][i] != 2) map[lr][i] = 1;
            if(map[rr][i] != 2) map[rr][i] = 1;
        }
    }
}