import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] info = new int[N];
		int[] ans = new int[N];
		boolean[] visited = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) {
			info[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		for (int i = 0; i < N; i++) {
			int loc = info[i];
			int tmp = 0;
			int cnt = 0;

			while (tmp <= loc) {
				if (ans[cnt] != 0) {
					cnt++;
					continue;
				}
				cnt++;
				tmp++;
			}
			ans[cnt-1] = i + 1;
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb.toString());
	}// end of main
}// end of class
