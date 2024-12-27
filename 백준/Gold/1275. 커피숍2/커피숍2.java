import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int Q;
	private static int[] arr;
	private static long[] segTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		segTree = new long[4 * N];

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} // 원본 배열

		makeSeg(1, 1, arr.length - 1);

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(x > y) {
				int temp = x;
				x = y;
				y = temp;
			}

			long result = sumSeg(1, 1, arr.length - 1, x, y);
			sb.append(result).append("\n");
			updateSeg(1, 1, arr.length - 1, a, b);

		} // 입력 완료
		System.out.println(sb.toString());
	}// end of main

	private static long updateSeg(int idx, int s, int e, int tIdx, int tValue) {
		// 범위 밖으로 나가면 return
		if (s > tIdx || e < tIdx) {
			return segTree[idx];
		}

		// 변경할 Leaf Node를 찾았다.
		if (s == tIdx && e == tIdx) {
			arr[tIdx] = tValue;
			segTree[idx] = tValue;
			return segTree[idx];
		}

		// 돌아와서 연관있는 노드들도 갱신
		int mid = (s + e) / 2;
		long left = updateSeg(idx * 2, s, mid, tIdx, tValue);
		long right = updateSeg(idx * 2 + 1, mid + 1, e, tIdx, tValue);
		segTree[idx] = left + right;
		return segTree[idx];
	}

	private static long sumSeg(int idx, int s, int e, int ts, int te) {
		// 현재 바라보고 있는 범위가 완벽하게 target의 범위에 포함.
		if (ts <= s && e <= te)
			return segTree[idx];

		// 완벽하게 범위 밖.
		if (s > te || e < ts)
			return 0;

		// 걸치는 경우(일부만 포함)
		int mid = (s + e) / 2;
		long left = sumSeg(idx * 2, s, mid, ts, te);// 왼쪽
		long right = sumSeg(idx * 2 + 1, mid + 1, e, ts, te);// 오른쪽
		return left + right;
	}

	private static long makeSeg(int idx, int s, int e) {
		if (s == e) {
			segTree[idx] = arr[s];
			return segTree[idx];
		}

		int mid = (s + e) / 2;
		long left = makeSeg(idx * 2, s, mid);
		long right = makeSeg(idx * 2 + 1, mid + 1, e);
		segTree[idx] = left + right;
		return segTree[idx];
	}

}// end of class
