// https://school.programmers.co.kr/learn/courses/30/lessons/150365
// dfs 사용 / 모든 경로 탐색 / 백트래킹을 쓰면 왔던경로 다시 못가서 사용 x


import java.util.*;import java.util.*;

class Solution {
    
    static int xSize, ySize;
    static int[] dx = {1, 0, 0, -1};        // d, l, r, u
    static int[] dy = {0, -1, 1, 0};
    static char[] dir = {'d', 'l', 'r', 'u'};
    
    // 격자 범위 체크
    static boolean inRange(int x, int y){
        return (1 <= x && x <= xSize && 1 <= y && y <= ySize);
    }
    
    static String search(int curX, int curY, int endX, int endY,
                         int curDist, int lastDist, String curStr){
        
        StringBuilder sb = new StringBuilder(curStr);
        
        // k번(= lastDist번) 이동할 때까지 한 칸씩 선택
        while (curDist < lastDist) {
            boolean moved = false;  // 이번 스텝에서 실제로 이동했는지 여부
            
            // 사전순: d, l, r, u 순서로 시도
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                
                // 격자 밖이면 패스
                if (!inRange(nextX, nextY)) continue;
                
                int nextDist = curDist + 1;
                int remain = lastDist - nextDist;  // 앞으로 남은 이동 수
                
                // 현재 후보 위치에서 도착점까지 최소 거리(맨해튼 거리)
                int dist = Math.abs(nextX - endX) + Math.abs(nextY - endY);
                
                // 1) 남은 이동 수로 도착점까지 갈 수 있는지
                // 2) 남은 이동 수 - 최소 거리 가 짝수인지(왔다갔다로 채울 수 있는지)
                if (dist <= remain && (remain - dist) % 2 == 0) {
                    // 이 방향으로 가는 게 유효하니까 확정
                    curX = nextX;
                    curY = nextY;
                    curDist = nextDist;
                    sb.append(dir[i]);
                    moved = true;
                    break;  // 이번 step 끝, 다음 step으로
                }
            }
            
            // 어떤 방향으로도 못 갔다 → 불가능한 상태
            if (!moved) {
                return "impossible";
            }
        }
        
        // k번 이동 끝났을 때 도착점에 와 있는지 최종 확인
        if (curX == endX && curY == endY && curDist == lastDist) {
            return sb.toString();
        }
        return "impossible";
    }
    
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        xSize = n;
        ySize = m;
        
        // 최소 이동 거리 (맨해튼 거리)
        int minDist = Math.abs(x - r) + Math.abs(y - c);
        if (minDist > k) return "impossible";          // k번 안에 도착 자체가 불가능
        if ((k - minDist) % 2 != 0) return "impossible"; // 홀짝 안 맞으면 정확히 k번에 도착 불가
        
        // 위 검사 통과했으면, 실제 경로 구성
        String answer = search(x, y, r, c, 0, k, "");
        return answer;
    }
}
