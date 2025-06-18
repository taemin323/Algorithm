import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());

        int[] targetDeck = new int[N];                 // 결과 덱
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) targetDeck[i] = Integer.parseInt(st.nextToken());

        /* 초기 덱 1..N */
        int[] initialDeck = new int[N];
        for (int i = 0; i < N; i++) initialDeck[i] = i + 1;

        int maxK = 31 - Integer.numberOfLeadingZeros(N - 1); // 2^K < N → ⌊log₂(N-1)⌋

        for (int k1 = 1; k1 <= maxK; k1++) {
            for (int k2 = 1; k2 <= maxK; k2++) {

                int[] workDeck = initialDeck.clone();  // 작업용 덱

                shuffle(k1, workDeck);   // 첫 번째 (2, k1)-섞기
                shuffle(k2, workDeck);   // 두 번째 (2, k2)-섞기

                if (same(workDeck, targetDeck)) {
                    System.out.println(k1 + " " + k2);
                    return;
                }
            }
        }
    }

    // 두 배열이 완전히 같은가? 
    private static boolean same(int[] a, int[] b) {
        for (int i = 0; i < N; i++) if (a[i] != b[i]) return false;
        return true;
    }

    // (2, K)-섞기: 블록의 ‘아래 절반’을 계속 맨 앞으로 올림  
    private static void shuffle(int K, int[] deck) {

        int blockEnd = N; //이번 단계에서 섞을 블록의 오른쪽 경계

        for (int step = 0; step <= K; step++) {

            int lift = (int) Math.pow(2,  K-step);  // 이번에 위로 올릴 카드 수 2^(K-step)
            int[] nextDeck = new int[N];
            int write = 0;

            // 1 블록의 ‘아래 lift장’ → 맨 앞 
            for (int j = blockEnd - lift; j < blockEnd; j++)
                nextDeck[write++] = deck[j];

            // 2 블록의 나머지(윗부분)
            for (int j = 0; j < blockEnd - lift; j++)
                nextDeck[write++] = deck[j];

            // 3 블록 밖 카드들은 그대로
            for (int j = blockEnd; j < N; j++)
                nextDeck[write++] = deck[j];

            // 상태 갱신 – deck ← nextDeck 
            System.arraycopy(nextDeck, 0, deck, 0, N);

            // 다음 단계: 블록 길이를 절반으로 줄임 
            blockEnd = lift;
        }
    }
}
