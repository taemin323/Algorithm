import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static boolean[][] visited;
	private static int bingo;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] board = new int[5][5];
		visited = new boolean[5][5];
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 5; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 철수 빙고판
		
		int answer = 0;
		bingo = 0;
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				int num = Integer.parseInt(st.nextToken());
				answer++;
				
				
				for(int r = 0; r < 5; r++) {
					for(int c = 0; c < 5; c++) {
						if(board[r][c] == num) {
							visited[r][c] = true;
						}
					}
				}
				
				rCheck();
				cCheck();
				lrCheck();
				rlCheck();
				
				
				if(bingo >= 3) {
					System.out.println(answer);
					return;
				}
				bingo = 0;
			}
		}
		
	}// end of main
	
	// 오른쪽에서 왼쪽으로 그어지는 대각선	
	private static void rlCheck() {
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			if(visited[i][4-i]) cnt++;
		}
		if(cnt == 5) bingo++;
	}

	// 왼쪽에서 오른쪽으로 그어지는 대각선
	private static void lrCheck() {
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			if(visited[i][i]) cnt++;
		}
		if(cnt == 5) bingo++;
	}

	private static void cCheck() {
		for (int i = 0; i < 5; i++) {
			int cnt = 0;
			for (int j = 0; j < 5; j++) {
				if(visited[j][i]) cnt++;
			}
			if(cnt == 5) bingo++;
		}
	}

	private static void rCheck() {
		for (int i = 0; i < 5; i++) {
			int cnt = 0;
			for (int j = 0; j < 5; j++) {
				if(visited[i][j]) cnt++;
			}
			if(cnt == 5) bingo++;
		}
	}

}// end of class