import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int carpet = brown + yellow;
        
        for(int i = 3; i <= Math.sqrt(carpet); i++) {
            int h = i;
            int w = carpet / h;
            
            if((w-2)*(h-2) == yellow) {
                answer[0] = w;
                answer[1] = h;
                break;
            
            }
        }
        
        return answer;
    }
}