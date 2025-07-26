import java.util.*;
/**
* 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버 만들기.
*/
class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, target, 0);
        
    }
    
    private int dfs(int[] numbers, int idx, int target, int result) {
        if (idx == numbers.length) {
            return result == target ? 1 : 0;
        }
        
        // 현재 숫자를 +로 쓰는 경우와 -로 쓰는 경우를 합산
        int withPlus = dfs(numbers, idx + 1, target, result + numbers[idx]);
        int withMinus = dfs(numbers, idx + 1, target, result - numbers[idx]);
        return withPlus + withMinus;
    }
}