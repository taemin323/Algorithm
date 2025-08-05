import java.util.*;
/**
* 각 노드에 있는 양 또는 늑대 정보: info
* 2진 트리의 각 노드들의 연결 관계: edges
* 2 <= info <= 17
* sheep: 0, info[0] = 0;
* wolf: 1
*/

class Solution {
    int maxSheep;
    int[] newInfo;
    int[][] newEdges;
    int N;
    
    public int solution(int[] info, int[][] edges) {
        newInfo = info;
        newEdges = edges;
        N = info.length;
        boolean[] visited = new boolean[N];
        
        dfs(0,0,0,visited);
        
        return maxSheep;
    }
    
    public void dfs(int idx, int sheepCnt, int wolfCnt, boolean[] visited) {
        visited[idx] = true;
        
        if(newInfo[idx] == 0) {
            sheepCnt++;
            if(sheepCnt > maxSheep) {
                maxSheep = sheepCnt;
            }
        } else {
            wolfCnt++;
        }
        
        if(sheepCnt <= wolfCnt) {
            return;
        }
        
        for(int[] edge : newEdges) {
            if(visited[edge[0]] && !visited[edge[1]]) {
                boolean[] nextVisited = new boolean[N];
                for(int i = 0; i < visited.length; i++) {
                    nextVisited[i] = visited[i];
                }
                dfs(edge[1], sheepCnt, wolfCnt, nextVisited);
            }
        }
    }
    
}