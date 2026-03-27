import java.util.*;

class Solution {
    private int[][] map;
    private boolean[][] visited;
    private int[] dr = {-1,1,0,0};
    private int[] dc = {0,0,-1,1};
    
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[102][102];
        visited = new boolean[102][102];
        
        for(int i = 0; i < rectangle.length; i++) {
            for(int j = 0; j < 4; j++) {
                int x1 = rectangle[i][0]*2;
                int y1 = rectangle[i][1]*2;
                int x2 = rectangle[i][2]*2;
                int y2 = rectangle[i][3]*2;
                
                makeMap(x1, y1, x2, y2);
            }
        }
        
        return bfs(characterX*2, characterY*2, itemX*2, itemY*2, 0);
    }
    
    private void makeMap(int x1, int y1, int x2, int y2) {
        for(int i = x1; i <= x2; i++) {
            for(int j = y1; j <= y2; j++) {
                if(map[j][i] == -1) continue;
                
                if(i == x1 || i == x2 || j == y1 || j == y2) {
                    map[j][i] = 1;
                } else {
                    map[j][i] = -1;
                }
            }
        }
    }
    
    private int bfs(int startC, int startR, int endC, int endR, int dist) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startR, startC, dist});
        visited[startR][startC] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
                
            int curR = cur[0];
            int curC = cur[1];
            int curDist = cur[2];
                
            if(curR == endR && curC == endC) return curDist/2;
                
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                    
                if(nr < 0 || nr >= 102 || nc < 0 || nc >= 102) continue;
                    
                if(!visited[nr][nc] && map[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, curDist+1});
                }
            }
        }
        return dist/2;
    }
}