import java.util.*;

class Solution {
    int[][] memo;
    
    public int solution(int[][] triangle) {
        int n = triangle.length;
        
        memo = new int[n][n];
        for(int[] row : memo) Arrays.fill(row, -1);
        
        return dfs(0,0,triangle);
    }
    
    int dfs(int r, int c, int[][] triangle) {
        if(r == triangle.length-1) {
            return triangle[r][c];
        }
        
        if(memo[r][c] != -1) {
            return memo[r][c];
        }
        
        memo[r][c] = triangle[r][c] + Math.max(dfs(r+1, c, triangle), dfs(r+1, c+1, triangle));
    
        return memo[r][c];    
    }
}