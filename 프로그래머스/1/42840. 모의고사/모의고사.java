import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] answers) {
        int[] supo1 = {1, 2, 3, 4, 5};
        int[] supo2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] supo3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] scores = {0,0,0};

        for (int i = 0; i < answers.length; i++) {
            if(answers[i] == supo1[i % 5]) scores[0]++;
            if(answers[i] == supo2[i % 8]) scores[1]++;
            if(answers[i] == supo3[i % 10]) scores[2]++;
        }

        int max = Math.max(scores[0], Math.max(scores[1], scores[2]));

        return IntStream.range(0,3)
                        .filter(i -> scores[i] == max)
                        .map(i -> i+1)
                        .toArray();
    }
}