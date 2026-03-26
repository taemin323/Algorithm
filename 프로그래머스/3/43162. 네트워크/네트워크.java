import java.util.*;

class Solution {
    private int[] parent;
    
    public int solution(int n, int[][] computers) {
        
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                else if(computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            set.add(find(i));
        }
        
        return set.size();
    }
    
    private void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        
        if(pi != pj) parent[pj] = pi;
    }
    
    private int find(int x) {
        if(parent[x] == x) return x;
        
        return parent[x] = find(parent[x]);
        
    }
}