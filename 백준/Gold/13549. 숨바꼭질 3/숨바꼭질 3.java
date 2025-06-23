import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 0 <= N <= 100,000
 * 0 <= K <= 100,000
 * 걷기 : N+1 or N-1
 * 순간이동 : 2*N
 */
public class Main {
	static final int MAX = 100000;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if (N == K) {
			System.out.println(0);
			return;
		}

		int[] dist = new int[MAX+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		Deque<Integer> deq = new ArrayDeque<Integer>();
		deq.add(N);
		dist[N] = 0;
		
		while (!deq.isEmpty()) {
			int cur = deq.pollFirst();
			if (cur == K) break;
			
			//순간이동
			int nx = cur << 1; // cur * 2
			if (nx <= MAX && dist[nx] > dist[cur]) {
				dist[nx] = dist[cur];
				deq.addFirst(nx);
			}
			
			//걷기
			for (int next: new int[] {cur -1, cur + 1}) {
				if(0 <= next && next <= MAX && dist[next] > dist[cur] + 1) {
					dist[next] = dist[cur] + 1;
					deq.addLast(next);
				}
			}
		}
		
		System.out.println(dist[K]);
	}//end of main
}//end fo class