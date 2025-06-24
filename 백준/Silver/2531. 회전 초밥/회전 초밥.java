import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] origin = new int[N];
		int[] dishes = new int[N+k];// 원형인걸 표현하기 위해 2 * N
		
		
		for (int i = 0; i < N; i++) {
			origin[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < dishes.length; i++) {
			dishes[i] = origin[i % N];
		}

		Set<Integer> set = new HashSet<Integer>();
		int max = Integer.MIN_VALUE;
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < k; j++) {
				set.add(dishes[i+j]);
				cnt = set.size();
				
				if(!set.contains(c)) cnt++;
			}
			max = Math.max(cnt, max);
			set.remove(dishes[i]);
		}
		System.out.println(max);
	}//end of main
}//end of class
