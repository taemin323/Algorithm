import java.util.*;
/**
* i가 짝수일 때 dp[i] = dp[i-2] arr[i-1] arr[i]
*/

class Solution {
    public int solution(String arr[]) {
        int n = (arr.length + 1) / 2;

        int[] nums = new int[n];
        char[] ops = new char[n-1];
        
        for(int i = 0; i < arr.length; i++) {
            if(i % 2 == 0) nums[i/2] = Integer.parseInt(arr[i]);
            else ops[i/2] = arr[i].charAt(0);
        }
        
        //dpMax[i][j] = i번째 숫자부터 j번째 숫자까지 계산해서 만들 수 있는 최댓값
        //dpMin[i][j] = 최솟값
        int[][] dpMax = new int[n][n];
        int[][] dpMin = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            dpMax[i][i] = nums[i];
            dpMin[i][i] = nums[i];
        }
        
        // 몇 개짜리 구간을 채우는 차례인가.작은 구간부터 큰 구간 순서로 채워야 하니까,
        // len을 1부터 n까지 늘려가는것.(근데 len=1은 이미 위에서 다 채웠으니, 2부터 시작)
        for(int len = 2; len <= n; len++) {
            // len이 정해지면, 그 길이의 구간이 배열에서 어디부터 시작할 수 있는지를 i로 돌려야함.
            // n=5, len=3일 때 i=3이면 [3,4,5]이므로 i = 0부터 2까지 가능. 즉 n-len까지만.
            for(int i = 0; i <= n - len; i++) {
                //구간의 끝 인덱스
                int j = i+len-1;
                
                dpMax[i][j] = Integer.MIN_VALUE;
                dpMin[i][j] = Integer.MAX_VALUE;
                
                //k를 i부터 j-1까지 돌면서 (i ~ k)와 (k+1 ~ j) 두 구간을 연산
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