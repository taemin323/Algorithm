import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
    
        for (int i = 0; i < commands.length; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1] - 1;
            int target = commands[i][2] - 1;
            int[] command = new int[end - start + 1];
            
            for (int j = start; j <= end; j++) {
                command[j-start] = array[j];
            }
            
            Arrays.sort(command);
            
            answer[i] = command[target];
        }
        return answer;
    }
}