/**
알고리즘 :
    브로드 포스 탐색 (모든 경우의 수) , 비트 마스킹

문제 요약 :
    1. 피로도 사용해서 던전 탐험
    2. 던전마다 탐험 시작하기 전 "최소 필요 피로도", "소모 피로도" 존재
    3. "최소 필요 피로도"로 던전 입장 , "소모 피로도" 로 던전 나올때 감소
    4. 최대한 던전을 많이 도는 방법 출력
    
전략 :
    들어가기전,
    정렬 필요할까, 정렬 시 우선 순위가 있으면 좋을까?
        -> 물론 최악에 경우는 모두 다 탐색하는 경우임
        -> 하지만 최악의 경우는 최대 8, 즉 모든 경우로 해봐도 문제 없음 (즉, 브로드포스)    
    dfs + backtracking 으로 모든 조합 찾아보면 물론 금방 가능한 수치임
    
    하지만, 나는 비트마스킹을 써서 해보고싶다.
    따라서 비트 마스킹을 써서 문제를 풀이해보겠다. (비트마스킹은 최대 1~30 까지 가능, 문제는 최대 8 이라고함)
    
    1. 아예 방문 안한경우 (초기세팅)
    2. 한개만 방문한경우 [][] 2차원 배열로 표기 -> 값을 현재 체력으로 둠
        ex) [1][1<<1] : 현재 위치 1, 1번 방문
            [2][1<<2] : 현재 위치 2, 2번 방문 등
    3. 방문 가능하다면 체력 감소 및 다음 곳에 체력 넣어주기
    4. 그리고 정답 answer 최대값 갱신
*/

import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = 0;
        int dL = dungeons.length; // 던전 길이
        // 1. (dp) 비트 마스킹용 배열 생성
        int[][] dp = new int[dL][1<<dL];
        
        // 방문 못하는 길은 -1 dp 배열 초기화
        for(int i=0; i<dL; i++){
            Arrays.fill(dp[i],-1);
        }
        
        // dp 배열 초기 세팅
        for(int i=0; i<dL; i++){
            if( k >= dungeons[i][0]){
                if( k - dungeons[i][1] >= 0){
                    dp[i][(1<<i)] = k - dungeons[i][1]; // 초기 체력 넣어주기
                    answer = Math.max(answer, 1);
                }
            }
        }
        
        // 2. 방문 가능한 길 모두 탐색
        for(int mask = 0; mask<(1<<dL); mask++){
            for(int cur = 0; cur<dL; cur++){
                if( (mask & (1<<cur)) == 0 ) continue; // 현재 방문했는데 방문 처리 안 되어있음
                if( dp[cur][mask] == -1 ) continue; // 현재 던전인데 방문은 안함처리 되어있음
                for(int next = 0; next<dL; next++){
                    if( (mask & (1<<next)) != 0) continue; // 이미 방문한 던전
                    int nextMask = (mask|(1<<next));
                    if(dp[cur][mask] >= dungeons[next][0]){ // 던전 입장 가능 하다면,
                        if(dp[cur][mask] - dungeons[next][1] >= 0){ // 탈출도 가능하다면,
                            dp[next][nextMask] = Math.max(dp[next][nextMask], dp[cur][mask] - dungeons[next][1]); // 3. 더 큰 수로 저장
                            answer = Math.max(answer, Integer.bitCount(nextMask)); // 4. 정답 최대 값 갱신
                        }
                    }
                }
            }
        }
        return answer;
    }  
}