import java.util.*;

class Solution {
    int[] parent;
    
    public int solution(int n, int[][] computers) {
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                if(computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for(int i : parent) {
            set.add(find(i));
        }
        
        return set.size();
    }
    
    void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        
        if(pi != pj) {
            parent[pj] = pi;
        }
    }
    
    int find(int i) {
        if(parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }
}