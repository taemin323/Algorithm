import java.util.*;
/**
* S -> K + K -> A + K -> B
* 즉 S-> K + A -> K + B -> K하면 됨.
*/

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dist = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        
        for(int i = 0; i < fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];
            
            dist[from][to] = Math.min(dist[from][to], cost);
            dist[to][from] = Math.min(dist[to][from], cost);
        }
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) continue;
                    
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        
        for(int k = 1; k <= n; k++) {
            int cur = dist[s][k] + dist[a][k] + dist[b][k];
            answer = Math.min(answer, cur);
        }
        
        return answer;
    }
}