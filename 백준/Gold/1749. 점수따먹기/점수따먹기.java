import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 1 < N < 200
		int M = Integer.parseInt(st.nextToken()); // 1 < M < 200

		int[][] arr = new int[N + 1][M + 1];
		

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		} // 입력 완료
		
		int maxPrefix = Integer.MIN_VALUE;
		
		// 2차원 카데인 알고리즘(1차원에서 최대 부분합을 확장)
		for (int r1 = 1; r1 <= N; r1++) {
			// temp 배열을 사용하여 행 간의 부분합을 계산.
			int[] temp = new int[M+1];
			
			for (int r2 = r1; r2 <= N; r2++) {
				// r1부터 r2까지 행의 부분합을 구함.
				for (int c = 1; c <= M; c++) {
					temp[c] += arr[r2][c];
				}
				
				// 여기서 카데인 알고리즘을 쓴다. 위에서 1차원 배열화를 했으니까.
				int currentSum = 0;
				int maxSum = Integer.MIN_VALUE;
				for (int c = 1; c <= M; c++) {
					currentSum = Math.max(temp[c], currentSum + temp[c]);
					maxSum = Math.max(maxSum, currentSum);
				}
				maxPrefix = Math.max(maxPrefix, maxSum);
			}
		}
		
		System.out.println(maxPrefix);
	}// end of main

}// end of class
