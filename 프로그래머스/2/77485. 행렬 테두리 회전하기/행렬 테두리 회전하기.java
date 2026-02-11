import java.util.*;

class Solution {
    private static int[][] board;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int n = queries.length;
        int[] answer = new int[n];
        
        
        // 초기 행렬
        board = new int[rows][columns];
        int num = 1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                board[i][j] = num++;
            }
        }
        
        for(int i = 0; i < n; i++) {
            int r1 = queries[i][0]-1;
            int c1 = queries[i][1]-1;
            int r2 = queries[i][2]-1;
            int c2 = queries[i][3]-1;
            
            answer[i] = rotate(r1, c1, r2, c2);
        }
        return answer;
    }
    
    private static int rotate(int r1, int c1, int r2, int c2) {
        int prev = board[r1][c1];
        int min = prev;
        
        // 위쪽 테두리
        for(int j = c1+1; j <= c2; j++) {
            int tmp = board[r1][j];
            board[r1][j] = prev;
            prev = tmp;
            min = Math.min(min, prev);
        }
        
        // 오른쪽 테두리
        for(int i = r1+1; i <= r2; i++) {
            int tmp = board[i][c2];
            board[i][c2] = prev;
            prev = tmp;
            min = Math.min(min, prev);
        }
        
        // 아래쪽 테두리
         for(int j = c2-1; j >= c1; j--) {
            int tmp = board[r2][j];
            board[r2][j] = prev;
            prev = tmp;
            min = Math.min(min, prev);
        }
        
        // 왼쪽 테두리
        for(int i = r2-1; i >= r1; i--) {
            int tmp = board[i][c1];
            board[i][c1] = prev;
            prev = tmp;
            min = Math.min(min, prev);
        }
        return min;
    }
}