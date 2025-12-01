import java.io.*;
import java.util.*;

public class Main {
	private static class Question {
		String num;
		int s;
		int b;
		
		public Question(String num, int s, int b) {
			this.num = num;
			this.s = s;
			this.b = b;
		}
	}
	
	private static int N;
	private static char[][] board;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		List<Question> questions = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			String num = st.nextToken();
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			questions.add(new Question(num, s, b));
		}
		
		int answer = 0;
		
		//123~987 완전탐색
		for (int i = 123; i <= 987; i++) {
			String candidate = String.valueOf(i);
			
			// 0포함 x, 중복 x 체크
			if(!isCan(candidate)) continue;
			
			boolean flag = true;
			
			// 모든 질문 만족하는 후보인지 확인
			for (Question q : questions) {
				int s = getStrike(candidate, q.num);
				int b = getBall(candidate, q.num);
				
				if(s != q.s || b != q.b) {
					flag = false;
					break;
				}
			}
			
			if(flag) answer++;
		}
		
		System.out.println(answer);
	}// end of main	

	private static int getBall(String candidate, String num) {
		int b = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(i == j) continue;
				if(candidate.charAt(i) == num.charAt(j)) b++;
			}
		}
		return b;
	}

	private static int getStrike(String candidate, String num) {
		int s = 0;
		for (int i = 0; i < 3; i++) {
			if(candidate.charAt(i) == num.charAt(i)) s++;
		}
		return s;
	}

	private static boolean isCan(String candidate) {
		if(candidate.charAt(0) == '0' || candidate.charAt(1) == '0' || candidate.charAt(2) == '0') return false;
		
		if(candidate.charAt(0) == candidate.charAt(1)) return false;
		if(candidate.charAt(0) == candidate.charAt(2)) return false;
		if(candidate.charAt(1) == candidate.charAt(2)) return false;
		return true;
	}
}// end of class
