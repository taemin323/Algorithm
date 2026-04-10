import java.util.*;

class Solution {
    List<int[]> list = new ArrayList<>();
    int[] arr;
    boolean[] visited;
    int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        arr = new int[dungeons.length];
        visited = new boolean[dungeons.length];
        
        perm(0, dungeons, k);
        
        return answer;
    }
    
    void perm(int cnt, int[][] dungeons, int k) {
        if(cnt == dungeons.length) {
            calculate(arr, dungeons, k);
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
    
    void calculate(int[] arr, int[][]dungeons, int k) {
        int cnt = 0;
        
        for(int i = 0; i < arr.length; i++) {
            int idx = arr[i];
            
            if(k >= dungeons[idx][0]) {
                k -= dungeons[idx][1];
                cnt++;
            }
        }
        
        answer = Math.max(cnt, answer);
    }
}