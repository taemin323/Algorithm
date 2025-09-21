import java.util.*;
/**
* 1 <= works <= 20,000
* works[i] <= 50000 (자연수)
* n <= 1,000,000 (자연수)
*/

class Solution {
    public long solution(int n, int[] works) {
        
        long total = 0;
        int max = 0;
        for(int i = 0; i < works.length; i++) {
            total += works[i];
            max = Math.max(max, works[i]);
        }
        
        // 모든 작업을 다 깎아도 시간이 남으면 다 0이므로 야근 지수 0
        if(total <= n) return 0;
        
        // 이분 탐색을 최종 작업량 k 찾기
        // k로 작업량을 맞추면 n시간이 충분하지만, k-1까지로 작업량으 맞추면 n시간보다 더 많은 시간 필요함
        // need(k) >= n이면서 need(k+1) < n이어야함.
        int low = 0;
        int high = max;
        while(low < high) {
            int mid = (low + high + 1) >>>1;
            long need = calculate(works, mid);
            if(need >= n) {
                //mid까지 작업량을 내려도 n시간을 초과한다 -> k를 더 키워야함.
                low = mid;
            } else {
                //mid까지 작업량을 내렸는데 시간이 남는다 -> k를 더 줄여도 됨.
                high = mid - 1;
            }
        }
        int k = low;
        
        //전부 k+1까지 작업량을 깎았다고 가정
        long tmp = calculate(works, k+1);
        // 그러면 아직 남은 시간이 존재
        long remain = n - tmp;
        
        long answer = 0;
        for(int w : works) {
            if(w <= k) {
                answer += (long) w * w;
            } else {
                if(remain > 0) {
                    answer += (long) k * k;// k+1 -> k로 더 깎을 수 있음.
                    remain--;
                } else {
                    answer += (long) (k+1) * (k+1);// 남은 시간 없으니까. k+1까지만 깎는거.
                }
            }
        }
        
        return answer;
    }
    
    // 모든 작업을 target 이하로 맞추는 데 필요한 총 시간
    long calculate(int[] works, int target) {
        long need = 0;
        for(int w : works) {
            if(w > target) need += (w - target);
        }
        return need;
    }
}