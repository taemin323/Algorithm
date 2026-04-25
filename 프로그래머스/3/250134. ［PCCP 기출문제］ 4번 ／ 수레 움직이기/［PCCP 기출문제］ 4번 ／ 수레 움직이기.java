import java.util.*;

class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    
    boolean[][] visitedR;
    boolean[][] visitedB;
    
    int n;
    int m;
    int minCnt = Integer.MAX_VALUE;

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        
        visitedR = new boolean[n][m];
        visitedB = new boolean[n][m];
        
        int rr = 0;
        int rc = 0;
        int br = 0;
        int bc = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
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
        dfs(rr, rc, br, bc, 0, maze);
        
        return minCnt == Integer.MAX_VALUE ? 0 : minCnt;
    }
    
    void dfs(int rr, int rc, int br, int bc, int cnt, int[][] map) {
        boolean redClear = (map[rr][rc] == 3);
        boolean blueClear = (map[br][bc] ==4);
        
        if(redClear && blueClear) {
            minCnt = Math.min(minCnt, cnt);
            return;
        }
        
        for(int rd = 0; rd < 4; rd++) {
            for(int bd = 0; bd < 4; bd++) {
                // 빨간 수레의 다음 위치
                int nrr = redClear ? rr : rr + dr[rd];
                int nrc = redClear ? rc : rc + dc[rd];
            
                // 파란 수레의 다음 위치
                int nbr = blueClear ? br : br + dr[bd];
                int nbc = blueClear ? bc : bc + dc[bd];
                
                //유효성 체크
                if(isValid(nrr, nrc, nbr, nbc, rr, rc, br, bc, redClear, blueClear, map)) {
                    //이동 확정 전 visited 처리
                    boolean rVisit = false;
                    boolean bVisit = false;
                    
                    if(!redClear) {
                        visitedR[nrr][nrc] = true;
                        rVisit = true;
                    }
                    
                    if(!blueClear) {
                        visitedB[nbr][nbc] = true;
                        bVisit = true;
                    }
                    
                    dfs(nrr, nrc, nbr, nbc, cnt+1, map);
                    
                    // 백트래킹
                    if(rVisit) visitedR[nrr][nrc] = false;
                    if(bVisit) visitedB[nbr][nbc] = false;
                }
            }
        }
        
        // 두 수레의 다음 위치가 유효한지
        
        // 방문 체크 후 재귀 호출
    }
    
    boolean isValid(int nrr, int nrc, int nbr, int nbc, int rr, int rc, int br, int bc, boolean redClear, boolean blueClear, int[][] map) {
        
        // 범위 체크
        if(nrr < 0 || nrr >= n || nrc < 0 || nrc >= m || nbr < 0 || nbr >= n || nbc < 0 || nbc >= m) return false;
        
        // 방문 체크(이미 도착 지점에 있는 수레는 움직이지 않는것 고려)
        if(!redClear && visitedR[nrr][nrc]) return false;
        if(!blueClear && visitedB[nbr][nbc]) return false;
        
        // 벽 체크
        if(map[nrr][nrc] == 5 || map[nbr][nbc] == 5) return false;
        
        // 두 수레가 같은 칸
        if(nrr == nbr && nrc == nbc) return false;
        
        // 두 수레가 교차하는 경우
        if(nrr == br && nrc == bc && nbr == rr && nbc == rc) return false;
        
        return true;
    }
}