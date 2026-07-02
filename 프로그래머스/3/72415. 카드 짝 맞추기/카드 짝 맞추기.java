import java.util.*;

class Solution {
    //어떤 카드부터 제거할지 정하는지에 따라 키 조작 횟수 달라지니 모든 경우의 수를 구해서 비교해야함
    List<List<Integer>> orders = new ArrayList<>();
    int answer = Integer.MAX_VALUE;
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    
    public int solution(int[][] board, int r, int c) {
        List<Integer> cardTypes = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[i][j] != 0 && !cardTypes.contains(board[i][j])) cardTypes.add(board[i][j]);
            }
        }

        //그러니 일단 나올 수 있는 카드의 순서를 모두 저장
        perm(cardTypes, new ArrayList<>(), new boolean[cardTypes.size()]);
        
        for(List<Integer> order : orders) {
            int result = calculate(copyBoard(board), order, r, c, 0);
            answer = Math.min(result, answer);
        }
        
        return answer;
    }
    
    //원본 board 훼손 안시키려고
    int[][] copyBoard(int[][] board) {
        int[][] result = new int[board.length][board[0].length];
        
        for(int i = 0; i < board.length; i++) {
            result[i] = board[i].clone(); 
        }
        return result;
    }
    
    //카드의 순서 정해주는 메서드
    void perm(List<Integer> cardTypes, List<Integer> cur, boolean[] visited) {
        if(cur.size() == cardTypes.size()) {
            orders.add(new ArrayList<>(cur));
            return;
        }
        
        for(int i = 0; i < cardTypes.size(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                cur.add(cardTypes.get(i));
                perm(cardTypes, cur, visited);
                cur.remove(cur.size() - 1);
                visited[i] = false;
            }
        }
    }
    
    // 각 순서의 최종 키 조작 횟수 계산 메서드
    int calculate(int[][] board, List<Integer> order, int r, int c, int depth) {
        if(depth == order.size()) {
            return 0;
        }
        
        //현재 카드의 위치를 알아야됨.
        List<int[]> pos = getPos(order.get(depth), board);
        int[] p1 = pos.get(0);
        int[] p2 = pos.get(1);
        
        //현재 위치 -> p1 -> p2일 때 키 조작 횟수 / +2는 엔터 누른것.
        int cnt1 = bfs(board, r, c, p1[0], p1[1]) + bfs(board, p1[0], p1[1], p2[0], p2[1]) + 2;
        
        //현재 위치 -> p2 -> p1일 때 키 조작 횟수
        int cnt2 = bfs(board, r, c, p2[0], p2[1]) + bfs(board, p2[0], p2[1], p1[0], p1[1]) + 2;
        
        //다음 단계를 위해 현재 카드 지움
        board[p1[0]][p1[1]] = 0;
        board[p2[0]][p2[1]] = 0;
        
        //다른 숫자 카드들도 뒤집기 위해 재귀
        int result1 = cnt1 + calculate(board, order, p2[0], p2[1], depth+1);
        int result2 = cnt2 + calculate(board, order, p1[0], p1[1], depth+1);
        
        //백트래킹
        board[p1[0]][p1[1]] = order.get(depth);
        board[p2[0]][p2[1]] = order.get(depth);
        
        return Math.min(result1, result2);
    }
    
    //카드 위치 찾는 메서드
    List<int[]> getPos(int cardNum, int[][] board) {
        List<int[]> result = new ArrayList<>();
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == cardNum) result.add(new int[] {i, j});
            }
        }
        return result;
    }
    
    //bfs
    int bfs(int[][] board, int sr , int sc, int er, int ec) {
        //커서가 이미 카드 위에 있는 경우
        if(sr == er && sc == ec) return 0;
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {sr, sc, 0});
        visited[sr][sc] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curCnt = cur[2];
            
            if(curR == er && curC == ec) return curCnt;
            
            for(int d = 0; d < 4; d++) {
                //일반 이동
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) continue;
                
                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc, curCnt+1});
                }
                
                //ctrl 이동
                int[] ctrl = getCtrl(board, curR, curC, dr[d], dc[d]);
                int cr = ctrl[0];
                int cc = ctrl[1];
                
                if(!visited[cr][cc]) {
                    visited[cr][cc] = true;
                    q.add(new int[] {cr, cc, curCnt+1});
                }
            }
        }
        return 0;
    }
    
    int[] getCtrl(int[][] board, int curR, int curC, int di, int dj) {
        int nr = curR;
        int nc = curC;
        
        while(true) {
            int tr = nr + di;
            int tc = nc + dj;
            
            //board를 벗어나면 현재 위치가 결국 최종 목적지
            if(tr < 0 || tr >= board.length || tc < 0 || tc >= board[0].length) break;
            
            nr = tr;
            nc = tc;
            
            //다른 카드를 마주쳤다면 여기가 최종 목적지
            if(board[tr][tc] != 0) break;
        }
        return new int[] {nr, nc};
    }
    
}