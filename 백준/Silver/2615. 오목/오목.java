import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point implements Comparable<Point>{
		int r;
		int c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			if(this.c == o.c) {
				return this.r - o.r;
			}
			return this.c - o.c;
		}
	}
	
	private static int[][] board;
	private static int[] dr = {-1,-1,0,1,1,1,0,-1};
	private static int[] dc = {0,1,1,1,0,-1,-1,-1};
	private static StringBuilder sb;
	private static int resultR;
	private static int resultC;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		board = new int[20][20];
		
		for (int r = 0; r < 19; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < 19; c++) {
				board[r+1][c+1] = Integer.parseInt(st.nextToken());
			}
		}// 입력 완료
		
		boolean flag = false;
		out:
		for (int r = 1; r < 20; r++) {
			for (int c = 1; c < 20; c++) {
				if(board[r][c] != 0) {
					for (int d = 0; d < dc.length; d++) {
						if(bfs(r,c,d)) {
							flag = true;
							sb.append(board[r][c]).append("\n").append(resultR).append(" ").append(resultC);
							break out;
						}
					}
				}
			}
		}
		
		if(!flag) {
			System.out.println(0);
		} else {
			System.out.println(sb.toString());
		}
		
	}//end of main

	private static boolean bfs(int r, int c, int d) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		int cnt = 1;
		boolean result = false;
		int color = board[r][c];
		pq.offer(new Point(r, c));
		int nr = r;
		int nc = c;
		for (int i = 0; i < 5; i++) {
			nr += dr[d];
			nc += dc[d];
			if(nr <= 0 || nr > 19 || nc <= 0 || nc > 19) break;
			if(board[nr][nc] != color)break;
			cnt++;
			pq.offer(new Point(nr, nc));
		}
		
		if(cnt == 5) {
			if(board[r+dr[d]*-1][c+dc[d]*-1] != color) {
				result = true;
				Point ans = pq.poll();
				resultR = ans.r;
				resultC = ans.c;
			}
		}
		return result;
	}

	
}//end of class
