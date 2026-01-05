import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int[] parents;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				int connect = Integer.parseInt(st.nextToken());
				if(connect == 1) {
					Union(i,j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int root = find(start);//첫번째 도시의 부모
		
		boolean flag = true;
		
		for (int i = 1; i < M; i++) {
			int next = Integer.parseInt(st.nextToken());
			if(root != find(next)) {// 대표 노드가 다르면 연견 안된거임.
				flag = false;
				break;
			}
		}
		System.out.println(flag ? "YES" : "NO");
	}// end of main	

	private static void Union(int i, int j) {
		int parentI = find(i);
		int parentJ= find(j);
		
		if(parentI != parentJ) {
			parents[parentJ] = parentI;
		}
	}

	private static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
}// end of class
