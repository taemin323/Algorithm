import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int w = 0;
        int h = 0;
        
        for(int i = 0; i < sizes.length; i++) {
            if(sizes[i][0] > sizes[i][1]) {
                int tmp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
            }
        }
        
        for(int i = 0; i < sizes.length; i++) {
            w = Math.max(w, sizes[i][0]);
            h = Math.max(h, sizes[i][1]);
        }
        
        int answer = w * h;
        return answer;
    }
}