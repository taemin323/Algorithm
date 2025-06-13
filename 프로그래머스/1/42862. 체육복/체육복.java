/**
 * 2 <= n <= 30
 * 1 <= 도난당한 학생 수 <= n
 * 1 <= 여벌 옷 가져온 학생 수 <= n
 * 여벌 체육복을 가져온 학생이 도난당할 수 있음. 이 때는 여벌 옷을 본인이 입는 경우가 되기 때문에 빌려줄 수 없음.
 * 도난 : -1, 도난x : 0, 여벌 : 1 
 */

import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n+1];
        
        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();
        
        for (int l : lost) lostSet.add(l);
        for (int r : reserve) {
            if (lostSet.contains(r)) {
                lostSet.remove(r); // 본인이 입음
            } else {
                reserveSet.add(r); // 빌려줄 수 있는 사람 저장.
            }
        }
        
        for (int l : lostSet) students[l]--;
        for (int r : reserveSet) students[r]++;
        
        for (int i = 1; i <= n; i++) {
            if (students[i] == -1) {
                if (i > 1 && students[i-1] == 1) {
                    students[i]++;
                    students[i-1]--;
                } else if (i < n && students[i+1] == 1) {
                    students[i]++;
                    students[i+1]--;
                }
            }
        }
        
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if(students[i] >= 0) cnt ++;
        }
        
        return cnt;
    }
}