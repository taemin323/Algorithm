import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * 2 <= N <= 30,000 접시의 수
 * 2 <= d <= 3,000 초밥의 가짓수
 * 2 <= k <= 3,000 연속해서 먹는 접시의 수
 * 1 <= c <= d 쿠폰 번호 c
 */
public class Main {	
	private static int N;
	private static int d;
	private static int k;
	private static int c;
	private static int[] dishes;
	private static HashSet<Integer> set;
	private static LinkedList<Integer> q;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		dishes = new int[N+k-1];
		for (int i = 0; i < N; i++) {
			dishes[i] = Integer.parseInt(br.readLine());
		}// 입력 완료
		
		int idx = 0;
		for (int i = N; i < N+k-1; i++) {
			dishes[i] = dishes[idx++];
		}
		
		// 초밥 번호의 개수
		int[] sushi = new int[d+1];
		int cnt = 0;
		
		// 초기 윈도우
		for (int i = 0; i < k; i++) {
			if(sushi[dishes[i]]++ == 0) {
				cnt++;
			}
		}
		int ans = cnt + (sushi[c] == 0 ? 1 : 0);
		
		
		for (int i = 1; i < N; i++) {
			int left = dishes[i-1];
			int right = dishes[i+k-1];
			
			if(--sushi[left] == 0) cnt--;
			if(sushi[right]++ == 0) cnt++;
			
			int cur = cnt + (sushi[c] == 0 ? 1 : 0);
			ans = Math.max(ans, cur);
		}
		
		System.out.println(ans);
	}// end of main

}// end of class
