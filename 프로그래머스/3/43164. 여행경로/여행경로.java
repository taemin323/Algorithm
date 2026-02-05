import java.util.*;

class Solution {
    private boolean[] visited;
    private String[] answer;
    private boolean flag = false;
    
    public String[] solution(String[][] tickets) {
        //티켓 자체를 도착지 기준으로 정렬
        Arrays.sort(tickets, (a,b) -> a[1].compareTo(b[1]));
        
        visited = new boolean[tickets.length];
        String[] path = new String[tickets.length+1];
        path[0] = "ICN";
        
        dfs("ICN", tickets, path, 0);
        
        return answer;
    }
    
    private void dfs(String cur, String[][] tickets, String[] path, int cnt) {
        if(flag) return;
        
        if(cnt == tickets.length) {
            answer = path.clone();
            flag = true;
            return;
        }
        
        for(int i = 0; i < tickets.length; i++) {
            if(!visited[i] && tickets[i][0].equals(cur)) {
                visited[i] = true;
                path[cnt+1] = tickets[i][1];
                
                dfs(tickets[i][1], tickets, path, cnt+1);
                
                visited[i] = false;
            }
        }
    }
}