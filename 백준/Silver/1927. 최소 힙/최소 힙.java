import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 1 <= N <= 100,000
 * x = 0 or 2^31보다 작은 자연수
 * x가 자연수일 때 배열에 x를 추가하는 연산
 * x가 0일 때 배열에서 가장 작은 값을 출력하고 그 값을 배열에서 제거하는 연산
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if(x == 0 && minHeap.isEmpty()) {
				System.out.println(0);
			} else if (x == 0 && !minHeap.isEmpty()) {
				System.out.println(minHeap.poll());
			} else {
				minHeap.add(x);
			}
		}
	}// end of main
}// end of class
