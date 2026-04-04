import java.util.*;

class Solution {
    private List<int[]>[] graph;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 플로이드 워셜 세팅
        int[][] dist = new int[n+1][n+1];
        int INF = 100_000_000;
        
        for(int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        // 플로이드 워셜 채우기
        for(int i = 0; i < fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];
            
            dist[from][to] = cost;
            dist[to][from] = cost;
        }
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];    
                    }
                }
            }
        }
        
        // 각자 가는 방법 vs 합승 후 각자
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            // i == s인 경우 처음부터 따로 출발한 것과 같음.
            int cur = dist[s][i] + dist[i][a] + dist[i][b];
            
            answer = Math.min(answer, cur);
        }
        
        return answer;
    }
}