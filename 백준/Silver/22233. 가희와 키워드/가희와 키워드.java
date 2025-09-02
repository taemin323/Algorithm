import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Set<String> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), ",");
			int len = st.countTokens();
			
			for (int j = 0; j < len; j++) {
				String keyWord = st.nextToken();
				if(set.contains(keyWord)) {
					set.remove(keyWord);
				}
			}
			
			sb.append(set.size()).append("\n");
		}
		System.out.println(sb.toString());
	}// end of main
}// end of class
