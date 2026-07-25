import java.util.*;

class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        //첫번째 털었을 경우 -> 마지막 집 도달 x
        int[] dp1 = new int[n];
        dp1[0] = money[0];
        dp1[1] = money[0];
        int max1 = dp1[0];
        
        for(int i = 2; i < n-1; i++) {
            dp1[i] = Math.max(dp1[i-1], money[i] + dp1[i-2]);
            max1 = Math.max(max1, dp1[i]);
        }
        
        //첫번째 안털었을 경우 -> 마지막 집 갈 수 있음.
        int[] dp2 = new int[n];
        dp2[1] = money[1];
        int max2 = dp2[1];
        
        for(int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i-1], money[i] + dp2[i-2]);
            max2 = Math.max(max2, dp2[i]);
        }
        
        return Math.max(max1, max2);
    }
}