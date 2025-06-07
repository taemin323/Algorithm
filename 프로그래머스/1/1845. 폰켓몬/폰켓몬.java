import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int max = nums.length/2;
        
        HashSet<Integer> set = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        
        if (set.size() >= max) {
            return max;
        } else {
            return set.size();
        }
    }
}