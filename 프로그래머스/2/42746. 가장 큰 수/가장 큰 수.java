import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] answer = Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .toArray(String[]::new);
        
        Arrays.sort(answer, (a, b) -> (b + a).compareTo(a+b));
        
        if (answer[0].equals("0")) return "0";
        
        return String.join("", answer);
    }
}