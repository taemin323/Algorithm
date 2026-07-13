import java.util.*;
/**
* 
*/

class Solution {
    public int solution(String[] lines) {
        int n = lines.length;
        long[] start = new long[n];
        long[] end = new long[n];
        
        for(int i = 0; i < n; i++) {
            String[] parts = lines[i].split(" ");
            String time = parts[1];
            double duration = Double.parseDouble(parts[2].replace("s", ""));
            
            long endMs = toMs(time);
            long durationMs = Math.round(duration * 1000);
            
            end[i] = endMs;
            start[i] = endMs - durationMs + 1;
        }
        
        int answer = 0;
        
        for(int i = 0; i < n; i++) {
            answer = Math.max(answer, getCount(start[i], start, end, n));
        }
        
        for(int i = 0; i < n; i++) {
            answer = Math.max(answer, getCount(end[i], start, end, n));
        }
        
        return answer;
    }
    
    //"20:59:57.421" -> 밀리초 단위로 변환
    long toMs(String time) {
        String[] timeParts = time.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int min = Integer.parseInt(timeParts[1]);
        double sec = Double.parseDouble(timeParts[2]);
        
        long hourSec = hour * 3600L;
        long minSec = min * 60L;
        
        long total = (hourSec + minSec) * 1000L + Math.round(sec * 1000);
        return total;
    }
    
    //후보시각
    int getCount(long t, long[] start, long[] end, int n) {
        long windowEnd = t + 1000;
        int cnt = 0;
        for(int j = 0; j < n; j++) {
            long overlapStart = Math.max(t, start[j]);
            long overlapEnd = Math.min(windowEnd - 1, end[j]);
            if(overlapStart <= overlapEnd) cnt++;
        }
        return cnt;
    }
}