import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        
        long answer = 0;
        
        long right = (long) Arrays.stream(times).max().getAsInt() * n;
        long left = 1;
        long mid = 0;
        
        while (left <= right) {
            mid = (left + right) / 2;
            long cnt = 0;
            
            for (int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
            }
            
            if (cnt < n) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }
        System.out.println(mid);
        return answer;
    }
}