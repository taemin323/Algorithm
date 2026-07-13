import java.util.*;
/**
* lock의 좌표를 확장시켜줘야됨 -> lock 배열의 크기를 벗어나게 열쇠를 맞춰볼 수 있기 때문에
* 즉 한 변의 길이 : n + 2*(m-1)
* key를 모든 위치에 놓아봐야된다. 회전 4가지 경우까지 다.
* 그리고 lock의 모든 부분이 1로 변해있다면 자물쇠가 채워진 것. 어느 곳 하나라도 0 or 2이면 오류
*/

class Solution {
    int n;
    int m;
    public boolean solution(int[][] key, int[][] lock) {
        n = lock.length;
        m = key.length;
        int k = n + 2*(m-1);
        
        //확장 보드
        int[][] board = new int[k][k];
        for(int i = m-1; i < m-1+n; i++) {
            for(int j = m-1; j < m-1+n; j++) {
                //실제 lock 위치의 값들은 복사.
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
    
    // 배열 자체를 회전시키는 메서드
    int[][] rotated(int[][] key) {
        
        int[][] result = new int[m][m];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                result[j][m-1-i] = key[i][j];
            }
        }
        
        return result;
    }
    
    //비교 메서드
    boolean compare(int[][] board, int[][] key, int dr, int dc) {
        // key의 돌기를 board에 더하기
        for(int i = 0; i < key.length; i++) {
            for(int j = 0; j < key.length; j++) {
                if(key[i][j] == 1) board[dr+i][dc+j] += 1;
            }
        }
        
        // 원래 lock 부분만 모든 부분이 1인지 체크
        boolean result = true;
        for(int i = m-1; i < m-1+n; i++) {
            for(int j = m-1; j < m-1+n; j++) {
                if(board[i][j] != 1) {
                    result = false;
                    break;
                }
            }
        }
        
        //원복
        for(int i = 0; i < key.length; i++) {
            for(int j = 0; j < key.length; j++) {
                if(key[i][j] == 1) board[dr+i][dc+j] -= 1;
            }
        }
        
        return result;
    }
}