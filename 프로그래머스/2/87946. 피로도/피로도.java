import java.util.*;

class Solution {
    boolean[] visited;
    int[] arr;
    int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        
        visited = new boolean[dungeons.length];
        arr = new int[dungeons.length];
        
        perm(0, dungeons, k);
        
        return answer;
    }
    
    public void perm(int cnt, int[][] dungeons, int k) {
        if(cnt == dungeons.length) {
            int sum = 0;
            for(int i : arr) {
                if(k >= dungeons[i][0]) {
                    k -= dungeons[i][1];
                    sum++;
                }
            }
            answer = Math.max(answer, sum);
            return;
        }
        
        for(int i = 0; i < dungeons.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[cnt] = i;
                perm(cnt+1, dungeons, k);
                visited[i] = false;
            }
        }
    }
}