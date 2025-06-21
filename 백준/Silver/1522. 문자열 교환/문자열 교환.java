import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		int N = str.length();
		
		
		// a의 개수 = 윈도우 길이 K
		int K = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'a') K++;
		}
		
		// 예외: 모두 a 또는 b
		if (K == 0 || K == N) {
			System.out.println(0);
			return;
		}
		
		// 원형 처리를 위해 문자열 두 번 이어붙이기
		String S = str + str; // 길이는 2N
		
		// 누적합 배열
		int[] prefix = new int[2*N+1];
		for (int i = 0; i < 2 * N; i++) {
			prefix[i + 1] = prefix[i] + (S.charAt(i) == 'b' ? 1 : 0);
		}
		
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int bCnt = prefix[i + K] - prefix[i];
			ans = Math.min(ans, bCnt);
		}
		System.out.println(ans);
	}
}
