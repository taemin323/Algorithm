import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	private static char[] aeiou = {'a', 'e', 'i', 'o', 'u'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new  InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String password = br.readLine();
			
			if(password.equals("end")) break;
			
			sb.append("<").append(password).append(">").append(" ");
			
			if(isAccept(password)) {
				sb.append("is acceptable.").append("\n");
			} else {
				sb.append("is not acceptable.").append("\n");
			}
		}
		System.out.println(sb.toString());
	}// end of main

	private static boolean isAccept(String password) {
		boolean hasAeiou = false;
		
		int cnt1 = 0; // 모음 카운트
		int cnt2 = 0; // 자음 카운트

		for (int i = 0; i < password.length(); i++) {
			char cur = password.charAt(i);
			boolean flag = check(cur);
			
			if(flag) hasAeiou = true;
			
			if(i > 0) {
				char prev = password.charAt(i-1);
				if(cur == prev && !(cur == 'e' || cur == 'o')) return false;
			}
			
			if(flag) {
				cnt1++;
				cnt2 = 0;
				if(cnt1 >= 3) return false;
			} else {
				cnt2++;
				cnt1 = 0;
				if(cnt2 >= 3) return false;
			}
		}
		return hasAeiou;
	}

	private static boolean check(char c) {
		for (int i = 0; i < aeiou.length; i++) {
			if(aeiou[i] == c) return true;
		}
		return false;
	}
}// end of class
