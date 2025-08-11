/**
* 2 <= n <= 100 송전탑의 개수
* wires의 원소 v1, v2는 서로 연결되어 있다는 의미. 1 <= v1 <= v2 <= n
*/
import java.util.*;

class Solution {
    List<List<Integer>> tree;
    int N;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        N = n;
        
        tree = new ArrayList<>();
        
        for(int i = 0; i <= n; i++) {
            tree.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < wires.length; i++) {
            //송전탑 관계 구현
            tree.get(wires[i][0]).add(wires[i][1]);
            tree.get(wires[i][1]).add(wires[i][0]);
        }
        
        for(int i = 0; i < wires.length; i++) {
            int firstGroup = bfs(i, wires);
            int secondGroup = n - firstGroup;
            answer = Math.min(answer, Math.abs(firstGroup - secondGroup));
            
        }     
    
        return answer;
    }
    
    public int bfs(int idx, int[][] wires) {
        // 해당 인덱스 삭제(전력망 끊기)
        tree.get(wires[idx][0]).remove(Integer.valueOf(wires[idx][1]));
        tree.get(wires[idx][1]).remove(Integer.valueOf(wires[idx][0]));
        
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        
        q.add(wires[idx][0]);
        visited[wires[idx][0]] = true;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            cnt++;
            
            for(int next : tree.get(cur)) {
                if(!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        tree.get(wires[idx][0]).add(wires[idx][1]);
        tree.get(wires[idx][1]).add(wires[idx][0]);
        
        return cnt;
    }
}