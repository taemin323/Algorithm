import java.util.*;
/**
* 1부터 n까지의 서로 다른 정수 5개
* m번의 시도를 할 수 있음 = ans.length
* 10 <= n <= 30
* 1 <= m <= 10
*/
class Solution {
    private int n, m;
    private int[][] q;
    private int[] ans;
    private boolean[][] inQuery; // inQuery[i][x]: q[i]안에 x가 포함되어 있는가
    private int[] candi; // 조합 후보 리스트
    private int[] pick = new int[5];
    private int answer;
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        this.m = ans.length;
        this.answer = 0;
        
        // ans == 5가 있다면 그것만 정답임. 따라서 1개임
        for(int i = 0; i < m; i++) {
            if(ans[i] == 5) return 1;
        }
        
        // 포함 여부 배열
        inQuery = new boolean[m][n+1];
        for(int i = 0; i < m; i++) {
            for(int v : q[i]) inQuery[i][v] = true;
        }
        
        // ans == 0이면 해당 숫자들은 비밀코드 후보에서 제외
        boolean[] outNumber = new boolean[n+1];
        for(int i = 0; i < m; i++) {
            if(ans[i] == 0) {
                for(int v : q[i]) outNumber[v] = true;
            }
        }
        
        // 후보 목록
        ArrayList<Integer> list = new ArrayList<>();
        for(int x = 1; x <= n; x++) {
            if(!outNumber[x]) list.add(x);
        }
        
        candi = new int[list.size()];
        for(int i = 0; i < list.size(); i++) candi[i] = list.get(i);
        
        // 가능 조합 탐색
        dfs(0,0);
        
        return answer;
    }
    
    private void dfs(int depth, int startIdx) {
        if(depth == 5) {
            for(int i = 0; i < m; i++) {
                int cnt = 0;
                for(int k = 0; k < 5; k++) if(inQuery[i][pick[k]]) cnt++;
                if(cnt != ans[i]) return;
            }
            answer++;
            return;
        }
        
        int left = 5 - depth;
        for(int idx = startIdx; idx <= candi.length - left; idx++) {
            pick[depth] = candi[idx];
            dfs(depth+1, idx+1);
        }
    }
}