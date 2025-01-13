import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Spin{
		int second;
		String dir;
		
		public Spin(int second, String dir) {
			super();
			this.second = second;
			this.dir = dir;
		}
	}
	
	private static int[] dr = {-1,0,1,0};//상우하좌
	private static int[] dc = {0,1,0,-1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());//보드의 크기
		int K = Integer.parseInt(br.readLine());//사과의 개수
		
		int[][] board = new int[N][N];
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken())-1;//사과의 r
			int c = Integer.parseInt(st.nextToken())-1;//사과의 c
			board[r][c] = 1;//사과.
		}
		
		int L = Integer.parseInt(br.readLine());//방향 변환 횟수
		
		Queue<Spin> spin = new LinkedList<>();
		
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int second = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();//second가 끝난 뒤에 방향. L:왼쪽으로 90도, D:오른쪽으로 90도
		
			spin.add(new Spin(second, dir));
		}
		
		int r = 0;
		int c = 0;
		int time = 0;
		int dir = 1;//방향은 오른쪽부터 시작.
		Queue<int[]> snake = new LinkedList<>();
		snake.offer(new int[] {r,c});
		board[r][c] = 2;
		
		while(true) {
			
			time++;

			//머리 이동
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			// 벽 또는 자기 몸과 충돌
			if(nr >= N || nr < 0 || nc >= N || nc < 0 || board[nr][nc] == 2) break;
			
			//사과가 있는 경우
			if(board[nr][nc] == 1) {
				board[nr][nc] = 0;
			} else {
				//사과가 없는 경우 꼬리 제거 
				int[] tail = snake.poll();
				board[tail[0]][tail[1]] = 0;
			}
			
			//뱀의 머리 이동
			snake.offer(new int[] {nr, nc});
			board[nr][nc] = 2;
			
			//방향 전환
			if(!spin.isEmpty()) {
				if(time == spin.peek().second) {
					Spin cur = spin.poll();
					
					if(cur.dir.equals("L")) {
						dir = (dir+3) % 4;
					} else {
						dir = (dir+1) % 4;
					}
				}
			}
			
			//머리 좌표 갱신
			r = nr;
			c = nc;
		}
		
		System.out.println(time);
		
	}//end of main

	
}//end of class