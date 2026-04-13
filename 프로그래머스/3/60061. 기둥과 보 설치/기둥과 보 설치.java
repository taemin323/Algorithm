import java.util.*;

class Solution {
    
    boolean[][] h;
    boolean[][] w;
    
    public int[][] solution(int n, int[][] build_frame) {
        h = new boolean[n+1][n+1];
        w = new boolean[n+1][n+1];
        
        for(int i = 0; i < build_frame.length; i++) {
            int c = build_frame[i][0];
            int r = build_frame[i][1];
            int type = build_frame[i][2];
            int work = build_frame[i][3];
            
            boolean prev = (type == 0) ? h[r][c] : w[r][c];
            
            // 일단 설치 혹은 제거
            if(work == 1){
                if(type == 0) {
                    h[r][c] = true;
                } else{
                    w[r][c] = true;
                }
            } else {
                if(type == 0) {
                    h[r][c] = false;
                } else {
                    w[r][c] = false;
                }
            }
            
            if(!isValid(n)) {
                //롤백
                if(type == 0) h[r][c] = prev;
                else w[r][c] = prev;
            }
        }
        
        List<int[]> list = new ArrayList<>();
        
        for(int c = 0; c <= n; c++) {
            for(int r = 0; r <= n; r++) {
                if(h[r][c]) list.add(new int[] {c,r,0});
                if(w[r][c]) list.add(new int[] {c,r,1});
            }
        }
        
        int[][] answer = new int[list.size()][3];
        
        for(int i = 0; i < list.size(); i++) {
            answer[i][0] = list.get(i)[0];
            answer[i][1] = list.get(i)[1];
            answer[i][2] = list.get(i)[2];
        }
        return answer;
    }
    
    boolean isValid(int n) {
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(h[i][j]) {
                    if(!(i == 0 || w[i][j] || (j > 0 && w[i][j-1]) || (i > 0 && h[i-1][j]))) return false;
                } 
                
                if(w[i][j]) {
                    if(!((i > 0 && h[i-1][j]) || (i > 0 && j < n && h[i-1][j+1]) || (j > 0 && j < n && w[i][j-1] && w[i][j+1]))) return false;
                }
            }
        }
        
        return true;
    }
}