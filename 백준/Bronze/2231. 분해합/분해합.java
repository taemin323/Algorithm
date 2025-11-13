import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {


	private static int N;
	private static int answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		answer = Integer.MAX_VALUE;

		
		
		String num = String.valueOf(N);
		int maxTen = (int) Math.pow(10, num.length()-1);
		
		int candidate = 1;
		while(candidate < N) {
			calculate(candidate, maxTen);
			if(answer != Integer.MAX_VALUE) break;
			candidate++;
		}
		
		if(answer == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(answer);
		}
	}// end of main

	private static void calculate(int candidate, int maxTen) {
		int tmp = candidate;
		int sum = candidate;
		while(maxTen >= 1) {
			sum += candidate / maxTen;
			candidate %= maxTen;
			maxTen /= 10;
		}
		if(sum == N) answer = tmp;
	}
}// end of class
