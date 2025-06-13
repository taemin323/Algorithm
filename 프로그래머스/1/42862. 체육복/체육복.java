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
        int[] students = new int[n+2];
        
        for (int i = 0; i < lost.length; i++) {
            students[lost[i]] = -1;
        }
        
        for (int i = 0; i < reserve.length; i++) {
            if(students[reserve[i]] == -1) {
                students[reserve[i]] = 0;
            } else {
                students[reserve[i]] = 1;
            }
        }

        for (int i = 1; i < students.length; i++) {
            if(students[i] == 1 && students[i-1] == -1) {
                students[i] = 0;
                students[i-1] = 0;
            } else if (students[i] == 1 && students[i+1] == -1) {
                students[i] = 0;
                students[i+1] = 0;
            }
        }
        
        int cnt = 0;
        for (int i = 1; i < students.length-1; i++) {
            if(students[i] >= 0) {
                cnt++;
            }
            
        }
        return cnt;
    }
}