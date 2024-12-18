import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int K;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());//물품의 수
		K = Integer.parseInt(st.nextToken());//버틸 수 있는 무게
		
		int[] dp = new int[K + 1];//dp[j]는 무게 j일 때 얻을 수 있는 최대 가치
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int W= Integer.parseInt(st.nextToken());//현재 물품의 무게
			int V = Integer.parseInt(st.nextToken());//현재 물품의 가치
			
			for (int j = K; j >=W ; j--) {
				// j 무게를 채웠을 때 최대 가치 계산
				dp[j] = Math.max(dp[j], dp[j-W]+V);
			}
		}
		
		System.out.println(dp[K]);
	}//end of main
}//end of class
