import java.util.*;

class Solution {
    int[][] map;
    boolean[][] visited;
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        map = new int[102][102];
        visited = new boolean[102][102];
        
        for(int i = 0; i < rectangle.length; i++) {
            int leftC = rectangle[i][0] * 2;
            int leftR = rectangle[i][1] * 2;
            int rightC = rectangle[i][2] * 2;
            int rightR = rectangle[i][3] * 2;
            
            make(leftC, leftR, rightC, rightR);
        }
        
        return bfs(characterX*2, characterY*2, itemX*2, itemY*2);
    }
    
    void make(int lc, int lr, int rc, int rr) {
        for(int i = lr; i <= rr; i++) {
            for(int j = lc; j <= rc; j++) {
                if(i > lr && i < rr && j > lc && j < rc) {
                    map[i][j] = -1;
                } else if(map[i][j] != -1) {
                    map[i][j] = 1;
                }
            }
        }
    }
    
    int bfs(int sc, int sr, int ec, int er) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {sr, sc, 0});
        visited[sr][sc] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curDist = cur[2];
            
            if(curR == er && curC == ec) {
                return curDist/2;
            }
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length) continue;
                
                if(!visited[nr][nc] && map[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc, curDist+1});
                }
            }
        }
        return 0;
    }
}