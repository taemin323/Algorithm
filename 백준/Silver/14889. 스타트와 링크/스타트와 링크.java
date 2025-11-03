import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[][] board;
	private static int R;
	private static boolean[] visited;
	private static int min;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		R = N/2;
		
		visited = new boolean[N];
		board = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력 완료
		
		min = Integer.MAX_VALUE;
		comb(0,0);
		
		System.out.println(min);
	}// end of main

	private static void comb(int idx, int cnt) {
		
		if(cnt == R) {
			calculate();
			return;
		}
		
		for(int i = idx; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				comb(i+1,cnt+1);
				visited[i] = false;
			}
		}
	}

	private static void calculate() {
		int start = 0;
		int link = 0;
		
		for(int i = 0; i < N-1; i++) {
			for(int j = i+1; j < N; j++) {
				
				//start팀인 경우
				if(visited[i] && visited[j]) {
					start += board[i][j] + board[j][i];
				} else if(!visited[i] && !visited[j]) {
					link += board[i][j] + board[j][i];
				}
			}
		}
		
		int diff = Math.abs(start - link);
		
		min = Math.min(min, diff);
	}
}// end of class
