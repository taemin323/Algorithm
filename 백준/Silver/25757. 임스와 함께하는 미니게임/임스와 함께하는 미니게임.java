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
		
		String[] names = new String[N];
		
		for (int i = 0; i < N; i++) {
			names[i] = br.readLine();
		}
		
		Set<String> set = new HashSet<>();
		int cnt = 0;
		int total = 0;
		int ans = 0;
		
		if(game.equals("Y")) total = 1;
		else if(game.equals("F")) total = 2;
		else total = 3;
		
		for (String name : names) {
			if(!set.contains(name)) {
				set.add(name);
				cnt++;
			}
			
			if(total - cnt == 0) {
				cnt = 0;
				ans++;
			}
		}
		System.out.println(ans);
	}// end of main
}// end of class
