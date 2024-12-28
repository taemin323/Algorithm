import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int N;
	private static int[] board;
	private static boolean[] visited;
	private static int cnt = 0;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		board = new int[N];
		visited = new boolean[N];
		
		Queen(0);
		sb.append(cnt);
		System.out.println(sb.toString());
	}//end of main

	private static void Queen(int depth) {
		if(depth == N) {
			cnt++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			board[depth] = i;
			if(possible(depth)) {
				Queen(depth+1);
			}
		}
	}

	private static boolean possible(int col) {
		for (int i = 0; i < col; i++) {
			//열에 일치하는게 있는지 판별
			if(board[i] == board[col]) {
				return false;
			}
			//대각선에 일치하는게 있는지 판별
			// |행1 - 행2| = |열1 - 열2| 
			else if(Math.abs(col-i) == Math.abs(board[col]-board[i])) {
				return false;
			}
		}
		return true;
	}
}//end of class
