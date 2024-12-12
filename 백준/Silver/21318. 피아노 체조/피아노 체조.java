import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());

		//실수 누적합 배열
		int[] prefixSum = new int[N+1];
		
		int[] levels = new int[N+1];// 악보의 개수 1<=N<=100,000
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			levels[i] = Integer.parseInt(st.nextToken());
			if(levels[i-1] > levels[i]) {
				prefixSum[i] = prefixSum[i-1] + 1;
			} else {
				prefixSum[i] = prefixSum[i-1];
			}
		}// 악보 입력 완료
		
		int Q = Integer.parseInt(br.readLine());// 질문의 개수 1<=Q<= 100,000
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			int cnt = prefixSum[end] - prefixSum[start];
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}//end of main
}//end of class
