class Solution {
    public int solution(int[][] sizes) {
        int N = sizes.length;
        int M = sizes[0].length;
            
        int maxWidth = 0;
        int maxLength = 0;
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++){
                maxWidth = Math.max(maxWidth, sizes[r][c]);
            }
        }
        
        for (int i = 0; i < N; i++) {
            int length = Math.min(sizes[i][0], sizes[i][1]);
            maxLength = Math.max(maxLength, length);
        }
        
        int answer = maxWidth * maxLength;
        return answer;
    }
}