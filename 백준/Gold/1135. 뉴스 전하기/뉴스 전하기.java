import java.util.*;
import java.io.*;

public class Main {
	
	private static int N;
	private static List<Integer>[] tree;
	private static int answer = 0;
	private static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		tree = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		st.nextToken();
		for (int i = 1; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			tree[parent].add(i);
		}
		
		System.out.println(dfs(0));
	}// end of main

	// cur 노드가 자신의 모든 부하직원에게 소식을 전하는 데 걸리는 최소 시간 반환
	private static int dfs(int cur) {
		// 자식이 없으면 0분 소요
		if(tree[cur].isEmpty()) return 0;
		
		// 각 자식 노드(부하직원)들이 자기 팀원들에게 모두 전파하는 데 걸리는 시간 수집
		List<Integer> times = new ArrayList<>();
		for (int next : tree[cur]) {
			times.add(dfs(next));
		}
		
		// 전파하는 데 시간이 오래 걸리는 자식에게 먼저 전화를 걸어야 전체 시간이 단축
		// 따라서 자식들의 소요 시간을 내림차순으로 정렬.
		Collections.sort(times, Collections.reverseOrder());
		
		int totalMaxT = 0;
		for (int i = 0; i < times.size(); i++) {
			// i번째 자식에게 전화를 거는 상황
			// i+1 : 부모가 순서대로 전화를 걸 때 발생하는 대기시간 (1번째면 1분, 2번째면 2분)
			// times.get(i) : 그 자식이 전화를 받은 후 자기 밑으로 전파하는 데 걸리는 시간
			// 두 값을 더한 것이 이 자식으로 인해 발생하는 최종 합계 시간
			
			int curT = times.get(i) + (i+1);
			
			// 여러 자식 중 가장 늦게 끝나는 시간이 현재 노드의 전체 소요 시간.
			totalMaxT = Math.max(totalMaxT, curT);
		}
		return totalMaxT;
	}
}// end of class