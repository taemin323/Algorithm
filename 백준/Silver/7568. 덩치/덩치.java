import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[] w = new int[N];
		int[] h = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			w[i] = Integer.parseInt(st.nextToken());
			h[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int rank = 1;
			for (int j = 0; j < N; j++) {
				if(i == j) continue;
				if(w[j] > w[i] && h[j] > h[i]) rank++;
			}
			sb.append(rank);
			if(i + 1 < N) sb.append(" ");
		}
		System.out.println(sb.toString());
	}// end of main
}// end of class
