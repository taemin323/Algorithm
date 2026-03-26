import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int n = (arr.length + 1) / 2;
        
        int[] nums = new int[n];
        char[] ops = new char[n-1];
        
        for(int i = 0; i < arr.length; i++) {
            if(i % 2 == 0) nums[i / 2] = Integer.parseInt(arr[i]);
            else ops[i / 2] = arr[i].charAt(0);
        }
        
        // i~j 구간의 최댓값, 최솟값
        int[][] maxDp = new int[n][n];
        int[][] minDp = new int[n][n];
        
        // 초기값.
        for(int i = 0; i < n; i++) {
            maxDp[i][i] = nums[i];
            minDp[i][i] = nums[i];
        }
        
        for(int len = 2; len <= n; len++){
            for(int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                maxDp[i][j] = Integer.MIN_VALUE;
                minDp[i][j] = Integer.MAX_VALUE;
                
                for(int k = i; k < j; k++) {
                    if(ops[k] == '+') {
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i][k] + maxDp[k+1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i][k] + minDp[k+1][j]);
                    } else {
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i][k] - minDp[k+1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i][k] - maxDp[k+1][j]);
                    }
                }
            }
        }
        return maxDp[0][n-1];
    }
}