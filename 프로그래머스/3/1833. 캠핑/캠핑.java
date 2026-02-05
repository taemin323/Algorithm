import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;
        
        //좌표 압출을 위해
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        
        for(int[] d: data) {
            xList.add(d[0]);
            yList.add(d[1]);
        }
        
        List<Integer> uniqueX = new ArrayList<>(new TreeSet<>(xList));
        List<Integer> uniqueY = new ArrayList<>(new TreeSet<>(yList));
        
        //2차원 배열에 점찍기
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            data[i][0] = uniqueX.indexOf(data[i][0]);
            data[i][1] = uniqueY.indexOf(data[i][1]);
            
            dp[data[i][0]][data[i][1]] = 1;
        }
        
        //2차원 누적합 계산
        int[][] sum = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                // sum[i][j]는 map의 (i-1, j-1) 위치까지의 사각형 합을 의미함
                sum[i][j] = dp[i-1][j-1] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
            }
        }
        
        //모든 쌍 검사 x축으로 정렬
        Arrays.sort(data, (o1,o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                if(data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;
                
                int x1 = Math.min(data[i][0], data[j][0]);
                int x2 = Math.max(data[i][0], data[j][0]);
                int y1 = Math.min(data[i][1], data[j][1]);
                int y2 = Math.max(data[i][1], data[j][1]);
                
                if(x1 == x2 || y1 == y2) continue;
                
                int cnt = sum[x2][y2] - sum[x1 + 1][y2] - sum[x2][y1 + 1] + sum[x1 + 1][y1 + 1];
                
                if(cnt == 0) answer++;
            }
        }
        
        return answer;
    }
}