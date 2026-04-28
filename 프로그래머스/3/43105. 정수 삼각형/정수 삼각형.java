import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int[][] dp = triangle;
        int answer = Integer.MIN_VALUE;
        
        dp[1][0] = triangle[0][0] + triangle[1][0];
        dp[1][1] = triangle[0][0] + triangle[1][1];
        
        // dp[i][j] : i줄 j번째까지의 누적합
        for(int i = 2; i < triangle.length; i++) {
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            dp[i][triangle[i].length-1] = dp[i-1][triangle[i-1].length-1] + triangle[i][triangle[i].length-1];
        }
        
        for(int i = 2; i < triangle.length; i++) {
            for(int j = 1; j < triangle[i].length; j++) {
                if(j > 0 && j < triangle[i].length-1) {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                }            
            }
        }
        
        
        for(int i = 0; i < dp[dp.length-1].length; i++) {
            answer = Math.max(answer, dp[dp.length-1][i]);
        }

        return answer;
    }
}