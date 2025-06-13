import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Queue<Integer> q = new LinkedList<>();
        q.add(arr[0]);
        
        for (int i = 1; i < arr.length; i++) {
            if(arr[i-1] != arr[i]) {
                q.add(arr[i]);
            }
        }
        
        int[] ans = new int[q.size()];
        
        int i = 0;
        for (Integer queue: q) {
            ans[i++] = queue;
        }

        return ans;
    }
}