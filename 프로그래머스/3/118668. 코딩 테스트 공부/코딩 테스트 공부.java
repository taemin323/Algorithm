import java.util.*;
/**
* 능력을 올리는 방법
* 1. 공부하기 -> 1을 높이기 위해 1의 시간 필요
* 2. 문제 풀기 -> 문제에 할당된 수치만큼 올리기 위해 문제에 할당된 시간 필요
* 문제는 해당 문제에 필요한 능력이 충족되어야 풀 수 있음
*/

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        //alp, cop의 상한선
        //어차피 문제 요구치 중 최댓값을 넘어가면, 그 이상 올라가봐야 새로 풀 수 있는 문제가 생기지 않음
        //그러니 그 이상은 다 같은 상태로 취급해줘도 상관없음
        int maxAlp = alp, maxCop = cop;
        for(int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }
        
        //dp[i][j] = alp가 i, cop가 j인 상태에 도달하기까지 걸리는 최소 시간
        int[][] dp = new int[maxAlp+1][maxCop+1];
        for(int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[alp][cop] = 0;
        
        //시작 능력치보다 낮아질 순 없으니까 alp, cop부터 바로 시작.
        for(int i = alp; i <= maxAlp; i++) {
            for(int j = cop; j <= maxCop; j++) {
                //아직 한 번도 갱신된 적 없다(도달 불가능하다는 뜻)면 여기서 뻗어나갈 것도 없으니 스킵.
                if(dp[i][j] == Integer.MAX_VALUE) continue;
                
                //도달 가능하면 현재까지의 최소 시간을 cur에 저장
                int cur = dp[i][j];
                
                //혼자 공부로 alp 또는 cop를 1씩 올리는 작업.
                //현재 저장된 값과 (i,j)를 거쳐서 오는 값(cur+1)중 더 작은 쪽으로 갱신.
                if(i+1 <= maxAlp) dp[i+1][j] = Math.min(dp[i+1][j], cur+1);
                if(j+1 <= maxCop) dp[i][j+1] = Math.min(dp[i][j+1], cur+1);
                
                //문제 푸는 방식
                for(int[] p : problems) {
                    int reqAlp = p[0], reqCop = p[1];
                    int gainAlp = p[2], gainCop = p[3], cost = p[4];
                    
                    if(i >= reqAlp && j >= reqCop) {
                        int ni = Math.min(maxAlp, i + gainAlp);
                        int nj = Math.min(maxCop, j + gainCop);
                        
                        if(cur + cost < dp[ni][nj]) dp[ni][nj] = cur + cost;
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}