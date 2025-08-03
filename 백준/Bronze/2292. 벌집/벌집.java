import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 1 <= N <= 1,000,000,000
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int cnt = 1;
		int range = 2;
		
		if(N == 1) {
			System.out.println(1);
		} else {
			while(range <= N) {
				range += (6 * cnt);
				cnt++;
			}
			System.out.println(cnt);
		}
		
	}// end of main
}// end of class
