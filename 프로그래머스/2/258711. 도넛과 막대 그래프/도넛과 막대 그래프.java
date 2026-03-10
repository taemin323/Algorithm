import java.util.*;

class Solution {
    class Info {
        int inCnt;
        int outCnt;
    }
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer, Info> graph = new HashMap<>();
        
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            // 노드가 처음 등장하면 생성
            if(!graph.containsKey(from)) graph.put(from, new Info());
            if(!graph.containsKey(to)) graph.put(to, new Info());
            
            graph.get(from).outCnt++;
            graph.get(to).inCnt++;
        }
        
        int startNode = -1;
        int donut = 0;
        int bar = 0;
        int eight = 0;
        
        for(int idx : graph.keySet()) {
            Info cur = graph.get(idx);
            
            if(cur.outCnt >= 2 && cur.inCnt == 0) {
                startNode = idx;
            } else if(cur.outCnt == 0) {
                bar++;
            } else if(cur.outCnt == 2 && cur.inCnt >= 2) {
                eight++;
            }
        }
        
        //도넛 갯수 : 전체 - 막대 - 8자
        donut = graph.get(startNode).outCnt - bar - eight;
        
        answer[0] = startNode;
        answer[1] = donut;
        answer[2] = bar;
        answer[3] = eight;
        
        return answer;
    }
}