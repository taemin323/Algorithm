import java.util.*;

class Solution {
    boolean[][] visited;
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    int answer = -1;
    int cnt = 0;
    boolean flag;
    
    public int solution(String[] maps) {
        
        int startR = 0;
        int startC = 0;
        int spotR = 0;
        int spotC = 0;
        int endR = 0;
        int endC = 0;
        
        visited = new boolean[maps.length][maps[0].length()];
        
        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[0].length(); j++) {
                if(maps[i].charAt(j) == 'S') {
                    startR = i;
                    startC = j;
                } else if(maps[i].charAt(j) == 'L') {
                    spotR = i;
                    spotC = j;
                } else if(maps[i].charAt(j) == 'E') {
                    endR = i;
                    endC = j;
                }
            }
        }
        
        bfs(startR, startC, spotR, spotC, maps);
        visited = new boolean[maps.length][maps[0].length()];
        
        if(flag) {
            flag = false;
            bfs(spotR, spotC, endR, endC, maps);
        }
        
        if(flag) answer = cnt;
        
        return answer;
    }
    
    void bfs(int sr, int sc, int er, int ec, String[] maps) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {sr, sc, 0});
        visited[sr][sc] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curDist = cur[2];
            
            if(curR == er && curC == ec) {
                flag = true;
                cnt += curDist;
                return;
            }
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= maps.length || nc < 0 || nc >= maps[0].length()) continue;
                
                if(visited[nr][nc]) continue;
                
                if(maps[nr].charAt(nc) != 'X') {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, curDist+1});
                }
            }
        }
    }
}