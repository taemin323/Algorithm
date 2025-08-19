/**
* 1 <= k <= 1,000,000,000,000
* 1 <= room_number <= 200,000
*/
import java.util.*;

class Solution {
    Map<Long, Long> map;
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        map = new HashMap<>();
        
        for(int i = 0; i < room_number.length; i++) {
            answer[i] = checkRoom(room_number[i]);
        }
        
        return answer;
    }
    
    public long checkRoom(long roomNum) {
        List<Long> path = new ArrayList<>();
        while(true) {
            Long newRoomNum = map.get(roomNum);
            if(newRoomNum == null) {
                map.put(roomNum, roomNum+1);
                //경로 압축
                for(long p : path) {
                    map.put(p, roomNum+1);
                }
                return roomNum;
            }
            path.add(roomNum);
            roomNum = newRoomNum;
        }
    }
}