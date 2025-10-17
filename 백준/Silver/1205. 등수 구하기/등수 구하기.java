import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int taesu = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		if(N == 0) {
			System.out.println(1);
			return;
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int[] ranks = new int[N];
		for (int i = 0; i < N; i++) {
			ranks[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		
		if(N == P && taesu <= ranks[N-1]) {
			System.out.println(-1);
			return;
		}
		
		int rank = 1;
		for (int i = 0; i < N; i++) {
			if(ranks[i] > taesu) rank++;
			else break;
		}
		
		System.out.println(rank);
	}// end of main
}// end of class
