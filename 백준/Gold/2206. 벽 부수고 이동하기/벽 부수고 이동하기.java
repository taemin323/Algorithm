import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point{
		int r,c,skill,cnt;
		
		public Point() {
		}

		public Point(int r, int c, int skill, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.skill = skill;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", skill=" + skill + ", cnt=" + cnt + "]";
		}
		
	}
	private static int N;
	private static int M;
	private static char[][] map;
	private static boolean[][][] visited;// 벽을 부수지 않고 방문
	private static int[] dr = { 0, 0, -1, 1 };
	private static int[] dc = { -1, 1, 0, 0 };;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());// 행 개수
		M = Integer.parseInt(st.nextToken());// 열 개수

		map = new char[N][M];
		//3차원 배열로 벽을 부쉈는지 여부 관리 (0: 벽 안 부쉼, 1: 벽 부쉼)
		visited= new boolean[N][M][2];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = str.charAt(c);
			}
		} // 입력 완료
		
		int ans = bfs();
		System.out.println(ans);
		
	}// end of main

	private static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, 1, 1));// r,c,skill,cnt
		visited[0][0][1] = true;
		
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			int r = curr.r;
			int c = curr.c;
			int skill = curr.skill;
			int cnt = curr.cnt;
			
			//도착 지점에 도달한 경우
			
			if(r == N-1 && c == M-1) return cnt;
			
			for (int d = 0; d < dc.length; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M) {
					//벽을 부수지 않고 이동하는 경우
					if(map[nr][nc] == '0' && !visited[nr][nc][skill]) {
						queue.add(new Point(nr,nc,skill,cnt+1));
						visited[nr][nc][skill] = true;
					}
					//벽을 부수고 이동하는 경우
					else if(map[nr][nc] == '1' && skill == 1 && !visited[nr][nc][0]) {
						queue.add(new Point(nr,nc,0,cnt+1));
						visited[nr][nc][0] = true;
					}
				}
			}
		}
		
		
		return -1;
	}

	

}// end of class
