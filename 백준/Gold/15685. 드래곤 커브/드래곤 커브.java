import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[] dr = {0,-1,0,1};//우상좌하
	private static int[] dc = {1,0,-1,0};
	private static List<Integer> dirList;
	private static boolean[][] map = new boolean[101][101];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			dirList = new ArrayList<>();
			addDirAll(d,g);// 각 세대마다 방향을 전부 넣기
			draw(r,c);
		}
		
		int answer = check();
		System.out.println(answer);
	}// end of main

	private static int check() {
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) cnt++;
			}
		}
		
		return cnt;
	}

	private static void draw(int r, int c) {
		map[r][c] = true;//초기위치
		
		int nr = r;
		int nc = c;
		
		for (int i = 0; i < dirList.size(); i++) {
			int d = dirList.get(i);
			
			nr += dr[d];
			nc += dc[d];
			
			map[nr][nc] = true;
		}
	}

	private static void addDirAll(int d, int g) {
		dirList.add(d);//0세대일 때 방향 추가
		
		for (int i = 1; i <= g; i++) {
			for (int j = dirList.size()-1; j >= 0; j--) {
				dirList.add((dirList.get(j)+1)%4);
			}
		}
	}
}// end of class
