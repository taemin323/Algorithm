import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        
        int verticalMove = 0;
        
        for (int i = 0; i < name.length(); i++) {
            verticalMove += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
        }
        
        // 오른쪽으로만 가는 이동 거리
        int move = name.length() - 1;
        
        for (int i = 0; i < name.length(); i++) {
            int next = i + 1;
            
            // next부터 연속된 A를 건너뜀
            while (next < name.length() && name.charAt(next) == 'A') {
                next++;
            }
            
            // 오른쪽 갔다가 되돌아오기
            move = Math.min(move, i * 2 + name.length() - next);
            
            // 왼쪽으로 갔다가 되돌아오기
            move = Math.min(move, (name.length() - next) * 2 + i);
        }
        answer = verticalMove + move;
        return answer;
    }
}