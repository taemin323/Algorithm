import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] dr = {-1,0,1,0};//북동남서
	private static int[] dc = {0,1,0,-1};
	private static int answer = 1;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int startR = Integer.parseInt(st.nextToken());
		int startC = Integer.parseInt(st.nextToken());
		
		// 0 : 북쪽, 1 : 동쪽, 2 : 남쪽, 3 : 서쪽
		int dir = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력 완료
		
		clean(startR, startC, dir);
		System.out.println(answer);
		
	}// end of main

	private static void clean(int startR, int startC, int dir) {
		map[startR][startC] = 2;
		
		for (int i = 0; i < 4; i++) {
			dir = (dir+3)%4;
			
			int nr = startR + dr[dir];
			int nc = startC + dc[dir];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			
			if(map[nr][nc] == 0) {
				answer++;
				clean(nr, nc, dir);
				return;
			}
		}
		
		int d = (dir+2) % 4; // 반대 방향으로 후진
		int br = startR + dr[d];
		int bc = startC + dc[d];
		
		if(br >= 0 && br < N && bc >= 0 && bc < M && map[br][bc] != 1) {
			clean(br, bc, dir);//후진이니까 바라보는 방향은 유지
		}
		
		
	}
}//end of class
