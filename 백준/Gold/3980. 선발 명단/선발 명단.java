import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] board;
	private static boolean[] visited;
	private static int max;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			
			board = new int[11][11];
			visited = new boolean[11];
			
			for (int r = 0; r < 11; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				for (int c = 0; c < 11; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}// 입력 완료
			
			max = 0;
			dfs(0, 0);
			
			System.out.println(max);
		}//end of tc
	}//end of main

	private static void dfs(int position, int total) {
		if(position == 11) {
			max = Math.max(max, total);
			return;
		}
		
		for (int i = 0; i < 11; i++) {
			if(!visited[i] && board[position][i] != 0) {
				visited[i] = true;
				dfs(position+1, total + board[position][i]);
				visited[i] = false;
			}
		}
	}
}//end of class
