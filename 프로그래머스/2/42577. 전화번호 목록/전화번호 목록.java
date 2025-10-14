import java.util.*;
/**
* 1 <= 전화번호부의 길이 <= 1,000,000
* 1 <= 각 전화번호의 길이 <= 20
* 중복된 전화번호 없음
* 즉 번호를 이어붙였을 때 해당하는 번호가 있다면 그 전화번호의 접두사.
* 하나라도 접두사 역할이 있다면 false 반환
* 길이가 짧은 순으로 나열을 하고
* 현재 전화번호의 뒤에 있는 전화번호를 하나씩 체크해나가다가 하나라도 걸리면 false 반환하고 종료
*/

class Solution {
    public boolean solution(String[] phone_book) {
        
        Set<String> set = new HashSet<>();
        for(String number : phone_book) {
            set.add(number);
        }
        
        for(String number : phone_book) {
            for(int i = 1; i < number.length(); i++) {
                if(set.contains(number.substring(0, i))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}