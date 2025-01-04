import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point implements Comparable<Point>{
		int r, c, size, cnt;
		
		public Point() {
		}
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.r == o.r) return this.c - o.c;
			return this.r - o.r;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", size=" + size + ", cnt=" + cnt + "]";
		}
	}

	private static int N;
	private static int[][] map;
	private static Point shark;
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};
	private static int ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());//공간크기
		
		shark = new Point();
		
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 9) {
					shark.r = r;
					shark.c = c;
					shark.size = 2;// 초기 사이즈
					shark.cnt = 0;// 먹은 물고기 수
					map[r][c] = 0;// 상어의 초기 좌표의 값은 먹을 물고기가 없으므로 0.
				}
			}
		}//입력완.
		
		while(true){
			if(!bfs(shark.r, shark.c)) {
				break;
			}
		}
		System.out.println(ans);
	}//end of main

	private static boolean bfs(int r, int c) {
		boolean[][] visited = new boolean[N][N];		
		List<Point> targetList = new ArrayList<Point>();
		
		Queue<Point> q = new LinkedList<Point>();
		q.offer(shark);
		visited[r][c] = true;
		
		int dist = 0;
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			
			for (int i = 0; i < qSize; i++) {
				Point cur = q.poll();
				
				for (int d = 0; d < dc.length; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] <= shark.size && !visited[nr][nc]) {
						Point fish = new Point(nr, nc);
						q.offer(new Point(nr, nc));
						visited[nr][nc] = true;
						
						if(map[nr][nc] >=1 && map[nr][nc] < shark.size) {
							targetList.add(new Point(nr, nc));
						}
					}
				}
			}
			dist++;
			
			if(targetList.size() > 0) {
				Collections.sort(targetList);
				
				Point fish = targetList.get(0);
				
				map[fish.r][fish.c] = 0;
				shark.r = fish.r;
				shark.c = fish.c;
				shark.cnt++;
				
				if(shark.cnt == shark.size) {
					shark.size++;
					shark.cnt = 0;
				}
				
				ans+=dist;
				return true;
			}
		}
		
		return false;
	}
}//end of class