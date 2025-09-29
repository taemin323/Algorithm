import java.util.*;

class Solution {
    private int calculate(int time) {
        int h = time / 100;
        int m = time % 100;
        
        m += 10;
        h += m / 60;
        m %= 60;
        
        return h * 100 + m;
    }
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        int saturday = 6 - (startday % 7);
        int sunday = (saturday + 1) % 7;
        
        for(int i = 0; i < timelogs.length; i++){
            int base = calculate(schedules[i]);
            boolean flag = true;
            
            for(int j = 0; j < timelogs[0].length; j++) {
                if(j == saturday || j == sunday) continue;
                
                if(timelogs[i][j] > base) flag = false;
            }
            
            if(flag) answer++;
        }
        
        return answer;
    }
}