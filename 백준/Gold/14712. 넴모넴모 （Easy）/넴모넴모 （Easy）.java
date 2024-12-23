import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static boolean[][] board;
	private static int ans;
	private static int[] dr = {-1,-1,0};// 2*2 정사각형 내부 탐색
	private static int[] dc = {-1,0,-1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());//1<= N, M <= 25
		M = Integer.parseInt(st.nextToken());//1<= N*M <= 25
		
		board = new boolean[N][M];
		
		backTracking(0,0);
		
		System.out.println(ans);
	}//end of main

	private static void backTracking(int r, int c) {
		if(c >= M) {
			backTracking(r+1,0);// 한 줄 끝까지 탐색하면 다음 줄로 이동
			return;
		}
		
		if(r >= N) {
			ans++;// 모든 배치를 끝내면 ans를 하나 증가.
			return;
		}
		
		//현재 2*2 정사각형을 이루는 배치가 아닌 경우
		board[r][c] = true;// 현재 칸 채워.
		if(!isSquare(r,c)) {// 2*2 정사각형이 아니면 다음 칸으로
			backTracking(r, c+1);
		}
		
		//원복
		board[r][c] = false;
		backTracking(r, c+1);
	}

	private static boolean isSquare(int r, int c) {
		if(!board[r][c]) return false;// 현재 칸이 비어있으면 2*2 정사각형이 아님.
		
		for (int d = 0; d < dc.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M || !board[nr][nc]) {
				return false;//2*2 정사각형이 아니면 false 반환
			}
		}
		
		return true;//2*2 정사각형이면 true 반환
	}
}//end of class
