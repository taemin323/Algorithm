import java.util.*;
import java.io.*;

public class Main {
	private static Integer[] sa;
	private static int[] rank, tmp, lcp;
	private static int k, n;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String A = br.readLine();
		String B = br.readLine();
		
		String sumAB = A + "#" + B;
		
		n = sumAB.length();
		int lenA = A.length();
		
		suffix(sumAB);
		LCP(sumAB);
		
		int maxLCP = 0;
		int startIdx = 0;
		
		for (int i = 1; i < n; i++) {
			//하나는 A 소속, 하나는 B소속인지 확인
			boolean isA_prev = sa[i-1] < lenA;
			boolean isA_curr = sa[i] < lenA;
			
			//이웃한 두 접미사의 춮신이다르고, 구분자 #을 포함하지 않는 경우
			if(isA_prev != isA_curr && sa[i-1] != lenA && sa[i] != lenA) {
				if(maxLCP < lcp[i]) {
					maxLCP = lcp[i];
					startIdx = sa[i];
				}
			}
		}
		
		System.out.println(maxLCP);
		if(maxLCP > 0) {
			System.out.println(sumAB.substring(startIdx, startIdx+maxLCP));
		}
	}// end of main

	private static void LCP(String sumAB) {
		lcp = new int[n];
		int[] isa = new int[n];//sa의 역배열(i번 접미사가 sa의 몇 번째 줄에 있는지 저장)
		for (int i = 0; i < n; i++) {
			isa[sa[i]] = i;
		}
		
		int h = 0; // 겹치는 글자 수
		for (int i = 0; i < n; i++) {
			// 만약 sa에서 첫 번째 줄이 아니라면(비교할 윗집이 있디면)
			if(isa[i] > 0) {
				// j는 내 바로 윗집 접미사의 인덱스 번호
				int j = sa[isa[i] - 1];
				
				while(i + h < n && j + h < n && sumAB.charAt(i+h) == sumAB.charAt(j+h)) {
					h++;
				}
				
				lcp[isa[i]] = h;
				
				//다음 접미사로 넘어갈 때, 맨 앞글자 하나만 빠지는 꼴이므로
				// 최소 h-1만큼음 누조건 겹침을 보장함.
				if(h > 0) h--;
			}
		}
		
	}

	private static void suffix(String sumAB) {
		sa = new Integer[n];//접미사 시작 인덱스를 담을 배열(번호표)
		rank = new int[n];//각 인덱스의 현재 순위
		tmp = new int[n];//다음 순위를 계산할 때 쓸 임시 배열
		
		for (int i = 0; i < n; i++) {
			sa[i] = i;// 처음에는 0,1,2... 순서대로 번호표 부여
			rank[i] = sumAB.charAt(i);// 처음에는 각 문자의 아스키 값으로 순위 매김
		}
		
		// k를 1,2,4,8...배수로 늘려가며 정렬
		for (int k = 1; k < n; k <<= 1) {
			final int curK = k;
			final int[] curRank = rank;
			
			//정렬 기준 정의
			Comparator<Integer> cmp = (i, j) -> {
				//1. 앞의 k글자 순위가 다르면 그것부터 비교
				if (curRank[i] != curRank[j]) return curRank[i] - curRank[j];
				
				//2. 앞의 k글자가 같다면, i+k번째의 순위(그다음 k글자)를 가져와서 비교
				int ri = (i + curK < n) ? rank[i+curK] : -1;
				int rj = (j + curK < n) ? rank[j+curK] : -1;
				return ri - rj;
			};
			
			// sa를 k글자 기준으로 정렬
			Arrays.sort(sa, cmp);
			
			//정렬된 결과를 바탕으로 다음 턴을 위한 새 순위 매기기
			tmp[sa[0]] = 0;
			for (int i = 1; i < n; i++) {
				//바로 앞집(sa[i-1]과 나(sa[i])의 점수 쌍이 똑같다면 공동 순위 부여
				if(cmp.compare(sa[i-1], sa[i]) == 0) {
					tmp[sa[i]] = tmp[sa[i-1]];
				} else {
					tmp[sa[i]] = tmp[sa[i-1]] + 1;
				}
			}
			
			// 임시 순위를 실제 순위에 덮어쓰기
			for (int i = 0; i < n; i++) {
				rank[i] = tmp[i];
			}
			
			//만약 모든 순위가 다 달라졌다면 이미 정렬 끝난 것이니 조기 종료 가능
			if(rank[sa[n-1]] == n-1) break;
		}
	}
}// end of class