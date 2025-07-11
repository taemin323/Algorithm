import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());// 날의 수
			
			int[] prices = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < N; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}// 입력 완료
			
			int max = prices[prices.length-1];
			long myMoney = 0;
			for (int i = prices.length-2; i >= 0; i--) {
				if(prices[i] > max) {
					max = Math.max(max, prices[i]);
				}
				
				myMoney += max - prices[i];
			}
			sb.append(myMoney).append("\n");
		}// end of tc
		System.out.println(sb.toString());
	}// end of main
}// end of class
