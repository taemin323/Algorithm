import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
    
        Queue<Integer> q = new LinkedList<>();
        int currentW = 0;
        int idx = 0;
        int time = 0;
        
        for(int i = 0; i < bridge_length; i++) {
            q.offer(0);
        }
        
        while(idx < truck_weights.length) {
            time++;
            
            //한 칸 이동(맨 앞 제거)
            currentW -= q.poll();
            
            if(currentW + truck_weights[idx] <= weight) {
                q.offer(truck_weights[idx]);
                currentW += truck_weights[idx];
                idx++;
            } else {
                q.offer(0);
            }
        }
        
        //마지막 트럭이 다리 건너는 시간 추가
        return time + bridge_length;
    }
}