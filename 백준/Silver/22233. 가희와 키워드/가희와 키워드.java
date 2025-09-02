import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		
		
		int remain = N;
		
		for (int i = 0; i < M; i++) {
			String line = br.readLine();
			String[] words = line.split(",");
			
			for (String word : words) {
				set.remove(word);
			}
			
			sb.append(set.size()).append("\n");
		}
		System.out.println(sb.toString());
	}// end of main
}// end of class
