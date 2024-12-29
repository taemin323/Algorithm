import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Stack;

public class Main {
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		sb.append((int)Math.pow(2, N)-1).append("\n");
		
		Hanoi(N,1,2,3);
		System.out.println(sb.toString());
		
	}//end of main

	private static void Hanoi(int N, int start, int mid, int end) {
		if(N == 1) {
			sb.append(start + " " + end + "\n");
			return;
		}
		
		// 1 -> 3으로 옮긴다고 가정할 때,
		// N-1개를 1에서 2로 이동
		Hanoi(N-1, start, end, mid);
		
		// 1에 남은 맨 아래꺼 1개를 3으로 이동시키자 
		sb.append(start + " " + end + "\n");
		
		// N-1개를 2에서 3으로 이동 시키면 끝
		Hanoi(N-1, mid, start, end);
	}
}//end of class
