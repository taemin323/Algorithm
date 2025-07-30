import java.util.*;
import java.io.*;

/**
 * 1 <= N <= 500,000
 *  1 <= top <= 100,000,000 
 */

public class Main {
	
	static class Top {
		int h, idx;
		
		public Top() {
		}

		public Top(int h, int idx) {
			super();
			this.h = h;
			this.idx = idx;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int[] tops = new int[N];
		int[] ans = new int[N];
		ans[0] = 0;
		sb.append(ans[0]).append(" ");
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			tops[i] = Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		
		Stack<Top> stack = new Stack<>();
		stack.add(new Top(tops[0], 0));
		
		
		for (int i = 1; i < N; i++) {
			
			while (!stack.isEmpty() && tops[i] >= stack.peek().h) {
				stack.pop();
			}
			
			if(stack.isEmpty()) {
				ans[i] = 0;
			} else {
				ans[i] = stack.peek().idx + 1;
			}
			stack.push(new Top(tops[i], i));
			sb.append(ans[i]).append(" ");
		}
		
		System.out.println(sb.toString());
	}//end of main
}//end of class