import java.util.*;

class Solution {
    private int n, m;
    private char[][] board;                 // 패딩 포함 격자
    private int[] dr = {0, 0, -1, 1};       // 좌, 우, 상, 하
    private int[] dc = {-1, 1, 0, 0};

    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();

        // 1) 패딩 포함 격자 생성: 바깥을 전부 '.'(공기)로 초기화
        board = new char[n + 2][m + 2];
        for (int i = 0; i < n + 2; i++) Arrays.fill(board[i], '.');

        // 2) 원본을 (1,1) ~ (n,m)에 복사
        for (int i = 0; i < n; i++) {
            String str = storage[i];
            for (int j = 0; j < m; j++) {
                board[i + 1][j + 1] = str.charAt(j);
            }
        }

        // 3) 요청 처리
        for (int i = 0; i < requests.length; i++) {
            char target = requests[i].charAt(0);
            if (requests[i].length() == 2) {
                yesCrane(target);      // 전량 제거
            } else {
                noCrane(target);       // 외곽 접촉분만(한 겹) 제거
            }
        }

        // 4) 남은 컨테이너 수(내부만 집계)
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i][j] != '.') answer++;
            }
        }
        return answer;
    }

    // 크레인: 해당 알파벳 전부 제거 (내부 영역만 훑음)
    private void yesCrane(char target) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i][j] == target) {
                    board[i][j] = '.';
                }
            }
        }
    }

    // 지게차: 바깥 공기에서 닿는 해당 알파벳만 한 겹 제거
    // 규칙: '.'만 큐에 넣어 확장, target을 만나면 즉시 '.'로 바꾸고 visited만 표시(큐 미삽입)
    private void noCrane(char target) {
        boolean[][] visited = new boolean[n + 2][m + 2];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        visited[0][0] = true;              // 가상의 바깥 (패딩 좌상단)
        q.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nr >= n + 2 || nc < 0 || nc >= m + 2) continue;
                if (visited[nr][nc]) continue;

                char cell = board[nr][nc];

                if (cell == '.') {                  // 공기만 통과
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                } else if (cell == target) {        // 경계에서 만난 목표: 이번 요청에서만 제거
                    board[nr][nc] = '.';            // 인플레이스 제거
                    visited[nr][nc] = true;         // 같은 요청 내 재확장 차단
                    // 큐에는 넣지 않음 → 연쇄 제거 방지
                }
                // 다른 알파벳은 벽 취급 (무시)
            }
        }
    }
}
