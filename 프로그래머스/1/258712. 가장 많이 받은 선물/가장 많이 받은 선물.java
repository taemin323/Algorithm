import java.util.*;
/**
* 선물지수 : 이번 달까지 자신이 친구들에게 준 선물의 수 - 받은 선물의 수
* 기록이 없거나, 주고받은 수가 같다면 -> 선물 지수가 작은 사람이 선물을 하나 줘야함.
* 선물지수까지 같다면 선물을 주고받지 않는다.
* A B : A는 선물을 준 사람. B는 선물을 받은 사람.
*/

class Solution {
    private int n;
    
    public int solution(String[] friends, String[] gifts) {
        n = friends.length;
        int[][] board = new int[n][n];
        
        // 자기 자신은 제외시켜야함
        for(int i = 0; i < n; i++) {
            board[i][i] = -1;
        }
        
        //gifts 파싱
        for(int i = 0; i < gifts.length; i++) {
            String[] info = gifts[i].split(" ");
            int giveP = idx(friends, info[0]);
            int getP = idx(friends, info[1]);
            
            board[giveP][getP]++;
        }
        
        //선물 지수 배열
        int[] giftPoint = new int[n];
        
        for(int i = 0; i < n; i++) {
            int give = 0;
            int get = 0;
            for(int j = 0; j < n; j++) {
                if(board[i][j] != -1) {
                    give += board[i][j];
                }
                
                if(board[j][i] != -1) {
                    get += board[j][i];
                }
            }
            giftPoint[i] = give - get;
        }
        
        int answer = 0;
        for(int i = 0; i < n; i++) {
            int cnt = 0;
            for(int j = 0; j < n; j++) {
                if(board[i][j] == -1) continue;
                
                int a = board[i][j];
                int b = board[j][i];
                
                if(a > b) cnt++;
                else if (a == b || (a == 0 && b == 0)) {
                    int x = giftPoint[i];
                    int y = giftPoint[j];
                    
                    if(x > y) cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }
            
        
        return answer;
    }
    
    private int idx(String[] friends, String person) {
        int result = 0;
        for(int i = 0; i < n; i++) {
            if(friends[i].equals(person)) {
                result = i;
            }
        }
        
        return result;
    }
}