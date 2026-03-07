import java.util.*;
import java.io.*;

public class Main {
	

	private static int N;
	private static int[][] graph;
	private static int[][] result;
	private static boolean[] visited;
	private static int cur;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		result = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			cur = i;
			dfs(i);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}// end of main

	private static void dfs(int i) {
		for (int j = 0; j < N; j++) {
			if(graph[i][j] == 1 && !visited[j]) {
				visited[j] = true;
				result[cur][j] = 1;
				dfs(j);
			}
		}
	}

}// end of class