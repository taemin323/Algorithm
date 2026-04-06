import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] answers) {
        int[] a = {1,2,3,4,5};
        int[] b = {2,1,2,3,2,4,2,5};
        int[] c = {3,3,1,1,2,2,4,4,5,5};
        int[] scores = new int[4];
        
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == a[i % 5]) scores[1]++;
            if(answers[i] == b[i % 8]) scores[2]++;
            if(answers[i] == c[i % 10]) scores[3]++;
        }
        
        int maxCnt = Math.max(scores[1], Math.max(scores[2], scores[3]));
        
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <=3; i++) {
            if(maxCnt == scores[i]) list.add(i);
        }
        
        // return list.stream().mapToInt(i -> i).toArray();
        return IntStream.range(1,4)
                        .filter(i -> scores[i] == maxCnt)
                        .toArray();
    }
}