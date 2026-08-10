import java.util.*;
/**
* N이 50 이하이기 때문에 플로이드 워셜로 충분히 가능
*/

class Solution {
    
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        int[][] dist = new int[N+1][N+1];
        
        for(int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        for(int i = 1; i <= N; i++) dist[i][i] = 0;
        
        for(int i = 0 ; i < road.length; i++) {
            int from = road[i][0];
            int to = road[i][1];
            int cost = road[i][2];
            
            dist[from][to] = Math.min(dist[from][to], cost);
            dist[to][from] = Math.min(dist[to][from], cost);
        }
        
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) continue;
                    
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        for(int i = 1; i<= N; i++) {
            if(dist[1][i] <= K) answer++;
        }
        
        return answer;
    }
}