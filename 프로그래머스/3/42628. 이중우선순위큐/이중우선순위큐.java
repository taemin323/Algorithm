import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        for (String op : operations) {
            String[] parts = op.split(" ");
            String cmd = parts[0];
            int num = Integer.parseInt(parts[1]);

            if (cmd.equals("I")) {
                minPQ.offer(num);
                maxPQ.offer(num);
            } 
            else if (cmd.equals("D") && !minPQ.isEmpty()) {
                if (num == 1) { // 최대값 삭제
                    int max = maxPQ.poll();
                    minPQ.remove(max);
                } else {       // 최소값 삭제
                    int min = minPQ.poll();
                    maxPQ.remove(min);
                }
            }
        }

        if (minPQ.isEmpty()) return new int[]{0, 0};
        return new int[]{maxPQ.peek(), minPQ.peek()};
    }
}
