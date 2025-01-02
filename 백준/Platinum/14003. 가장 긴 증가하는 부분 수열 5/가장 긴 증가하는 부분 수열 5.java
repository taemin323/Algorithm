import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		int[] lis = new int[N];
		int[] parent = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//입력완료
		
		int size = 1;
		lis[0] = arr[0];
		parent[0] = -1;//첫 요소는 부모가 없음
		
		int[] lisIdx = new int[N];//lis의 각 위치에 해당하는 arr의 인덱스 저장 배열
		lisIdx[0] = 0;
		
		for (int i = 1; i < N; i++) {
			int target = arr[i];
			
			if(lis[size-1] < target) {
				parent[i] = lisIdx[size-1];//이전 요소를 부모로 설정
				lis[size] = target;
				lisIdx[size] = i;
				size++;
			} else {
				int idx = bs(lis, size, target);
				lis[idx] =  target;
				lisIdx[idx] = i;
				parent[i] = idx > 0 ? lisIdx[idx-1] : -1;//이전 요소를 부모로 설정
			}
		}
		
		Stack<Integer> stack = new Stack<>();
		int lastIdx = lisIdx[size-1];
		
		while(lastIdx != -1) {
			stack.push(arr[lastIdx]);
			lastIdx = parent[lastIdx];
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.println(size);
		System.out.println(sb.toString());
	}//end of main

	private static int bs(int[] lis, int size, int target) {
		int left = 0;
		int right = size;
		
		while(left < right) {
			int mid = (left + right)/2;
			
			if(lis[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

}//end of class
