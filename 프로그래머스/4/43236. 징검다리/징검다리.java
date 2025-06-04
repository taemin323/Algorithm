import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int left = 1;
        int right = distance;
        
        while (left <= right) {
            int mid = (left + right)/2;
            
            if(removedRocks(rocks, mid, distance) <= n) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
    
    public int removedRocks(int[] rocks, int mid, int distance) {
        int removedCnt = 0;
        
        int start = 0;
        int end = distance;
        
        for(int i = 0; i < rocks.length; i++) {
            if(rocks[i] - start < mid) {
                removedCnt++;
                continue;
            }
            start = rocks[i];
        }
        
        if(end- start < mid) removedCnt++;
        
        return removedCnt;
    }
}