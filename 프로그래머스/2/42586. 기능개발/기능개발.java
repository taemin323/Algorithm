import java.util.*;
/**
* 현재 위치 cur에서 배포 타임 계산
* 해당 배포 타임에 기능 완성된 이후 프로그램 개수 카운팅
* list.add(cnt)
* 반복
*/

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        
        for(int i = 0; i < progresses.length; i++) {
            q.offer(new int[] {progresses[i], speeds[i]});
        }
        
        int time = 0;
        int cnt = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            int left = 100 - cur[0];
            int completeTime = 0;
            if(left % cur[1] == 0) {
                completeTime = left/cur[1];
            } else  {
                completeTime = left/cur[1] + 1;
            }
            
            if(time < completeTime) {
                if(cnt > 0) list.add(cnt);
                cnt = 1;
                time = completeTime;
            } else {
                cnt++;
            }
        }        
        list.add(cnt);
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}