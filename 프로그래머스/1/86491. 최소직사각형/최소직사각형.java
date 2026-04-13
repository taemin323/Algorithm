import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int h = 0;
        int w = 0;
        
        for(int i = 0; i < sizes.length; i++) {
            h = Math.max(h, Math.max(sizes[i][0], sizes[i][1]));
            w = Math.max(w, Math.min(sizes[i][0], sizes[i][1]));
        }
        
        int answer = h * w;
        return answer;
    }
}