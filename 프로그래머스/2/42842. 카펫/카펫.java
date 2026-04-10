import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        for(int w = 0; w < 5000; w++) {
            for(int h = 0; h < 5000; h++) {
                if(w*h == brown + yellow && (w-2)*(h-2) == yellow) {
                    answer[0] = w;
                    answer[1] = h;
                }
            }
        }
        
        return answer;
    }
}