import java.util.*;

class Solution {
    public int solution(int n, int w, int num) {
        int row = 0;
        if(n % w == 0) {
            row = n / w;
        } else {
            row = (n / w) + 1;
        }
        
        int[][] board = new int[row][w];
        int start = 1;
        for(int i = 0; i < row; i++) {
            
            if(i % 2 != 0) {
                for(int j = w-1; j >= 0; j--) {
                    board[i][j] = start++;
                }
            } else {
                for(int j = 0; j < w; j++) {
                    board[i][j] = start++;
                }
            }
        }
        
        int answer = 0;
        int numR = 0;
        int numC = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < w; j++) {
                if(board[i][j] == num) {
                    numR = i;
                    numC = j;
                }   
            }
        }
        
        if(board[row-1][numC] > n) {
            answer = (row-1) - numR; 
        } else {
            answer = (row-1) - numR + 1;
        }
        
        return answer;
    }
}