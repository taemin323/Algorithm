import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int rank = 1;
		
		int[][] names = new int[N][4];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int name = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int bronze = Integer.parseInt(st.nextToken());
			
			names[name-1][0] = gold;
			names[name-1][1] = silver;
			names[name-1][2] = bronze;
		}
		
		for (int i = 0; i < N; i++) {
			if(names[i][0] > names[K-1][0]) {
				rank++;
			} else if(names[i][0] == names[K-1][0] && names[i][1] > names[K-1][1]) {
				rank++;
			} else if(names[i][0] == names[K-1][0] && names[i][1] == names[K-1][1] && names[i][2] > names[K-1][2]) {
				rank++;
			}
		}
		System.out.println(rank);
	}// end of main
}// end of class
