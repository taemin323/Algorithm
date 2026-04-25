import java.util.*;

class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    int n;
    int m;
    boolean[][] visited;
    
    int spotR;
    int spotC;
    int answer = 0;
    boolean flag;
    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        
        visited = new boolean[n][m];
        
        int startR = 0;
        int startC = 0;
        int endR = 0;
        int endC = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
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
        if(!flag) return -1;
        
        visited = new boolean[n][m];
        flag = false;
        bfs(spotR, spotC, endR, endC, maps);
        if(!flag) return -1;
        return answer;
    }
    
    void bfs(int startR, int startC, int spotR, int spotC, String[] maps) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startR, startC, 0});
        visited[startR][startC] = true;
        
        boolean flag2 = false;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curDist = cur[2];
            
            if(curR == spotR && curC == spotC) {
                answer += curDist;
                flag2 = true;
                break;
            }
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                
                
                if(!visited[nr][nc] && maps[nr].charAt(nc) != 'X') {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc, curDist+1});
                }
            }
            
        }
        flag = flag2;
        
    }
}