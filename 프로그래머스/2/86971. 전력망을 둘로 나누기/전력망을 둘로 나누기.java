import java.util.*;

class Solution {
    int N, min = Integer.MAX_VALUE;
    List<List<Integer>> g;
    boolean[] vst;

    int dfs(int n, int parent) {
        int size = 1;
        for (int next : g.get(n)) {
            if (next == parent) continue;      // 트리라 parent로만 거슬러 올라감 방지
            int sub = dfs(next, n);            // 자식 서브트리 크기
            min = Math.min(min, Math.abs(sub - (N - sub))); // 간선 (n, next) 끊기
            size += sub;
        }
        return size;
    }

    public int solution(int n, int[][] wires) {
        N = n;
        g = new ArrayList<>();
        for (int i = 0; i <= n; i++) g.add(new ArrayList<>());
        for (int[] w : wires) {
            g.get(w[0]).add(w[1]);
            g.get(w[1]).add(w[0]);
        }
        dfs(1, 0);
        return min;
    }
}
