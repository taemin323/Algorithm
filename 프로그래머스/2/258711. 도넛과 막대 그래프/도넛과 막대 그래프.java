import java.util.*;

class Solution {
    class Node {
        int inCnt = 0;
        int outCnt = 0;
        
    }
    
    public int[] solution(int[][] edges) {
        Map<Integer, Node> graph = new HashMap<>();
        
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            // 노드가 처음 등장하면 생성
            if(!graph.containsKey(from)) graph.put(from, new Node());
            if(!graph.containsKey(to)) graph.put(to, new Node());
            
            graph.get(from).outCnt++;
            graph.get(to).inCnt++;
        }
        
        int startNode = -1;
        int donut = 0;
        int bar = 0;
        int eight = 0;
        
        for(int i : graph.keySet()) {
            Node node = graph.get(i);
            
            if(node.outCnt >= 2 && node.inCnt == 0) {
                startNode = i;
            } else if(node.outCnt == 0) {
                bar++;
            } else if(node.outCnt == 2 && node.inCnt >= 2) {
                eight++;
            }
        }
        
        // 도넛 갯수 : 전체 - 막대 - 8자
        donut = graph.get(startNode).outCnt - bar - eight;
        
        int[] answer = {startNode, donut, bar, eight};
        
        return answer;
    }
}