import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Egg {
		int s, w;

		public Egg(int s, int w) {
			super();
			this.s = s;
			this.w = w;
		}
	}

	private static int N;
	private static boolean[] visited;
	private static int ans;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		
		Egg[] list = new Egg[N];
		visited = new boolean[N];

		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[i] = new Egg(s, w);
		}
		
		ans = 0;
		
		game(0,list);
		
		System.out.println(ans);
	}//end of main

	private static void game(int idx, Egg[] list) {
		//종료조건: 맨 오른쪽 계란이면 끝.
		if(idx == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if(list[i].s <= 0) {
					sum++;
				}
			}
			ans = Math.max(sum, ans);
			return;
		}
		
		
		if(list[idx].s > 0) {//현재 손에 든 계란이 깨지지 않았는지?
			boolean next = false;
			for (int i = 0; i < N; i++) {
				if(idx == i) continue; // 손에 든 계란과 같은 idx는 패스.
				if(list[i].s <= 0) continue; // 다른 계란이 깨져으면 패스
				next = true;
				//부딪혀보기
				list[idx].s -= list[i].w;
				list[i].s -= list[idx].w;
				game(idx+1,list);
				
				//원복
				list[idx].s += list[i].w;
				list[i].s += list[idx].w;
			}
			//칠 계란이 없었다면 다음 계란 들기
			if(!next) {
				game(idx+1,list);
			}
		} else {
			game(idx+1,list);
		}
	}
}//end of class
