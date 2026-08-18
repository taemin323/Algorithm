import java.util.*;
/**
* 
*/

class Solution {
    public int solution(int[] d, int budget) {
        Arrays.sort(d);
        
        int answer = 0;
        
        int i = 0;
        while(i < d.length) {
            if(d[i] <= budget) {
                answer++;
                budget -= d[i];
            }
            
            i++;
        }
        
        return answer;
    }
}