import java.util.*;
/**
* +, - 2가지 길을 고르면서 가는것.
* 깊이 우선 탐색 타겟 넘버까지 갔다가 되돌아와야함
*/

class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(0, 0, numbers, target);
    }
    
    private int dfs(int idx, int result, int[] numbers, int target) {
        if(idx == numbers.length) {
            return result == target ? 1 : 0;
        }
        
        int withPlus = dfs(idx + 1, result + numbers[idx], numbers, target);
        int withMinus = dfs(idx + 1, result - numbers[idx], numbers, target);
        
        return withPlus + withMinus;
    }
}