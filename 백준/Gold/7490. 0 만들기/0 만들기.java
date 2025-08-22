import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * + : 더하기
 * - : 빼기
 * 공백 : 숫자를 이어 붙이는 것
 */
public class Main {
	private static int N;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());//1~N까지 오름차순 수열
			dfs(1, "1");
			sb.append("\n");
		}// end of tc
		System.out.println(sb.toString());
	}// end of main

	private static void dfs(int now, String str) {
		if(now == N) {
			if(calculate(str) == 0) {
				sb.append(str).append("\n");
			}
			return;
		}
		int next = now + 1;
		//공백
		dfs(next, str + " " + next);
		// + 
		dfs(next, str + "+" + next);
		// - 
		dfs(next, str + "-" + next);
	}
	
	// 수식 계산
	private static int calculate(String str) {
		// 공백 제거
		String noSpace = str.replace(" ", "");
		
		int sum = 0;
		int num = 0;
		char sign = '+';
		
		for (int i = 0; i < noSpace.length(); i++) {
			char c = noSpace.charAt(i);
			
			if(c >= '0' && c <= '9') {
				num = num * 10 + (c - '0');
			} 
		
			if(!(c >= '0' && c <= '9') || i == noSpace.length() - 1) {
				if(sign == '+') sum += num;
				else if(sign == '-') sum -= num;
				
				num = 0;
				sign = c;
			}
		}
		return sum;
	}
}// end of class
