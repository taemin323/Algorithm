import java.util.*;
/**
* 빈 공간의 칸 수가 일치한다면 해당 퍼즐 조각 회전 시작.
* 회전하여 빈 공간과 일치한다면 방문 처리 후 정답 카운트 증가
* 회전하면서 빈 공간과 일치하는지 체크하는 메서드가 관건
* 빈 공간의 좌표, 퍼즐의 좌표 모두 정규화 한 다음에 회전 및 비교가 가능
*/

class Solution {
    List<List<int[]>> blanks = new ArrayList<>();
    List<List<int[]>> puzzles = new ArrayList<>();
    int n;
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        boolean[][] visited = new boolean[n][n];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && game_board[i][j] == 0) blanks.add(bfs(i, j, game_board, visited, 0));
            }
        }
        
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && table[i][j] == 1) puzzles.add(bfs(i, j, table, visited, 1));
            }
        }
        
        return match(blanks, puzzles);
    }
    
    //빈 칸이랑 퍼즈들 각각 저장하기 위한 bfs
    List<int[]> bfs(int r, int c, int[][] board, boolean[][] visited, int target) {
        List<int[]> result = new ArrayList<>();
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});
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
                    q.add(new int[] {nr, nc});
                }
            }
        }
        
        return calculate(result);
    }
    
    //정규화 메서드 - 퍼즐과 빈 칸을 비교하기 위해
    List<int[]> calculate(List<int[]> list) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for(int[] l : list) {
            minR = Math.min(minR, l[0]);
            minC = Math.min(minC, l[1]);
        }
        
        for(int[] l : list) {
            l[0] -= minR;
            l[1] -= minC;
        }
        return list;
    }
    
    // 빈 칸과 퍼즐 매칭 메서드
    int match(List<List<int[]>> blanks, List<List<int[]>> puzzles) {
        int result = 0;
        boolean[] visited = new boolean[puzzles.size()];
        
        for(int i = 0; i < blanks.size(); i++) {
            List<int[]> blank = blanks.get(i);
            
            for(int j = 0; j < puzzles.size(); j++) {
                List<int[]> puzzle = puzzles.get(j);
                
                if(visited[j]) continue;
                
                if(rotateAndCompare(blank, puzzle)) {
                    result += puzzle.size();
                    visited[j] = true;
                    break;
                }
            }
        }
        return result;
    }
    
    // 회전시켜서 비교하는 메서드
    boolean rotateAndCompare(List<int[]> blank, List<int[]> puzzle) {
        //길이가 다르면 애초에 다름
        if(blank.size() != puzzle.size()) return false;
        
        //회전시켰을 때 4개의 모양을 blank와 비교
        List<int[]> rotate = puzzle;
        for(int i = 0; i < 4; i++) {
            if(compare(blank, rotate)) return true;
            
            if(i < 3) {
                rotate = rotated(rotate);
            }
        }
        return false;
    }
    
    // 비교 메서드
    boolean compare(List<int[]> blank, List<int[]> rotate) {
        Collections.sort(blank, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        Collections.sort(rotate, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        for(int i = 0; i < blank.size(); i++) {
            int[] b = blank.get(i);
            int[] r = rotate.get(i);
            
            if(b[0] != r[0] || b[1] != r[1]) return false;
        }
        
        return true;
    }
    
    // 회전 메서드
    List<int[]> rotated(List<int[]> puzzle) {
        List<int[]> result = new ArrayList<>();
        
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for(int[] p : puzzle) {
            int r = p[1];
            int c = -p[0];
            
            result.add(new int[] {r, c});
            
            if(r < minR) minR = r;
            if(c < minC) minC = c;
        }
        
        //정규화
        for(int[] i : result) {
            i[0] -= minR;
            i[1] -= minC;
        }
        
        return result;
    }
}