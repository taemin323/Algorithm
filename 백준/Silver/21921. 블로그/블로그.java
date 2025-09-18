import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 1 <= X <= N <= 250,000
 * 0 <= 방문자 수 <= 8,000
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int[] people = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
	
		int cnt = 1;
		int start = 0;
		int end = start + X - 1;
		
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum += people[i];
		}
		int ans = sum;
		
		while(end < N-1) {
			sum -= people[start];
			sum += people[++end];
			start++;

			if(ans == sum) cnt++;
			if(ans > sum) continue;
			
			if(ans < sum) {
				ans = sum;
				cnt = 1;
			} 
			
		}
		
		if(ans == 0) {
			sb.append("SAD");
		} else {
			sb.append(ans).append("\n").append(cnt);
		}
		System.out.println(sb.toString());
	}// end of main
}// end of class
