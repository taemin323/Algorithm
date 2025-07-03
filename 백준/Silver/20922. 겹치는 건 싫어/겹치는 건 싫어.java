import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1 <= N <= 200,000
 * 1 <= K <= 100
 * 1 <= a <= 100,000 -> 배열 안의 값.
 */

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());// 수열의 길이
		int K = Integer.parseInt(st.nextToken());// K개 이하로 중복 가능.
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//입력 완료
		
		int maxLen = Integer.MIN_VALUE;
		int start = 0;
		int end = 0;
		int[] counts = new int[100001];
		
		while(end < N) {
			if (counts[arr[end]] == K) {
				counts[arr[start]]--;
				start++;
			} else {
				counts[arr[end]]++;
				end++;
			}
			
			maxLen = Math.max(maxLen, end - start);
		}
		
		System.out.println(maxLen);
	}//end of main
}//end of class
