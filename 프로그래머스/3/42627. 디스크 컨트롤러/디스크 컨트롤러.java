import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int n = jobs.length;
        
        // 요청 시간 기준 정렬
        Arrays.sort(jobs, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        
        // 작업 시간 기준 최소 힙
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> o1[1] - o2[1]        
        );
        
        int time = 0;
        int idx = 0;
        int total = 0;
        
        while(idx < n || !pq.isEmpty()) {
            // 현재 시점까지 들어온 작업 모두 큐에 추가
            while(idx < n && jobs[idx][0] <= time) {
                pq.offer(jobs[idx]);
                idx++;
            }
            
            // 실행할 작업 없다며 다음 작업 시점으로 이동
            if(pq.isEmpty()) {
                time = jobs[idx][0];
                continue;
            }
            
            // 작업 실행
            int[] job = pq.poll();
            time += job[1];
            total += time - job[0];
        }
        
        return total / n;
    }
}