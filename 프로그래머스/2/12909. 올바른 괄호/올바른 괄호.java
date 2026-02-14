import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int n = s.length();
        
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if(c == '(') {
                stack.push(c);
            } else {
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        
        if(!stack.isEmpty()) return false;
        
        return answer;
    }
}