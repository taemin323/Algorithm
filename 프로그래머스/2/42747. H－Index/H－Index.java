import java.util.*;

class Solution {
    
    public int solution(int[] citations) {
        int answer = 0;
        int n = citations.length;
        
        int left = 0;
        int right = 0;
        
        for(int i = 0; i < n; i++) {
            right = Math.max(right, citations[i]);
        }

        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(check(mid, citations)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    private boolean check(int mid, int[] citations) {
        int upCnt = 0;
        int downCnt = 0;
        
        for(int i = 0; i < citations.length; i++) {
            if(citations[i] >= mid) upCnt++;
            else downCnt++;
        }
        
        if(upCnt >= mid && downCnt <= mid) return true;
        
        return false;
    }
}