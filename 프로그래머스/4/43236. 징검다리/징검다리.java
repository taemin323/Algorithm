import java.util.*;
/**
* 일단 바위 배열을 정렬 때려야 됨.
* 바위를 n개 제거했을 경우 중에서 거리의 최솟값을 구하고 그 중 제일 큰 값 반환.
* 완전탐색은 일단 무조건 시간초과.
* 
*/

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int left = 0;
        int right = distance;
        
        while(left <= right) {
            int mid = (right - left)/2 + left;
            
            if(removedRocks(rocks, mid, distance) <= n) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    
        return answer;
    }
    
    int removedRocks(int[] rocks, int mid, int distance) {
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
        
        if(end - start < mid) removedCnt++;
        return removedCnt;
    }
}