import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int c;
	private static int r;
	private static int K;
	private static int[][] map;
	private static int[] dr = {0,0,-1,1};
	private static int[] dc = {1,-1,0,0};
	private static int[] dice = new int[7];
	//   1
	//  234
	//   5
	//   6
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());//세로
		M = Integer.parseInt(st.nextToken());//가로
		r = Integer.parseInt(st.nextToken());//주사위 r
		c = Integer.parseInt(st.nextToken());//주사위 c
		K = Integer.parseInt(st.nextToken());//명령의 개수
		
		map = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken());
			move(d);
		}
		
		
		
	}//end of main

	private static void move(int d) {
		int nr = r + dr[d-1];
		int nc = c + dc[d-1];
		if(nr < 0 || nr >= N || nc < 0 || nc >= M) return;
		action(d, nr,nc);
		r = nr;
		c = nc;
	}

	// 1234 -> 동서북남
	private static void action(int d, int r, int c) {
		int tmp = dice[3];
		switch(d) {
		case 1:
			dice[3] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[2];
			dice[2] = tmp;
			break;
		case 2:
			dice[3] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[4];
			dice[4] = tmp;
			break;
		case 3:
			dice[3] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[1];
			dice[1] = tmp;
			break;
		case 4:
			dice[3] = dice[1];
			dice[1] = dice[6];
			dice[6] = dice[5];
			dice[5] = tmp;
			break;
		}
		if(map[r][c] == 0) {
			map[r][c] = dice[6];
		} else {
			dice[6] = map[r][c];
			map[r][c] = 0;
		}
		System.out.println(dice[3]);
	}
}//end of class
