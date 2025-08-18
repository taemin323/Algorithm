/**
* 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림하기.
* 1<=fees[0]<=1,439 : 기본 시간(분)
* 0<=fees[1]<=100,000 : 기본 요금(원)
* 1<=fees[2]<=1,439 : 단위 시간(분)
* 0<=fees[3]<=100,000 : 단위 요금(원)
* 
* 1<=records<=1,000 : 시각, 차량번호, 내역 형식의 문자열(공백으로 구분), 시각 기준으로 오름차순 정렬
*/
import java.util.*;

class Solution {
    int basicTime;
    int basicFee;
    int unitTime;
    int unitFee;
    
    public int[] solution(int[] fees, String[] records) {
        basicTime = fees[0];
        basicFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];     
        
        Map<String, Integer> inTime = new HashMap<>();
        Map<String, Integer> total = new HashMap<>();
        
        for(String s : records) {
            String[] info = s.split(" ");
            int time = toMinutes(info[0]);
            String car = info[1];
            String type = info[2];
            
            if("IN".equals(type)) {
                inTime.put(car, time);
            } else {
                int used = time - inTime.get(car);
                total.put(car, total.getOrDefault(car, 0) + used);
                inTime.remove(car);
            }
        }
        
        // 출차 기록이 없는 차량 23:59 출차로 간주
        int limitTime = toMinutes("23:59");
        for(Map.Entry<String, Integer> e : inTime.entrySet()) {
            String car = e.getKey();
            int used = limitTime - e.getValue();
            total.put(car, total.getOrDefault(car, 0) + used);
        }
        
        List<String> cars = new ArrayList<>();
        for(String car : total.keySet()) {
            cars.add(car);
        }
        Collections.sort(cars);
        
        int[] answer = new int[cars.size()];
        for(int i = 0; i < cars.size(); i++) {
            int totalTime = total.get(cars.get(i));
            answer[i] = calculate(totalTime);
        }
        return answer;
    }
    
    public int toMinutes(String hhmm) {
        String[] t = hhmm.split(":");
        return Integer.parseInt(t[0])*60 + Integer.parseInt(t[1]);
    }
    
    public int calculate(int totalTime) {
        if(totalTime <= basicTime) return basicFee;
        int overTime = totalTime - basicTime;
        int units = (overTime + unitTime - 1) / unitTime; //올림처리
        return basicFee + units * unitFee;
    }
}