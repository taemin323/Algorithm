import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	private static int F;
	private static HashMap<String, Integer> map;
	private static int[] rank;
	private static int[] parent;
	private static int idx;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			F = Integer.parseInt(br.readLine());
			
			rank = new int[F*2];
			parent = new int[F*2];
			
			for (int i = 0; i < F*2; i++) {
				parent[i] = i;
				rank[i] = 1;
			}
			
			map = new HashMap<String, Integer>();
			
			idx = 0;
			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String friend1 = st.nextToken();
				String friend2 = st.nextToken();
				
				if(!map.containsKey(friend1)) {
					map.put(friend1, idx++);
				}

				if(!map.containsKey(friend2)) {
					map.put(friend2, idx++);
				}
				
				sb.append(union(map.get(friend1), map.get(friend2))).append("\n");
			}
		}//end of tc
		System.out.println(sb.toString());
	}//end of main

	private static int union(Integer x, Integer y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if(rootX != rootY) {
			parent[rootY] = rootX;
			rank[rootX] += rank[rootY];
			rank[rootY] = 1;
		}
		
		return rank[rootX];
	}

	private static int find(Integer num) {
		if(num == parent[num]) return num;
		
		return parent[num] = find(parent[num]);
	}
}//end of class
