import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Info implements Comparable<Info>{
		String word;
		int cnt;
		
		public Info(String word, int cnt) {
			super();
			this.word = word;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Info o) {
			if(this.cnt == o.cnt && this.word.length() == o.word.length()) {
				return this.word.compareTo(o.word);
			}
			
			if(this.cnt == o.cnt) {
				return o.word.length() - this.word.length();
			}
			
			return o.cnt - this.cnt;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			
			if(str.length() < M) continue;
			
			if(!map.containsKey(str)) {
				map.put(str, 1);
			} else {
				map.put(str, map.get(str)+1);
			}
		}
		
		PriorityQueue<Info> pq = new PriorityQueue<>();
		for (String key : map.keySet()) {
			Info info = new Info(key, map.get(key));
			pq.add(info);
		}
		
		while(!pq.isEmpty()) {
			sb.append(pq.poll().word).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}//end of main
}//end of class
