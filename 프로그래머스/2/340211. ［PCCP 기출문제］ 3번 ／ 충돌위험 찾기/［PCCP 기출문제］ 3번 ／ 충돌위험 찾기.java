/**
* 다음 포인트로 이동 시 항상 최단 경로로 이동. 최단 경로가 여러 가지일 경우, r좌표가 변하는 이동을 c좌표가 변하는 이동보다 먼저.
* 2 <= points.length <= 100
* 1 <= r <= 100
* 1 <= c <= 100
* 2 <= routes.length <= 100
* 2 <= routes[i].length <= 100
* routes[i][j]는 i+1번째 로봇이 j+1번째로 방문하는 포인트 번호.
*/
import java.util.*;

class Solution {
    static class Info {
        int r, c;
        
        Info(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        List<List<Info>> pathes = new ArrayList<>();
        
        // 로봇별 경로 계산
        for(int i = 0; i < routes.length; i++) {
            List<Info> path = new ArrayList<>();
            
            int startIdx = routes[i][0] - 1;
            int startR = points[startIdx][0] - 1;
            int startC = points[startIdx][1] - 1;
            path.add(new Info(startR, startC));
            
            for(int j = 1; j < routes[i].length; j++) {
                int pointIdx = routes[i][j]-1;
                int pointR = points[pointIdx][0]-1;
                int pointC = points[pointIdx][1]-1;
                
                // r 방향 이동
                while(startR != pointR) {
                    if(startR < pointR) startR++;
                    else startR--;
                    path.add(new Info(startR, startC));
                }
                
                // c 방향 이동
                while(startC != pointC) {
                    if(startC < pointC) startC++;
                    else startC--;
                    path.add(new Info(startR, startC));
                }
            }
            pathes.add(path);
        }
        
        // 최대 경과 시간 구하기
        int maxTime = 0;
        for(List<Info> path : pathes) {
            maxTime = Math.max(maxTime, path.size());
        }
        
        // 초별 충돌 검사
        int ans = 0;
        for(int t = 0; t < maxTime; t++) {
            Map<Integer, Integer> cnt = new HashMap<>();
            //각 로봇의 t초 위치 카운트
            for(List<Info> path : pathes) {
                // 이미 운송이 끝난 로봇은 더 이상 물류 센터에 없음
                if(t >= path.size()) continue;
                
                Info pos = path.get(t);
                // (r,c) 좌표를 하나의 정수 key로 변환
                int key = pos.r * 200 + pos.c;
                cnt.put(key, cnt.getOrDefault(key, 0) + 1);
            }
            
            // 충돌 발생
            for(int v : cnt.values()) {
                if(v > 1) {
                    ans++;
                }
            }
        }
        return ans;
    }
}