import java.util.*;
public class Main{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int N, v;
		int[] arr;
				
		N = sc.nextInt();//입력숫자갯수
		arr = new int[N];//두번째줄의 숫자를 저장할 빈 배열 생성
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();//두번째줄 숫자 N개를 읽어 배열에 저장
		}
		v = sc.nextInt();
		
		int count = 0;//찾은 횟수
		for(int i=0; i<N; i++) {
			if(v == arr[i]) count++;
		}
		System.out.println(count);
	}
}