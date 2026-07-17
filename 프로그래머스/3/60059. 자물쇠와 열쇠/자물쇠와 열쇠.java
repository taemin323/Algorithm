import java.util.*;
/**
* 맵 확장시켜서 실제로 덮어보는 문제
*/

class Solution {
    int n;
    int m;
    int k;
    public boolean solution(int[][] key, int[][] lock) {
        n = lock.length;
        m = key.length;
        k = n + 2*(m-1);
        
        int[][] board = new int[k][k];
        for(int i = m-1; i < m-1 + n; i++) {
            for(int j = m-1; j < m-1 + n; j++) {
                board[i][j] = lock[i-m+1][j-m+1];
            }
        }
        
        int[][] rotate = key;
        for(int i = 0; i < 4; i++) {
            for(int dr = 0; dr < n+m-1; dr++) {
                for(int dc = 0; dc < n+m-1; dc++) {
                    if(compare(board, rotate, dr, dc)) return true;
                }
            }
            
            if(i < 3) rotate = rotated(rotate);
        }
        return false;
    }
    
    int[][] rotated(int[][] key) {
        int[][] result = new int[m][m];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                result[j][m-1-i] = key[i][j];
            }
        }
        
        return result;
    }
    
    boolean compare(int[][] board, int[][] key, int dr, int dc) {
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                if(key[i][j] == 1) board[i+dr][j+dc] += 1;
            }
        }
        
        boolean result = true;
        for(int i = m-1; i < m-1+n; i++) {
            for(int j = m-1; j < m-1+n; j++) {
                if(board[i][j] != 1) {
                    result = false;
                    break;
                }
            }
        }
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                if(key[i][j] == 1) board[i+dr][j+dc] -= 1;
            }
        }     
        
        return result;
    }
}