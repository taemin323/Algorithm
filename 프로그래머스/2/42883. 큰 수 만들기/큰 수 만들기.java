import java.util.*;

class Solution {
    public String solution(String number, int k) {
        
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < number.length(); i++) {
            char cur = number.charAt(i);
            
            while(!stack.isEmpty() && k > 0 && stack.peek() < cur) {
                stack.pop();
                k--;
            }
            
            stack.push(cur);
        }
        
        // k가 남아있다면 뒤에서 제거
        while(k > 0) {
            stack.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        for(char c : stack) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}