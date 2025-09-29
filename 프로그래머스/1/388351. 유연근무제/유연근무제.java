import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        int saturday = -1;
        
        switch(startday) {
            case 1 : saturday = 5;
                break;
            case 2 : saturday = 4;
                break;
            case 3 : saturday = 3;
                break;
            case 4 : saturday = 2;
                break;
            case 5 : saturday = 1;
                break;
            case 6 : saturday = 0;          
                break;
            default : saturday = 6;
        }
        
        int sunday = -1;
        if(saturday == 6) sunday = 0;
        else sunday = saturday+1;
        
        for(int i = 0; i < timelogs.length; i++){
            int base = schedules[i] + 10;
            
            if(base % 100 >= 60) {
                int hour = base / 100 + 1;
                int minute = base % 100 - 60;
                base = hour * 100 + minute;
            }
            
            
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