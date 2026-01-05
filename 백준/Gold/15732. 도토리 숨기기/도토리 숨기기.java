import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int K;
	private static long D;
	private static int[][] rules;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		rules = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			rules[i][0] = Integer.parseInt(st.nextToken());
			rules[i][1] = Integer.parseInt(st.nextToken());
			rules[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int left = 1;
		int right = N;
		int answer = N;
		
		while(left <= right) {
			int mid = left + (right - left) / 2;
			
			//1번부터 mid번 상자까지 도토리 총합 계산
			if(calculate(mid) >= D) {
				//도토리 개수가 충분히 담겼다면, 더 앞쪽 상자에서도 가능한지 확인
				answer = mid;
				right = mid -1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(answer);
	}// end of main

	private static long calculate(int boxLimit) {
		long total = 0;
		
		for (int i = 0; i < K; i++) {
			int start = rules[i][0];
			int end = rules[i][1];
			int term = rules[i][2];
			
			// 이번 규칙이 적용되는 실제 끝 번호
			int curEnd = Math.min(end, boxLimit);
			
			if(curEnd >= start) {
				total += (curEnd - start) / term + 1;
			}
		}
		return total;
	}
}// end of class
