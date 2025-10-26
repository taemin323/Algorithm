import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int cnt;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());//테스트 케이스 개수
		for (int tc = 0; tc < T; tc++) {
			String S = br.readLine();
			
			cnt = 0;
			if(isPalindrome(S, 0, S.length()-1)) {
				sb.append(1);
			} else {
				sb.append(0);
			}
		
			sb.append(" ").append(cnt).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}//end of main

	private static boolean isPalindrome(String s, int start, int end) {
		cnt++;
		if(start >= end) {
			return true;
		}
		
		if(s.charAt(start) != s.charAt(end)) {
			return false;
		}
		
		return isPalindrome(s, start+1, end-1);
	}
}//end of class
