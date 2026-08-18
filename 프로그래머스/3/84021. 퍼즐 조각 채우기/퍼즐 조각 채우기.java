import java.util.*;
/**
* 30분
*/

class Solution {
    int n;
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    
    public int solution(int[][] game_board, int[][] table) {
        List<List<int[]>> spaces = new ArrayList<>();
        List<List<int[]>> puzzles = new ArrayList<>();
        
        n = game_board.length;
        
        boolean[][] visited = new boolean[n][n];
        
        //spaces 채우기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(game_board[i][j] == 0 && !visited[i][j]) {
                    spaces.add(bfs(i, j, game_board, visited, 0));
                }
            }
        }
        
        //puzzles 채우기
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(table[i][j] == 1 && !visited[i][j]) {
                    puzzles.add(bfs(i, j, table, visited, 1));
                }
            }
        }
        return match(spaces, puzzles);
    }
    
    List<int[]> bfs(int r, int c, int[][] board, boolean[][] visited, int target) {
        List<int[]> result = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c});
        visited[r][c] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            
            result.add(new int[] {curR, curC});
            
            for(int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                
                if(!visited[nr][nc] && board[nr][nc] == target) {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        
        return calculate(result);
    }
    
    List<int[]> calculate(List<int[]> list) {
        List<int[]> result = new ArrayList<>();
        
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for(int[] i : list) {
            minR = Math.min(minR, i[0]);
            minC = Math.min(minC, i[1]);
        }
        
        for(int[] i : list) {
            int r = i[0] - minR;
            int c = i[1] - minC;
            
            result.add(new int[] {r, c});
        }
        
        Collections.sort(result, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        return result;
    }
    
    int match(List<List<int[]>> spaces, List<List<int[]>> puzzles) {
        int answer = 0;
        
        boolean[] sVisited = new boolean[spaces.size()];
        boolean[] pVisited = new boolean[puzzles.size()];
        
        for(int i = 0; i < spaces.size(); i++) {
            List<int[]> space = spaces.get(i);
            
            for(int j = 0; j < puzzles.size(); j++) {
                List<int[]> puzzle = puzzles.get(j);
                
                if(!sVisited[i] && !pVisited[j] && compare(space, puzzle)) {
                    sVisited[i] = true;
                    pVisited[j] = true;
                    answer += puzzle.size();
                }
            }
        }
        return answer;
    }
    
    boolean compare(List<int[]> space, List<int[]> puzzle) {
        if(space.size() != puzzle.size()) return false;
        
        List<int[]> rotate = puzzle;
        
        for(int i = 0; i < 4; i++) {
            if(check(space, rotate)) return true;
            
            if(i < 3) rotate = rotated(rotate);
        }
        
        return false;
    }
    
    boolean check(List<int[]> space, List<int[]> puzzle) {
        for(int i = 0; i < space.size(); i++) {
            if(space.get(i)[0] != puzzle.get(i)[0] || space.get(i)[1] != puzzle.get(i)[1]) return false;
        }
        return true;
    }
    
    List<int[]> rotated(List<int[]> puzzle) {
        List<int[]> result = new ArrayList<>();
        
        for(int[] i : puzzle) {
            int r = i[1];
            int c = -i[0];
            
            result.add(new int[] {r, c});
        }
        
        return calculate(result);
    }
}