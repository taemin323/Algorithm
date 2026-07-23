import java.util.*;
/**
* 응답완료시간과 처리시간을 모두 밀리초로 변환시키자.
* 각 응답시작시간, 끝시간 시점에서만 몇개인지 체크하면 됨. 그 시점이 사실상 변곡점이니까.
* 시작시간 = 종료시간 - T + 1 (밀리초 기준)
* startT2 - startT1 > 0 -> 구간이 떨어져 있음. 
* startT2 - startT1 < 0 -> 겹침 
* 어차피 9월 15일 하루만 고려하므로 날짜 정보는 필요없음
*/

class Solution {
    int n;
    long[] start;
    long[] end;
    
    public int solution(String[] lines) {
        n = lines.length;
        start = new long[lines.length];
        end = new long[lines.length];
        
        for(int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(" ");
            String endTime = parts[1];
            double duration = Double.parseDouble(parts[2].replace("s", ""));
            
            long endMs = toMs(endTime);
            long durationMs = Math.round(duration * 1000);
            
            long startMs = endMs - durationMs + 1;
            
            start[i] = startMs;
            end[i] = endMs;
        }
        
        int answer = 1;
        
        for(int i = 0; i < start.length; i++) {
            answer = Math.max(answer, getCount(start[i]));
        }
        
        for(int i = 0; i < end.length; i++) {
            answer = Math.max(answer, getCount(end[i]));
        }
        return answer;
    }
    
    long toMs(String time) {
        String[] parts = time.split(":");
        
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        double s = Double.parseDouble(parts[2]);
        
        long hSec = h * 3600L;
        long mSec = m * 60L;
        
        long total = (hSec + mSec) * 1000L + Math.round(s * 1000);
        return total;
    }
    
    int getCount(long t) {
        long windowEnd = t + 1000;
        int cnt = 0;
        
        for(int i = 0; i < n; i++) {
            long overLapStart = Math.max(t, start[i]);
            long overLapEnd = Math.min(windowEnd-1, end[i]);
            if(overLapStart <= overLapEnd) cnt++;
        }
        
        return cnt;
    }
}