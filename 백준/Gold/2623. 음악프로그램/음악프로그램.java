import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 1 <= N <= 1000
 * 1 <= M <= 100
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		// 진입 차수 배열
		int[] indeg = new int[N+1];
		
		// 중복 방지
		boolean[][] seen = new boolean[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			if(k == 0) continue;
			
			int prev = Integer.parseInt(st.nextToken());
			for (int j = 1; j < k; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if(!seen[prev][cur]) {
					adj[prev].add(cur);
					indeg[cur]++;
					seen[prev][cur] = true;
				}
				prev = cur;
			}
		}//입력 완료
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for (int v = 1; v <= N; v++) {
			if(indeg[v] == 0) q.add(v);
		}
		
		int visited = 0;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			sb.append(v).append("\n");
			visited++;
			
			for (int next : adj[v]) {
				if(--indeg[next] == 0) q.add(next);
			}
		}
		
		if(visited != N) {
			System.out.println(0);
		} else {
			System.out.println(sb.toString());
		}
	}// end of main
}// end of class
