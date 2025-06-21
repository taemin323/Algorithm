import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		int aCnt = 0; //a의 총 개수 = a가 연속으로 나올 수 있는 길이.
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'a') aCnt++;
		}
		// a가 가장 많이 포함되어 있는 구간(aCnt만큼의 길이) 찾아서 그 구간에서 b의 개수가 결국 교환의 횟수.
		// 대신 [0]과 [마지막 인덱스]가 둘 다 0인 문자열은 이어지는 걸 생각해야함. 문자열이 원형이기 때문에.
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < str.length(); i++) {
			int bCnt = 0;
			int start = i;
			
			for (int j = start; j < aCnt + start; j++) {
				if (str.charAt(j % str.length()) == 'b') {
					bCnt++;
				}
			}
			min = Math.min(bCnt, min);
		}
		
		System.out.println(min);
	}
}
