import java.util.*;

class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    boolean[][] visitedR;
    boolean[][] visitedB;
    int n;
    int m;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        visitedR = new boolean[n][m];
        visitedB = new boolean[n][m];
        
        int rr = 0;
        int rc = 0;
        int br = 0;
        int bc = 0;
        
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[0].length; j++) {
                if(maze[i][j] == 1) {
                    rr = i;
                    rc = j;
                } else if(maze[i][j] == 2) {
                    br = i;
                    bc = j;
                }
            }
        }
        
        visitedR[rr][rc] = true;
        visitedB[br][bc] = true;
        
        dfs(0,rr,rc,br,bc,maze);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    void dfs(int cnt, int rr, int rc, int br, int bc, int[][] maze) {
        if(cnt > answer) return;
        
        //각각 도착 칸에 도착했다면
        boolean redClear = maze[rr][rc] == 3;
        boolean blueClear = maze[br][bc] == 4;
        
        if(redClear && blueClear) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        
        for(int rd = 0; rd < 4; rd++) {
            //빨간 수레의 4방 탐색
            int nrr = (redClear) ? rr : rr + dr[rd];
            int nrc = (redClear) ? rc : rc + dc[rd];
                
            //빨간 수레 범위/벽/방문 체크
            if(nrr < 0 || nrr >= n || nrc < 0 || nrc >= m || maze[nrr][nrc] == 5 || (!redClear && visitedR[nrr][nrc])) continue;
            
            for(int bd = 0; bd < 4; bd++) {
                
                
                //파란 수레의 4방 탐색
                int nbr = blueClear ? br : br + dr[bd];
                int nbc = blueClear ? bc : bc + dc[bd];
                
                //파란 수레 범위/벽/방문 체크
                if(nbr < 0 || nbr >= n || nbc < 0 || nbc >= m || maze[nbr][nbc] == 5 || (!blueClear && visitedB[nbr][nbc])) continue;
                
                // 겹치는 경우 
                if(nrr == nbr && nrc == nbc) continue;
                
                // 서로 교차하는 경우
                if(nrr == br && nrc == bc && nbr == rr && nbc == rc) continue;
                
                visitedR[nrr][nrc] = true;
                visitedB[nbr][nbc] = true;
                dfs(cnt+1, nrr, nrc, nbr, nbc, maze);
                if(!redClear) visitedR[nrr][nrc] = false;
                if(!blueClear) visitedB[nbr][nbc] = false;
                
            }
        }
    }
}