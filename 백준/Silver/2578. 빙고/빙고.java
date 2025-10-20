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
		
		// 숫자 -> 좌표 매핑
		int[][] pos = new int[26][2];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				int v = Integer.parseInt(st.nextToken());
				board[i][j] = v;
				pos[v][0] = i;
				pos[v][1] = j;
			}
		}
		
		// 가로
		int[] rCnt = new int[5];
		
		// 세로
		int[] cCnt = new int[5];
		
		//우하향 대각선
		int lrCnt = 0;
		
		//우상향 대각선
		int rlCnt = 0;
		
		boolean[] rDone = new boolean[5];
		boolean[] cDone = new boolean[5];
		boolean lrDone = false;
		boolean rlDone = false;
		
		int bingo = 0;
		int answer = 0;
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				int num = Integer.parseInt(st.nextToken());
				answer++;
				
				int r = pos[num][0];
				int c = pos[num][1];
				
				if(board[r][c] != -1) {
					board[r][c] = -1;
					
					if(!rDone[r]) {
						rCnt[r]++;
						if(rCnt[r] == 5) {
							rDone[r] = true;
							bingo++;
						}
					}
					
					if(!cDone[c]) {
						cCnt[c]++;
						if(cCnt[c] == 5) {
							cDone[c] = true;
							bingo++;
						}
					}
					
					if(r == c && !lrDone) {
						lrCnt++;
						if(lrCnt == 5) {
							lrDone = true;
							bingo++;
						}
					}
					
					if(r + c == 4 && !rlDone) {
						rlCnt++;
						if(rlCnt == 5) {
							rlDone = true;
							bingo++;
						}
					}
					
					if(bingo >= 3) {
						System.out.println(answer);
						return;
					}
				}
			}
		}
		
	}// end of main
}// end of class
