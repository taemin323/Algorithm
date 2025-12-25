import java.util.*;
// a ~ z : 1 ~ 26
// aa ~ az : 27 ~ 52
// ba ~ bz : 53 ~ 78
// aa ab ac ad ae af ag ah
//원래는 30이 ad, 그치만 앞에 3개 사라지고. 27이 됨. 그래서 뒤로 3개를 가야함. 그 길에 ae가 있어서 건너뛰면 ah
class Solution {
    public String solution(long n, String[] bans) {
        // 삭제된 주문들을 숫자로 변환하여 정렬
        List<Long> banNums = new ArrayList<>();
        for(String s : bans) {
            banNums.add(stringToLong(s));
        }
        Collections.sort(banNums);
        
        // 이진 탐색으로 n번째 주문의 원래 번호 찾기
        long left = 1;
        long right = 2_000_000_000_000_000_000L;
        long answerNum = right;
        
        while(left <= right) {
            long mid = left + (right - left) / 2;
            
            // mid보다 작거나 같은 삭제된 주문의 개수 계산
            long countBans = countLessEqual(banNums, mid);
            
            if (mid - countBans >= n) {
                answerNum = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        // 숫자를 다시 문자열로 변환
        return longToString(answerNum);
    }
    
    private long stringToLong(String s) {
        long result = 0;
        for(int i = 0; i < s.length(); i++) {
            result = result * 26 + (s.charAt(i) - 'a' + 1);
        }
        return result;
    }
    
    private String longToString(long n) {
        StringBuilder sb = new StringBuilder();
        
        while(n > 0) {
            n--;
            sb.append((char) ('a' + (n % 26)));
            n /= 26;
        }
        return sb.reverse().toString();
    }
    
    // 정렬된 리스트에서 target보다 작거나 같은 값의 개수 찾기
    private long countLessEqual(List<Long> list, long target) {
        int left = 0, right = list.size() - 1;
        int cnt = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
           
            if(list.get(mid) <= target) {
                cnt = mid + 1;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return cnt;
    }
}