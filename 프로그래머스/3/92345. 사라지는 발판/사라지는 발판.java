import java.util.*;
/**
* 항상 이기는 플레이어 -> 최대한 빠르게 게임 끝내기
* 항상 지는 플레이어 -> 최대한 늦게 게임 끝내기
* 현재 위치에서 4방향 탐색 진행
* 상대방에게 턴 넘겨주기 -> 재귀적으로 진행.
* 이 재귀 과정에서 도출된 결과로 누가 이기는 플레이어인지 정해짐
* 내가 이기는 플레이어라면 minCnt를 최신화. 지는 플레이어라면 maxCnt를 최신화.
* 이길 수 없는 기저 조건 
* 1. 이미 발판이 없거나 갈 곳이 없음
* 2. 이동할 수 없다면 패배
*/

class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    int n, m;
    
    class Result {
        boolean win;
        int cnt;
        
        public Result(boolean win, int cnt) {
            this.win = win;
            this.cnt = cnt;
        }
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        
        return dfs(board, aloc[0], aloc[1], bloc[0], bloc[1]).cnt;
    }
    
    Result dfs(int[][] board, int ar, int ac, int br, int bc) {
        if(board[ar][ac] == 0) return new Result(false, 0);
        
        boolean canWin = false;
        int minCnt = Integer.MAX_VALUE;//내가 이기는 플레이어라면 최소 이동
        int maxCnt = Integer.MIN_VALUE;//내가 지는 플레이어라면 최대 이동
        
        boolean canMove = false;
        board[ar][ac] = 0;//발판 사라짐. 이동했으니까.
        
        //일단 현재 좌표 기준 4방향 탐색하기
        for(int d = 0; d < 4; d++) {
            int nr = ar + dr[d];
            int nc = ac + dc[d];
            
            if(nr < 0 || nr >= n || nc < 0 || nc >= m || board[nr][nc] == 0) continue;
            
            //위 조건이 아니라면 움직일 수 있다는 것.
            canMove = true;
            
            //이제 상대방에게 턴 넘겨주기
            Result res = dfs(board, br, bc, nr, nc);
            
            //내가 이기는 플레이어라면
            if(!res.win) {
                canWin = true;
                minCnt = Math.min(minCnt, res.cnt + 1);//내가 이동한 거리 = 1
            } else {
                maxCnt = Math.max(maxCnt, res.cnt + 1);
            }
        }
        
        //원복시켜주기
        board[ar][ac] = 1;
        
        if(!canMove) {
            return new Result(false, 0);
        } 
        
        return new Result(canWin, canWin ? minCnt : maxCnt);
    }
}