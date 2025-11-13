import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {


	private static int N;
	private static int answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		answer = 0;

		
		
		String num = String.valueOf(N);
		
		int start = N - (num.length() * 9);
		if(start < 1) {
			start = 1;
		}
		
		for (int i = start; i < N; i++) {
			int sum = i;
			int tmp = i;
			
			while(tmp > 0) {
				sum += tmp % 10;
				tmp /= 10;
			}
			
			if(sum == N) {
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);
	}// end of main

}// end of class
