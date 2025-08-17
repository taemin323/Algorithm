import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	private static int N;
	private static int[] nextOf;
	private static boolean[] visited;
	private static boolean[] finished;
	private static boolean[] inCycle;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		// i가 가리키는 다음 번호 
		nextOf = new int[N+1];
		for (int i = 1; i <= N; i++) {
			nextOf[i] = Integer.parseInt(br.readLine());
		}
		
		// i를 한 번이라도 방문했는지
		visited = new boolean[N+1];
		
		// i에서 시작한 dfs 처리가 끝났는지
		finished = new boolean[N+1];
		
		// 정답에 포함되는지(사이클이 되는지)
		inCycle = new boolean[N+1];
		
		// 모든 정점을 시작점으로 시도
		for (int i = 1; i <= N; i++) {
			if(!visited[i]) dfs(i);
		}
		
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if(inCycle[i]) cnt++;
		}
		sb.append(cnt).append("\n");
		for (int i = 1; i <= N; i++) {
			if(inCycle[i]) sb.append(i).append("\n");
		}
		
		System.out.println(sb.toString());
	}// end of main

	private static void dfs(int i) {
		visited[i] = true;
		int next = nextOf[i];
		
		if(!visited[next]) {
			//처음 가는 노드면 계속 재귀
			dfs(next);
		} else if (!finished[next]) {
			// next는 방문됐지만 아직 '처리 완료'는 아님 = 현재 재귀스택 위
			// next부터 다시 next까지가 하나의 사이클 = 그 구간만 정답임.
			int cur = next;
			
			do {
				inCycle[cur] = true;
				cur = nextOf[cur];
			} while(cur != next);
		}
		
		// i에서 갈 수 있는 처리를 모두 끝냈으니 스택에서 내려옴
		finished[i] = true;
	}
}// end of class
