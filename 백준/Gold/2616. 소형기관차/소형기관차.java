import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());// 객차의 수 <= 50,000
		
		int[] train = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 1; i <= N; i++) {
			train[i] = train[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());//소형이 최대로 끌 수 있는 객차 수
		
		int[][] dp = new int[4][N+1];
		
		for (int i = 1; i < 4; i++) {
			for (int j = i * M; j <= N; j++) {
				dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-M] + train[j] - train[j-M]);
			}
		}
		
		System.out.println(dp[3][N]);
	}//end of main
}//end of class
