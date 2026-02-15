import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int n = priorities.length;
        
        Queue<int[]> q = new LinkedList<>();
        
        for(int i = 0; i < n; i++) {
            q.offer(new int[] {i, priorities[i]});
        }
        
        int cnt = 0;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            boolean isMax = true;
            
            for(int[] process : q) {
                if(process[1] > cur[1]) {
                    isMax = false;
                    break;
                }
            }
            
            if(isMax) {
                cnt++;
                if(cur[0] == location) return cnt;
            } else {
                q.offer(cur);
            }
        }
        return cnt;
    }
}