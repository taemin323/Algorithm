import java.util.*;
/**
* 빈칸, 블럭 bfs로 저장하기 -> 정규화 필요
* 빈칸, 블럭 매칭하기 -> 회전과 비교하는 메서드 필요
*/

class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    int n;
    boolean[][] visited;
    
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        
        List<List<int[]>> spaces = new ArrayList<>();
        List<List<int[]>> puzzles = new ArrayList<>();
        
        visited = new boolean[n][n];
        
        //빈칸 구하기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && game_board[i][j] == 0) {
                    visited[i][j] = true;
                    spaces.add(bfs(i, j, game_board, 0));
                }
            }
        }
        
        visited = new boolean[n][n];
        
        //퍼즐 구하기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && table[i][j] == 1) {
                    visited[i][j] = true;
                    puzzles.add(bfs(i, j, table, 1));
                }
            }
        }
        
        return match(spaces, puzzles);
    }
    
    // 빈칸, 퍼즐 모양대로 저장하는 메서드
    List<int[]> bfs(int i, int j, int[][] board, int target) {
        List<int[]> result = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {i, j});
        
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
                    q.add(new int[] {nr, nc});
                }
            }
        }
        
        return calculate(result);
    }
    
    //정규화한 뒤 정렬까지 메서드 - 나중에 비교할 때 정렬이 필요해서 미리.
    List<int[]> calculate(List<int[]> list) {
        List<int[]> result = new ArrayList<>();
        
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for(int[] l : list) {
            minR = Math.min(minR, l[0]);
            minC = Math.min(minC, l[1]);
        }
        
        for(int[] l : list) {
            int r = l[0] - minR;
            int c = l[1] - minC;
            
            result.add(new int[] {r, c});
        }
        
        Collections.sort(result, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        return result;
    }
    
    //match 메서드
    int match(List<List<int[]>> spaces, List<List<int[]>> puzzles) {
        int answer = 0;
        
        boolean[] sUsed = new boolean[spaces.size()];
        boolean[] pUsed = new boolean[puzzles.size()];
        
        for(int i = 0; i < spaces.size(); i++) {
            List<int[]> space = spaces.get(i);
            
            for(int j = 0; j < puzzles.size(); j++) {
                List<int[]> puzzle = puzzles.get(j);
                
                if(!sUsed[i] && !pUsed[j] && rotateAndCompare(space, puzzle)) {
                    sUsed[i] = true;
                    pUsed[j] = true;
                    answer += puzzle.size();
                }
            }
        }
        return answer;
    }
    
    //회전시켜 비교하기
    boolean rotateAndCompare(List<int[]> space, List<int[]> puzzle) {
        if(space.size() != puzzle.size()) return false;
        
        List<int[]> rotate = puzzle;
        for(int i = 0; i < 4; i++) {
            if(compare(space, rotate)) return true;
            
            if(i < 3) {
                rotate = rotated(rotate);
            }
        }
        return false;
    }
    
    //회전 메서드
    List<int[]> rotated(List<int[]> rotate) {
        List<int[]> result = new ArrayList<>();
        
        for(int[] i : rotate) {
            int r = i[1];
            int c = -i[0];
            
            result.add(new int[] {r, c});
        }
        
        return calculate(result);
    }
    
    //비교 메서드
    boolean compare(List<int[]> space, List<int[]> puzzle) {
        for(int i = 0; i < space.size(); i++) {
            if(space.get(i)[0] != puzzle.get(i)[0] || space.get(i)[1] != puzzle.get(i)[1]) return false;
        }
        return true;
    }
    
}