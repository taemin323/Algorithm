import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int P = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= P; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int T = Integer.parseInt(st.nextToken());
			sb.append(T).append(" ");
			
			int cnt = 0;
			int[] num = new int[20];
			
			for (int i = 0; i < 20; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < i; j++) {
					if(num[j] > num[i]) cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}// end of tc
		System.out.println(sb.toString());
	}// end of main
}// end of class
