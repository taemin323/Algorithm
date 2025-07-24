import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // board[i] = i가 기본값이지만,
        // 사다리나 뱀의 시작점이 i라면 board[i] = 도착지
        int[] board = new int[101];
        for (int i = 1; i <= 100; i++) {
            board[i] = i;
        }

        // 사다리 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end   = Integer.parseInt(st.nextToken());
            board[start] = end;  // 사다리를 타면 end로 이동
        }

        // 뱀 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end   = Integer.parseInt(st.nextToken());
            board[start] = end;  // 뱀을 만나면 end로 이동
        }

        // dist[i]: i번 칸에 도달하기 위한 최소 주사위 던진 횟수
        // 방문하지 않은 칸은 -1로 초기화
        int[] dist = new int[101];
        for (int i = 1; i <= 100; i++) {
            dist[i] = -1;
        }

        Queue<Integer> q = new LinkedList<>();
        dist[1] = 0;  // 시작점(1번 칸)은 주사위 0번 던진 상태
        q.offer(1);

        // BFS 탐색 시작
        while (!q.isEmpty()) {
            int cur = q.poll();

            // 1~6까지 주사위 던지기
            for (int dice = 1; dice <= 6; dice++) {
                int next = cur + dice;
                if (next > 100) continue;  // 100칸을 넘어가면 무시

                // 사다리나 뱀 있으면 바로 이동
                next = board[next];

                // 아직 방문하지 않은 칸이라면
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;  // 주사위 한 번 더 던진 것
                    q.offer(next);
                }
            }
        }

        // 100번 칸에 도달하기 위한 최소 던진 횟수 출력
        System.out.println(dist[100]);
    }
}
