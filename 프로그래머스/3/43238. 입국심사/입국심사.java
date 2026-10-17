import java.util.*;
/**
* 심사관 수 : m,  O(m * 탐색 횟수), times.length = m
* 어떤 시간 T일 때, T/times[i] = i번째 심사관이 T동안 심사한 사람 수
*/

class Solution {
    public long solution(int n, int[] times) {
        return binarySearch(n, times);
    }
    
    public long binarySearch(int n, int[] times) {
        long left = 0;
        long right = 0;
        
        for(int i = 0; i < times.length; i++) {
            right = Math.max(right, times[i]);
        }
    
        right *= n;

        while(left < right) {
            long mid = (right - left)/2 + left;
            long sum = 0;

            for(int i = 0; i < times.length; i++) {
                sum += mid/times[i];
                if(sum >= n) break;
            }
            
            if(sum < n) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
}