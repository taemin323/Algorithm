import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    List<List<Integer>> orders = new ArrayList<>();
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    
    public int solution(int[][] board, int r, int c) {
        List<Integer> cardTypes = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] != 0 && !cardTypes.contains(board[i][j])) cardTypes.add(board[i][j]);
            }
        }
        
        perm(cardTypes, new boolean[cardTypes.size()], new ArrayList<>());
        
        for(List<Integer> order : orders) {
            int result = calculate(copyBoard(board), order, 0, r, c);
            answer = Math.min(answer, result);
        }
        
        return answer;
    }
    
    void perm(List<Integer> cardTypes, boolean[] visited, List<Integer> cur) {
        if(cur.size() == cardTypes.size()) {
            orders.add(new ArrayList<>(cur));
            return;
        }
        
        for(int i = 0; i < cardTypes.size(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                cur.add(cardTypes.get(i));
                perm(cardTypes, visited, cur);
                cur.remove(cur.size()-1);
                visited[i] = false;
            }
        }
    }
    
    int calculate(int[][] board, List<Integer> order, int depth, int r, int c) {
        if(depth == order.size()) {
            return 0;
        }
        
        int cardNum = order.get(depth);
        
        //현재 지워야할 숫자 카드 2개의 위치
        List<int[]> pos = getPos(board, cardNum);
        int[] p1 = pos.get(0);
        int[] p2 = pos.get(1);
        
        // 현재 위치 -> p1 -> p2
        int move1 = bfs(board, r, c, p1[0], p1[1]) + bfs(board, p1[0], p1[1], p2[0], p2[1]) + 2;
        
        
        // 현재 위치 -> p2 -> p1
        int move2 = bfs(board, r, c, p2[0], p2[1]) + bfs(board, p2[0], p2[1], p1[0], p1[1]) + 2;
        
        // 다음 단계를 위해 보드에서 현재 카드 번호 지움
        board[p1[0]][p1[1]] = 0;
        board[p2[0]][p2[1]] = 0;
        
        // 다른 숫자 카드들도 지우기 위해 재귀 호출
        int res1 = move1 + calculate(board, order, depth+1, p2[0], p2[1]);
        int res2 = move2 + calculate(board, order, depth+1, p1[0], p1[1]);
        
        //백트래킹
        board[p1[0]][p1[1]] = cardNum;
        board[p2[0]][p2[1]] = cardNum;
        
        return Math.min(res1, res2);
    }
    
    int[][] copyBoard(int[][] board) {
        int[][] result = new int[4][4];
        for(int i = 0; i < 4; i++) {
            result[i] = board[i].clone();
        }
        return result;
    }
    
    List<int[]> getPos(int[][] board, int cardNum) {
        List<int[]> result = new ArrayList<>();
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == cardNum) {
                    result.add(new int[] {i, j});
                }
            }
        }
        
        return result;
    }
    
    int bfs(int[][] board, int startR, int startC, int targetR, int targetC) {
        //커서가 이미 카드 위에 있는 경우
        if(startR == targetR && startC == targetC) return 0;
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startR, startC, 0});
        visited[startR][startC] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curDist = cur[2];
            
            if(curR == targetR && curC == targetC) return curDist;
            
            for(int d = 0; d < 4; d++) {
                // 일반 이동
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc, curDist+1});
                }
                
                // ctrl+방향키 이동
                int[] ctrl = getCtrl(board, curR, curC, dr[d], dc[d]);
                int cr = ctrl[0];
                int cc = ctrl[1];
                
                if(!visited[cr][cc]) {
                    visited[cr][cc] = true;
                    q.offer(new int[] {cr, cc, curDist + 1});
                }
            }
        }
        return 0;
    }
    
    int[] getCtrl(int[][] board, int r, int c, int di, int dj) {
        int nr = r;
        int nc = c;
        
        while(true) {
            int tr = nr + di;
            int tc = nc + dj;
            
            //격자 범위를 벗어나면 현재 위치가 최종 목적지
            if(tr < 0 || tr >= 4 || tc < 0 || tc >= 4) break;
            
            nr = tr;
            nc = tc;
            
            // 이동 중 카드를 만나면 그 자리에서 멈춤
            if(board[nr][nc] != 0) break;
        }
        return new int[]{nr, nc};
    }
}