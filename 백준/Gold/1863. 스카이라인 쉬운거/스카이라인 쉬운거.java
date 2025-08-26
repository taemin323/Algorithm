import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * 1 <= N <= 50,000
 * 1 <= x <= 1,000,000
 * 0 <= y <= 500,000
 * 첫 번째 지점의 x좌표는 항상 1
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty() && stack.peek() > y) {
				stack.pop();
				cnt++;
			}
			
			if(stack.isEmpty() || stack.peek() < y) {
				stack.push(y);
			}
			
		}// 입력 완료
		
		while(!stack.isEmpty()) {
			if(stack.pop() > 0) cnt++;
		}
		
		System.out.println(cnt);
		
	}// end of main
}// end of class
