import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static Info[] board;
	private static int max;
	
	private static class Info {
		int term, price;

		public Info(int term, int price) {
			this.term = term;
			this.price = price;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		board = new Info[N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			board[i] = new Info(t,p);
		}// 입력 완료
		
		max = 0;
		dfs(1,0);
		System.out.println(max);
	}// end of main

	private static void dfs(int day, int sum) {
		//모든 날을 넘어섰다면 결과 갱신
		if(day > N) {
			if(sum > max) max = sum;
			return;
		}
		
		// 오늘 일을 하지 않는 경우
		dfs(day + 1, sum);
		
		// 오늘 일을 하는 경우
		int t = board[day].term;
		int p = board[day].price;
		int finishDay = day + t - 1;
		
		if(finishDay <= N) {
			dfs(day+t, sum + p);
		}
	}

}// end of class
