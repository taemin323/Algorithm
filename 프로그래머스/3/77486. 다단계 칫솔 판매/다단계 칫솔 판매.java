import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        int m = seller.length;
        int[] answer = new int[n];
                        
        Map<String, String> relations = new HashMap<>();
        Map<String, Integer> moneys = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            String person = enroll[i];
            String parent = referral[i];
            
            relations.put(person, parent);
            moneys.put(person, 0);
        }
        
        
        for(int i = 0; i < m; i++) {
            String cur = seller[i];
            int money = amount[i] * 100;
            
            while(!cur.equals("-") && money > 0) {
                int give = money / 10;
                moneys.put(cur, moneys.get(cur) + (money - give));
                cur = relations.get(cur);
                money = give;
            }
        }
        
        for(int i = 0; i < n; i++) {
            String person = enroll[i];
            answer[i] = moneys.get(person);
        }
        return answer;
    }
}