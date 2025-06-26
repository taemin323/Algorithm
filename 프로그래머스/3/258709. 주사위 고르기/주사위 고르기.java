import java.util.*;

/**
* 2 <= dice의 길이 (n) <= 10
* n은 2의 배수
* dice[i]는 i+1번 주사위에 쓰인 6개의 수를 담고 있다.
* 1 <= dice[i]의 원소 <= 100
*/
class Solution {
    static int len;
    static int[][] allDice;
    static boolean[] visited;
    static long maxWin = 0;
    static int[] ans;
    
    public int[] solution(int[][] dice) {
        len = dice.length;
        allDice = dice;
        visited = new boolean[len];
        ans = new int[len / 2];

        selectDice(0, 0);

        return ans;
    }
    
    // 주사위 조합 뽑기
    private static void selectDice(int start, int depth) {
        if (depth == len/ 2) {
            int winCnt = countWin();
            
            if (winCnt > maxWin) {
                maxWin = winCnt;
                
                int k = 0;
                for (int j = 0; j < visited.length; j++) {
                    if (visited[j]) {
                        ans[k++] = j + 1;
                    }
                }
            }
            return;
        }
        
        for (int i = start; i < len; i++) {
            visited[i] = true;
            selectDice(i + 1, depth + 1);
            visited[i] = false;
        }
    }
    
    // 현재 visited 상태로 A, B 승리 횟수 계산
    private static int countWin() {
        //a와 b의 가능한 합계를 리스트로 생성
        List<Integer> aSum = makeSum(true);
        List<Integer> bSum = makeSum(false);
        
        // b의 합계를 정렬
        Collections.sort(bSum);
        
        int winCnt = 0;
        
        for (int sum: aSum) {
            // a팀 각 합에 대해 b팀보다 작은 합 개수 누적
            int cnt = lowerBound(bSum, sum);
            winCnt += cnt;
        }
        return winCnt;
    }
    
    // 선택한 주사위로 가능한 모든 합 생성
    private static List<Integer> makeSum(boolean isA) {
        // 방문 배열 기준으로 A, B 주사위 인덱스 수집
        List<int[]> diceList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (visited[i] == isA) {
                diceList.add(allDice[i]);
            }
        }
        
        // 초기 합 리스트
        List<Integer> sums = new ArrayList<>();
        sums.add(0);
        
        // 각 주사위 배열마다 기존 합에 주사위 값을 더해 모든 조합 확장.
        for (int[] dice: diceList) {
            List<Integer> newSums = new ArrayList<>();
            for (int sum: sums) {
                for (int num: dice) {
                    newSums.add(sum + num);
                }
            }
            sums = newSums;
        }
        
        return sums;
    }
    
    // 정렬된 리스트에서 target 미만 원소 개수를 이분 탐색으로 반환
    private static int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();
    
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left; // target보다 작은 값의 개수
    }
    
}