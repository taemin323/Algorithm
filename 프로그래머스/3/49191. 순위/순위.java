import java.util.*;

class Solution {
    
    public class Record {
        Set<Integer> win; // 내가 이긴 선수들의 번호
        Set<Integer> lose; // 나에게 이긴 선수들의 번호
        
        public Record() {
            this.win = new HashSet<>();
            this.lose = new HashSet<>();
        }
    }
    
    // 내가 이긴 선수들이 또 이긴 선수들까지 모두 찾아서 losers에 저장
    public Set<Integer> findLoser(Set<Integer> losers, Record[] record, int i) {
        for (Integer player: record[i].win) {// 내가 직접 이긴 사람들
            if(!losers.contains(player)) {// 이미 추가된 사람은 제외
                losers.add(player);
                findLoser(losers, record, player);// 재귀적으로 그 사람도 확인
            }
        }
        return losers;
    }
    
    // 내가 진 선수들이 또 진 선수들까지 모두 찾아서 losers에 저장
    public Set<Integer> findWinner(Set<Integer> winners, Record[] record, int i) {
        for (Integer player: record[i].lose) {// 나에게 직접 이긴 사람들
            if(!winners.contains(player)) {// 이미 추가된 사람은 제외
                winners.add(player);
                findWinner(winners, record, player);// 재귀적으로 그 사람도 확인
            }
        }
        return winners;
    }
    
    // 간접 승패 반영하여 record 업데이트
    public void update(Record[] record) {
        for (int i = 1; i < record.length; i++) {
            // i 선수가 이긴 사람들의 이긴 사람들까지 모두 찾기
            Set<Integer> losers = new HashSet<>();
            findLoser(losers, record, i);
            
            for (Integer player: losers) {
                record[i].win.add(player);// i 선수가 그 사람들도 이긴 셈이므로 추가.
            }
            
            // i 선수가 진 사람들의 이긴 사람들까지 모두 찾기
            Set<Integer> winners = new HashSet<>();
            findWinner(winners, record, i);
            
            for (Integer player: winners) {
                record[i].lose.add(player);
            }
        }
    }
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        Record[] record = new Record[n+1];
        
        for (int i = 1; i <= n; i++) {
            record[i] = new Record();
        }
        
        // 주어진 경기 결과로 직접적인 승패 관계 설정
        for (int i = 0; i < results.length; i++) {
            int winner = results[i][0];
            int loser = results[i][1];
            
            record[winner].win.add(loser);
            record[loser].lose.add(winner);
        }
        
        update(record);
        
        // 각 선수마다 win + lose == n-1인지 확인
        for (int i = 1; i <= n; i++) {
            if (record[i].win.size() + record[i].lose.size() == n-1) {
                answer++;
            }
        }
        
        return answer;
    }
}