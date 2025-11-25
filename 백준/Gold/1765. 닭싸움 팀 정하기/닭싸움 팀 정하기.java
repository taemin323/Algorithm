import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int[] parent;
	private static int[] enemy;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		enemy = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			String type = st.nextToken();
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			if(type.equals("F")) {
				union(p,q);
			} else {
				int enemyP = enemy[p];
				int enemyQ = enemy[q];
				
				if(enemyP == 0) enemy[p] = q;
				else union(enemyP, q);
				
				if(enemyQ == 0) enemy[q] = p;
				else union(enemyQ, p);
			}
		}
		
		boolean[] seen = new boolean[N+1];
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			int r = find(i);
			
			if(!seen[r]) {
				seen[r] = true;
				cnt++;
			}
		}
		System.out.println(cnt);
	}// end of main

	private static void union(int p, int q) {
		p = find(p);
		q = find(q);
		
		if(p != q) parent[q] = p;
	}

	private static int find(int p) {
		if(parent[p] == p) return p;

		return parent[p] = find(parent[p]);
	}
}// end of class
