/**
 * 산성: 1 ~ 1,000,000,000
 * 알칼리성: -1 ~ -1,000,000,000
 * 두 용액을 혼합해서 0에 가장 가까운 용액 만들기
 * 2 <= N <= 100,000
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		int min = Integer.MAX_VALUE;
		int left = 0;
		int right = arr.length - 1;
		
		int ansL = 0;
		int ansR = 0;
		
		while(left < right) {
			int curSum = arr[left] + arr[right];
			int abs = Math.abs(curSum);
			
			if(min > abs) {
				min = Math.min(min, abs);
				ansL = arr[left];
				ansR = arr[right];
				if(min == 0) break;
			}
			
			if(curSum < 0) left++;
			else right--;
		}
		
		System.out.println(ansL + " " + ansR);
	}// end of main
}//end of class
