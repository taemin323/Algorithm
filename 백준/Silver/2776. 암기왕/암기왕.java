import java.util.*;
import java.io.*;

public class Main {
	
	private static int N;
	private static int M;
	private static int[] arr1;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			StringBuilder sb = new StringBuilder();
			
			N = Integer.parseInt(br.readLine());
			
			HashSet<Integer> set = new HashSet<>();
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				set.add(Integer.parseInt(st.nextToken()));
			}
			
			M = Integer.parseInt(br.readLine());
			
			int[] arr2 = new int[M];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				if(set.contains(Integer.parseInt(st.nextToken()))) sb.append(1);
				else sb.append(0);
				sb.append("\n");
			}
			System.out.print(sb.toString());
			
		}
		
		
	}// end of main
}// end of class