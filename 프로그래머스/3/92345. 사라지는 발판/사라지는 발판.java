import java.util.*;

class Solution {
    private int[] dr = {0,0,-1,1};
    private int[] dc = {-1,1,0,0};
    private int[][] newBoard;
    private int n;
    private int m;
    
    class Result {
        boolean win;
        int cnt;
        
        Result(boolean win, int cnt) {
            this.win = win;
            this.cnt = cnt;
        }
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        newBoard = board;
        n = board.length;
        m = board[0].length;
    
        return dfs(board, aloc[0], aloc[1], bloc[0], bloc[1]).cnt;    
    }     
    
    private Result dfs(int[][] board, int r1, int c1, int r2, int c2) {
        // 현재 플레이어가 패배할 조건(이미 발판이 없거나 갈 곳이 없음)
        if(board[r1][c1] == 0) return new Result(false, 0);
        
        boolean canWin = false;
        int minCnt = 100;// 승리 - 최소 이동
        int maxCnt = 0;// 패배 - 최대 이동
        
        boolean canMove = false;
        board[r1][c1] = 0;//현재 발판 사라짐
        
        for(int d = 0; d < 4; d++) {
            int nr = r1 + dr[d];
            int nc = c1 + dc[d];
            
            if(nr < 0 || nr >= n || nc < 0 || nc >= m || board[nr][nc] == 0) continue;
            
            canMove = true;
            
            //상대방 차례로 넘기기
            Result res = dfs(board, r2, c2, nr, nc);
            
            //상대방이 지는 게임이라면
            if(!res.win) {
                canWin = true;
                minCnt = Math.min(minCnt, res.cnt + 1);
            } else {// 상대가 이기는 게임이라면 최대한 멀리
                if(!canWin) {// 이기는 게임이 아닐때만 최대값 경신
                    maxCnt = Math.max(maxCnt, res.cnt + 1);
                }
            }
        }
        
        board[r1][c1] = 1;//원복
        
        // 갈 곳이 없다면 패배
        if(!canMove) {
            return new Result(false, 0);
        }
        return new Result(canWin, canWin ? minCnt : maxCnt);
    }
}