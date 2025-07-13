import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 1 <= N <= 1,500
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				pq.offer(num);
				
				if(pq.size() > N) {
					pq.poll();
				}
			}
		}//입력 완료
	
		System.out.println(pq.poll());
	}//end of main
}//end of class
