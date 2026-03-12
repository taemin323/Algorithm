import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int cur = Integer.MIN_VALUE;
        
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        for(int i = 0; i < routes.length; i++) {
            
            if(cur >= routes[i][0]) continue;
            
            cur = routes[i][1];
            answer++;
            
        }
        
        return answer;
    }
}