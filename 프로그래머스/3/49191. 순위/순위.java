import java.util.*;
/**
* A가 B를 이이고, B가 C를 이기면 -> A는 C를 이긴다.
* 나를 제외한 모든 사람(N-1명)과의 승패 관계가 정리된 사람만 순위가 정해질 수 있음.
*/

class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] graph = new boolean[n+1][n+1];
        
        for(int i = 0; i < results.length; i++) {
            int winner = results[i][0];
            int loser = results[i][1];
            
            graph[winner][loser] = true;
        }
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    //i가 k를 이기고, k가 j를 이겼다면 -> i는 j를 이김
                    if(graph[i][k] && graph[k][j]) graph[i][j] = true;
                }
            }
        }
        
        //정확한 순위를 알 수 있는 선수 측정
        int answer = 0;
        for(int i = 1; i <= n; i++) {
            int cnt = 0;
            for(int j = 1; j<= n; j++) {
                //i가 j를 이겼거나, j가 i를 이겼다면(관계 성립)
                if(graph[i][j] || graph[j][i]) cnt++;
            }
            
            //그리고 관계가 n-1라면 -> 나를 제외한 모든 사람과 관계가 성립된다는 것.
            if(cnt == n-1) answer++;
        }
        return answer;
    }
}