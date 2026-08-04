import java.util.*;
/**
* 40분 -> 실패
* 결국은 모든 경우의 수는 괄호의 개수와 위치로 결정됨.
* 모든 연산은 A +or- B라고 할 수 있음.
* i%2 == 0 -> 숫자 / != 0 -> 연산자
* dp[i] = i번째까지 계산했을 때의 최댓값
* +인 경우 -> dp[i] = dp[i-1] + B가 될 수 있는 것 중 최댓값;
* -인 경우 -> dp[i] = dp[i-1] - B가 될 수 있는 것 중 최솟값;
*
* 위 방식처럼 진행하면 안되는 이유 -> i번째까지 계산했을 때의 최댓값으로 정의하면
* 현재까지의 부분식은 이미 하나의 값으로 확정되어 있다는 전제가 깔림
* 앞쪽 구간의 최댓값/최솟값만 갖고는 그 구간 내부의 어느 지점부터 빼기 그룹이 시작됐는지 알 수 없음.
* 
* dpMax[i][j] : numbers[i]부터 numbers[j]까지를 최적으로 괄호 쳤을 때 만들 수 있는 최댓값
* dpMin[i][j] : numbers[i]부터 numbers[j]까지를 최적으로 괄호 쳤을 때 만들 수 있는 최솟값
* 구간[i,j] 사이의 어떤 연산자의 위치 k를 기준으로 나눠보면
* +인 경우 -> dpMax[i][j]의 후보 : dpMax[i][k] + dpMax[k+1][j] / dpMin[i][j]의 후보 : dpMin[i][k] + dpMin[k+1][j]
* -인 경우 -> dpMax[i][j]의 후보 : dpMax[i][k] - dpMin[k+1][j] / dpMin[i][j]의 후보 : dpMin[i][k] - dpMax[k+1][j]
* 이 모든 k에 대해 나온 후보 중에서 dpMax[i][j]는 최댓값, dpMin[i][j]는 최솟값을 취함.
* dpMax[0][n-1]이 정답.
*/

class Solution {
    public int solution(String arr[]) {
        int n = (arr.length + 1) / 2;
        
        int[] numbers = new int[n];
        char[] ops = new char[n-1];
        
        for(int i = 0; i < arr.length; i++) {
            if(i % 2 == 0) numbers[i/2] = Integer.parseInt(arr[i]);
            else ops[i/2] = arr[i].charAt(0);
        }
        
        int[][] dpMax = new int[n][n];
        int[][] dpMin = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            dpMax[i][i] = numbers[i];
            dpMin[i][i] = numbers[i];
        }
        
        //길이는 위에서 자기 자신은 초기화해놧으니 2부터.
        for(int len = 2; len <= n; len++) {
            //n = 5, len=3일 때 i = 3이면 [3,4,5]이므로 i = 0부터 2까지 가능. 따라서 n-len까지만
            for(int i = 0; i <= n - len; i++) {
                //구간의 끝 인덱스
                int j = i + len - 1;
                
                dpMax[i][j] = Integer.MIN_VALUE;
                dpMin[i][j] = Integer.MAX_VALUE;
                
                //k를 i부터 j-1까지 돌면서 (i~k), (k+1, j) 두 구간을 연산
                for(int k = i; k <= j-1; k++) {
                    if(ops[k] == '+') {
                        dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k] + dpMax[k+1][j]);
                        dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i][k] + dpMin[k+1][j]);
                    } else {
                        dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k] - dpMin[k+1][j]);
                        dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i][k] - dpMax[k+1][j]);
                    }
                }
            }
        }
        
        
        return dpMax[0][n-1];
    }
}