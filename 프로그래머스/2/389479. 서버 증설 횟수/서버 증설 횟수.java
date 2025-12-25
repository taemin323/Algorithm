import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int servers = 0;
        Queue<int[]> q = new LinkedList<>();
        
        for(int i = 0; i < players.length; i++) {
            int curPlayers = players[i];
            int need = curPlayers / m;
            
            if(!q.isEmpty()) {
                int[] cur = q.peek();
                
                if(cur[0] == i) {
                    servers -= cur[1];
                    q.poll();
                }
            }
          
            if(need > servers) {
                int add = need - servers;
                answer += add;
                servers += add;
                q.add(new int[] {i+k, add});
            }
            
            
        }
        return answer;
    }
}