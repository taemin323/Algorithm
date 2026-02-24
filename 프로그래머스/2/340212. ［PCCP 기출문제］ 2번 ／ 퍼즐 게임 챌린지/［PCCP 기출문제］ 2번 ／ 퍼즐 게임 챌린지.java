import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;
        int answer = 0;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(calculate(mid, diffs, times) > limit) {
                left = mid+1;
            } else {
                right = mid-1;
                answer = mid;
            }
        }
        
        return answer;
    }
    
    private long calculate(long mid, int[] diffs, int[] times) {
        long result = (long) times[0];
        
        for(int i = 1; i < diffs.length; i++) {
            if(diffs[i] <= mid) {
                result += times[i];
            } else {
                long tmp = (long) diffs[i] - mid;
                result += (times[i] + times[i-1]) * tmp + times[i];
            }
        }
        return result;
    }
}