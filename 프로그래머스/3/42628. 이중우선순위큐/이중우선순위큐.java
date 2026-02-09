import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int n = operations.length;
        int[] answer = new int[2];
        
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0; i < n; i++) {
            String[] parts = operations[i].split(" ");
            String op = parts[0];
            int num = Integer.parseInt(parts[1]);
            
            if(op.equals("I")) {
                maxPQ.offer(num);
                minPQ.offer(num);
            } else if (op.equals("D") && !maxPQ.isEmpty()) {
                if(num == 1) {
                    int max = maxPQ.poll();
                    minPQ.remove(max);
                } else {
                    int min = minPQ.poll();
                    maxPQ.remove(min);
                }
            } else {
                continue;
            }
        }
        
        if(maxPQ.isEmpty()) {
            return new int[]{0,0};
        }
        
        answer[0] = maxPQ.poll();
        answer[1] = minPQ.poll();
        
        return answer;
    }
}