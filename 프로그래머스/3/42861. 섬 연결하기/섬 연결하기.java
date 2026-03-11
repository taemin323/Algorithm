import java.util.*;

class Solution {
    
    private static int[] parent;
    
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        Arrays.sort(costs, (a,b) -> a[2] - b[2]);
        
        int answer = 0;
        int cnt = 0;
        
        for(int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            int price = cost[2];
            
            if(find(a) != find(b)) {
                union(a, b);
                answer += price;
                cnt++;
                
                if(cnt == n - 1) break;
            }
        }
        return answer;
    }
    
    int find(int x) {
        if(parent[x] == x) return x; 
        return parent[x] = find(parent[x]);
    }
    
    void union(int a, int b) {
        int pa = parent[a];
        int pb = parent[b];
        
        if(pa != pb) {
            parent[pb] = pa;
        }
    }
}