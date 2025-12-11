import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static ArrayList<Integer> nums;
	private static ArrayList<Character> ops;
	private static int answer;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		nums = new ArrayList<>();
		ops = new ArrayList<>();
		
		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			if(i % 2 == 0) {
				nums.add(str.charAt(i) - '0');
			} else {
				ops.add(str.charAt(i));
			}
		}
		
		answer = Integer.MIN_VALUE;
		dfs(nums.get(0), 0);
		System.out.println(answer);
	}

	private static void dfs(int result, int opIdx) {
		//주어진 연산자의 개수를 초과하였을 경우
		if(opIdx >= ops.size()) {
			answer = Math.max(answer, result);
			return;
		}
		
		// 괄호가 없는 경우
		int result1 = calculate(ops.get(opIdx), result, nums.get(opIdx+1));
		dfs(result1, opIdx+1);
		
		// 괄호가 있는 경우
		if(opIdx + 1 < ops.size()) {
			int result2 = calculate(ops.get(opIdx+1), nums.get(opIdx+1), nums.get(opIdx+2));
			dfs(calculate(ops.get(opIdx), result, result2), opIdx+2);
		}
	}// end of main

	private static int calculate(char op, int n1, int n2) {
		switch(op) {
		case '+':
			return n1 + n2;
		case '-':
			return n1 - n2;
		case '*':
			return n1 * n2;
		}
		return -1;
	}
}// end of class