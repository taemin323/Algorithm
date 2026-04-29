import java.util.*;

class Solution {
    boolean[] visited;
    Stack<String> stack = new Stack<>();
    boolean flag;
    
    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length+1];
        visited = new boolean[tickets.length];
        stack.push("ICN");
        
        //사전순 정렬
        Arrays.sort(tickets, (a,b) -> a[1].compareTo(b[1]));
        
        dfs("ICN", tickets, 0);
        return stack.stream().toArray(String[]::new);
    }
    
    void dfs(String cur, String[][] tickets, int depth) {
        if(flag) return;
        
        if(depth == tickets.length) {
            flag = true;
            return;
        }
        
        
        for(int i = 0; i < tickets.length; i++) {
            if(!visited[i] && tickets[i][0].equals(cur)) {
                visited[i] = true;
                stack.push(tickets[i][1]);
                dfs(tickets[i][1], tickets, depth+1);
                
                if(flag) return;
                visited[i] = false;
                stack.pop();
            }
        }
    }
}