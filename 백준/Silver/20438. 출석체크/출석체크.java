import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());//학생 수
		int K = Integer.parseInt(st.nextToken());//졸고 있는 학생 수
		int Q = Integer.parseInt(st.nextToken());//출석 코드를 보낼 학생의 수
		int M = Integer.parseInt(st.nextToken());//주어질 구간의 수
		
		boolean[] students = new boolean[N+3];
		
		Set<Integer> sleeping = new HashSet<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			sleeping.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Q; i++) {
			int firstStudent = Integer.parseInt(st.nextToken());
			if(sleeping.contains(firstStudent)) continue;
			if(!students[firstStudent]) {
				for (int j = firstStudent; j < students.length; j+=firstStudent) {
					if(!sleeping.contains(j)) {
						students[j] = true;
					}
				}
			}
		}
		
		int[] prefixSum = new int[N+3];
		for (int i = 3; i < prefixSum.length; i++) {
			prefixSum[i] = prefixSum[i-1]+ ((!students[i]) ? 1:0);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			System.out.println(prefixSum[E] - prefixSum[S-1]);
		}
		
	}//end of main
}//end of class
