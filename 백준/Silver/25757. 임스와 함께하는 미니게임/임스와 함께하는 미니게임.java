import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * 1 <= N <= 100,000
 * 1 <= name <= 20
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		String game = st.nextToken();
		
		int limit;
		switch(game) {
			case "Y": limit = 1;
			break;
			case "F": limit = 2;
			break;
			default: limit = 3;
		}
		
		Set<String> set = new HashSet<>();
	
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		System.out.println(set.size() / limit);
	}// end of main
}// end of class
