import java.util.*;
/**
* 40분 -> 실패 / 65분 -> 실패
* dfs로 접근이 쉬워보임.
* 일단 현재 양의 개수(sCnt), 늑대의 개수(wCnt)를 들고 다녀야됨.
* 기저조건을 뭘로 해야하나??
* if(sCnt > wCnt + 1 && info[nextIdx] == 1) -> 갈 수 있음. wCnt+1
* if(info[nextIdx] == 0) -> 갈 수 있음. sCnt+1
* 
* 양의 개수는 유지가 되어야됨.
* 기저조건을 다시 정리해보자
* 1. 양의 개수 <= 늑대의 개수일 땐 무조건 return -> 이거 이미 가면 안되는거라 이건 아님.
* 2. dfs를 타기 전에 양의 개수 <= 늑대의 개수를 체크하고 애초에 가면 안돼.
* 내가 하고 싶은 게 양의 개수는 한번 간 순간 계속 유지하고, 늑대만 최신화를 시키고 싶으니
* 양의 개수는 전역변수로, 늑대의 개수는 매개변수로 들고 다니자.
* 그렇다면 기저조건을 뭘로 할거냐.
* 또 트리를 위아래로 자유롭게 이동하는 걸 어떻게 구현할거냐.
* 
* 내가 막힌 이유 - 일반 DFS처럼 자식 노드로만 내려가려고 해서.
* 다음에 갈 수 있는 노드 목록을 들고 다녀야 됨.
* 어느 시점이든 "내가 지금까지 방문한 노드들의 자식들"은 언제든지 다음에 새로 방문할 후보.
* 
* 1. 현재 노드를 방문 처리한다
* 2. 만약 늑대수 >= 양수가 되면 탐색 종료(기저조건/가지치기)
* 3. 최대 양의 개수를 갱신한다.
* 4. 새로운 다음 방문 후보 목록을 만든다.
*   - 기존 후보 목록 + 현재 노드의 자식 노드들 - 현재 노드 자신
* 5. 새로운 후보 목록에 있는 노드들을 하나씩 순회하며 다음 DFS 호출
*/

class Solution {
    int maxSheep = 0;
    List<Integer>[] tree;
    
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        tree = new ArrayList[n];
        
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            
            tree[parent].add(child);
        }
        
        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);
        
        dfs(0,0,0,nextNodes,info);
        return maxSheep;
    
    }
    
    void dfs(int cur, int sheep, int wolf, List<Integer> nextNodes, int[] info) {
        if(info[cur] == 0) sheep++;
        else wolf++;
        
        if(sheep <= wolf) return;
        
        maxSheep = Math.max(maxSheep, sheep);
        
        List<Integer> newNodes = new ArrayList<>(nextNodes);
        newNodes.remove(Integer.valueOf(cur));
        
        for(int i : tree[cur]) {
            newNodes.add(i);
        }
        
        for(int i : newNodes) {
            dfs(i, sheep, wolf, newNodes, info);
        }
    }
    
}