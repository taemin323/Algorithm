import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int cntA = 0;
        int cntB = 0;
        int cntC = 0;
        
        int n = answers.length;
        
        int[] b = {2,1,2,3,2,4,2,5};
        int[] c = {3,3,1,1,2,2,4,4,5,5};
        
        // a 계산
        for(int i = 0; i < n; i++) {
            if(answers[i] == (i%5) + 1) cntA++;
            
            if(answers[i] == b[(i % 8)]) cntB++;
            
            if(answers[i] == c[(i % 10 )]) cntC++;
        }
        
        int[] arr = new int[4];
        arr[1] = cntA;
        arr[2] = cntB;
        arr[3] = cntC;

        int max = Math.max(cntA, Math.max(cntB, cntC));
        
        
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 3; i++) {
            if(arr[i] == max) {
                list.add(i);
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}