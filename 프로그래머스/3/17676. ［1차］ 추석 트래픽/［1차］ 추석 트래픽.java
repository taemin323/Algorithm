import java.util.*;
/**
* 일단 문자열로된 시간들을 밀리초로 변환해주자.
*/

class Solution {
    public int solution(String[] lines) {
        int n = lines.length;
        long[] start = new long[n];
        long[] end = new long[n];
        int answer = 0;

        for(int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(" ");
            //어차피 9월 15일만 포함하는거니가. parts[0]은 냅둬.
            String time = parts[1];
            double duration = Double.parseDouble(parts[2].replace("s", ""));
            
            long endMs = toMs(time);
            long durationMs = Math.round(duration * 1000);
        
            end[i] = endMs;
            start[i] = endMs - durationMs + 1;
        }
        
        for(int i = 0; i < n; i++) {
            answer = Math.max(answer, getCount(start[i], start, end, n));
        }
        
        for(int i = 0; i < n; i++) {
            answer = Math.max(answer, getCount(end[i], start, end, n));
        }
        
        return answer;
    }
    
    //밀리초로 변환
    long toMs(String time) {
        String[] parts = time.split(":");
        
        long hour = Integer.parseInt(parts[0]) * 3600L;
        long min = Integer.parseInt(parts[1]) * 60L;
        double sec = Double.parseDouble(parts[2]);
        
        long total = (hour + min) * 1000L + Math.round(sec * 1000);
        return total;
    }
    
    int getCount(long t, long[] start, long[] end, int n) {
        long windowEnd = t + 1000;
        int cnt = 0;
        
        for(int i = 0; i < n; i++) {
            long overLapStart = Math.max(t, start[i]);
            long overLapEnd = Math.min(windowEnd - 1, end[i]);
            
            if(overLapStart <= overLapEnd) cnt++;
        }
        return cnt;
    }
}