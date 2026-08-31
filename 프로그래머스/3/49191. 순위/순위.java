import java.util.*;
/**
* 내가 이긴 선수들한테 진 선수들도 결국 내가 이긴 선수들로 포함이 된다.
* 플로이드 워셜로 될듯? 
* record[i][j] : i가 j에게 이겼다는 기록
*/

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        boolean[][] record = new boolean[n+1][n+1];
        
        for(int i = 0; i < results.length; i++) {
            int winner = results[i][0];
            int loser = results[i][1];
            
            record[winner][loser] = true;
        }
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(record[i][k] && record[k][j]) {
                        record[i][j] = true;
                    }
                }
            }
        }
        
        for(int i = 1; i <= n; i++) {
            int cnt = 0;
            for(int j = 1; j <= n; j++) {
                if(record[i][j] || record[j][i]) cnt++;
            }
            
            if(cnt == n-1) answer++;
        }
        
        return answer;        
    }
}