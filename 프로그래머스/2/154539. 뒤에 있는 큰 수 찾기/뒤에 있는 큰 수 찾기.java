/**
* 4 <= number <= 1,000,000
* 1 <= numbers[i] <= 1,000,000
*/
import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answers = new int[numbers.length];
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = numbers.length - 1; i >= 0; i--) {
            // 현재 값보다 작거나 같으면 스택에서 제거
            while(!stack.isEmpty() && stack.peek() <= numbers[i]) {
                stack.pop();
            }
            
            // 스택이 비어있다면 큰 수가 없는 것이므로 -1
            if (stack.isEmpty()) {
                answers[i] = -1;
            } else {
                answers[i] = stack.peek();
            }
            
            // 현재 값을 스택에 넣기
            stack.add(numbers[i]);
        }
        
        return answers;
    }
}