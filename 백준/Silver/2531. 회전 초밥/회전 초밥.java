import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] sushi = new int[N];
		int[] dishes = new int[d+1];
		
		
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		// 처음 0번부터 k개수만큼 먹었을 때의 초기화
		int cnt = 0;
		for (int i = 0; i < k; i++) {
			if(dishes[sushi[i]] == 0) {
				cnt++;
			}
			dishes[sushi[i]]++;// 해당 번호의 초밥을 먹었다면 개수 추가.
		}
		
		int max = cnt + (dishes[c] == 0 ? 1 : 0);
		
		
		// 1번부터 N-1번까지 슬라이딩 윈도우하면된다.
		for (int i = 1; i < N; i++) {
			
			int end = (i + k - 1) % N;
			if(dishes[sushi[end]] == 0) {
				cnt++;
			}
			dishes[sushi[end]]++;
			
			// 한칸 이동 했으니 이전의 초밥 제거
			dishes[sushi[i-1]]--;
			if(dishes[sushi[i-1]] == 0) {
				cnt--;// 해당 초밥이 0이면 윈도우 안에 없다는 것이므로 cnt -1
			}
			
			int cur = cnt + (dishes[c] == 0 ? 1 : 0);
		    if (cur > max) max = cur;
		}
		
		System.out.println(max);
		
		
	}//end of main
}//end of class
