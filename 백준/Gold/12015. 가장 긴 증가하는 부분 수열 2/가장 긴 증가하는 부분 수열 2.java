import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[] arr;
	private static int[] lis;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());// 1<= N <= 1,000,000 
		
		arr = new int[N];
		lis = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
		}
		int size = 0;
		for (int i: arr) {
			int idx = bs(lis, size, i);// 삽입 위치 찾기
			
			if(idx == size) {
				lis[size++] = i;
			} else {
				lis[idx] = i;
			}
		}
		System.out.println(size);
	}//end of main

	private static int bs(int[] lis, int size, int key) {
		int left = 0;
		int right = size;
		
		while(left < right) {
			int mid = (left+right)/2;
			
			if(lis[mid] >= key) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return left;
	}
}//end of class
