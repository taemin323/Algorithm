import java.util.*;
/**
* 25분
* 범위가 엄청 큼. dp는 안될듯.
* 이분탐색? -> 뭘 기준으로 이분탐색을 할것이냐? 
* 시간을 기준으로.
*/

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long max = 0;
        for(int time : times) {
            max = Math.max(max, time);
        }
        
        long left = 1;
        long right = (long) max * n;
        
        while(left <= right) {
            long mid = (right - left)/2 + left;
            long cnt = 0;
            
            for(int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
            }
            
            if(cnt < n) {
                left = mid+1;
            } else {
                answer = mid;
                right = mid-1;
            }
        }
        
        return answer;
    }
}