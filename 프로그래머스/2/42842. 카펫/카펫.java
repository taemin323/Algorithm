/**
* 8 <= brown <= 5,000
* 1 <= yellow <= 2,000,000
* 가로 길이 >= 세로 길이
*/

import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int carpet = brown + yellow;
        
        // yellow가 존재하기 위해서는 가로의 길이와 세로의 길이가 3이상
        for(int i = 3; i <= carpet; i++) {
            int c = i;//세로
            int r = carpet / c;//가로
            
            // 가로의 개수가 3 이하는 패스
            if(r < 3) {
                continue;
            }
            
            //가로는 세로의 길이보다 크거나 같다 조건
            if (r >= c) {
                if((r-2) * (c-2) == yellow) {
                    answer[0] = r;
                    answer[1] = c;
                    break;
                }
            }
        }
        
        
        return answer;
    }
}