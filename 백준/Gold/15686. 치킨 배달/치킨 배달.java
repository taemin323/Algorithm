import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Point {
		int r, c;
		
		public Point() {
		}
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	private static int N;
	private static int M;
	private static int[][] map;
	private static ArrayList<Point> storeList;
	private static int min;
	private static ArrayList<Point> houseList;
	private static boolean[] visited;
	private static int[][] dist;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		
		map = new int[N][N];
		storeList = new ArrayList<Point>();
		houseList = new ArrayList<Point>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 2) {
					storeList.add(new Point(i,j));
				} else if(map[i][j] == 1) {
					houseList.add(new Point(i,j));
				}
			}
		}// 입력 완료
		
		int houseCnt = houseList.size();
		int storeCnt = storeList.size();
		
		// i번째 집에서 j번째 치킨집까지의 거리
		dist = new int[houseCnt][storeCnt];
		
		for (int i = 0; i < houseCnt; i++) {
			Point house = houseList.get(i);
			for (int j = 0; j < storeCnt; j++) {
				Point store = storeList.get(j);
				
				int curDist = Math.abs(house.r - store.r) + Math.abs(house.c - store.c);
				dist[i][j] = curDist;
			}
		}
		
		visited = new boolean[storeList.size()];
		min = Integer.MAX_VALUE;
		comb(0,0);
		
		System.out.println(min);
	}// end of main

	private static void comb(int idx, int cnt) {
		if(cnt == M) {
			calculate();
		}
		
		for (int i = idx; i < storeList.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				comb(i+1, cnt+1);
				visited[i] = false;
			}
		}
	}

	private static void calculate() {
		int cityDistSum = 0;
		
		for (int i = 0; i < houseList.size(); i++) {
			int chickenDist = Integer.MAX_VALUE;// 해당 집의 치킨거리
			for (int j = 0; j < storeList.size(); j++) {
				if(visited[j]) {
					chickenDist = Math.min(chickenDist, dist[i][j]);
				}
			}
			cityDistSum += chickenDist;
		}
		
		min = Math.min(min, cityDistSum);
	}

}// end of class
