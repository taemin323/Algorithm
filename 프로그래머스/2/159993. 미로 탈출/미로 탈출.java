import java.util.*;

class Solution {
    boolean[][] visited;
    boolean flag;
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    int answer = 0;
    
    public int solution(String[] maps) {
        
        int sr = 0;
        int sc = 0;
        int er = 0;
        int ec = 0;
        int lr = 0;
        int lc = 0;
        
        visited = new boolean[maps.length][maps[0].length()];

        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[0].length(); j++) {
                if(maps[i].charAt(j) == 'S') {
                    sr = i;
                    sc = j;
                } else if(maps[i].charAt(j) == 'L') {
                    lr = i;
                    lc = j;
                } else if(maps[i].charAt(j) == 'E') {
                    er = i;
                    ec = j;
                }
            }
        }
        
        bfs(sr, sc, lr, lc, maps);
        if(!flag) return -1;
        visited = new boolean[maps.length][maps[0].length()];
        flag = false;
        
        bfs(lr, lc, er, ec, maps);
        if(!flag) return -1;
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
                answer += curDist;
            }
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= maps.length || nc < 0 || nc >= maps[0].length()) continue;
                
                if(!visited[nr][nc] && maps[nr].charAt(nc) != 'X') {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc, curDist+1});
                }
            }
        }
    } 
}