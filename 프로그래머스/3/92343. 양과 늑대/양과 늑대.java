/**
알고리즘 :
    dfs + 비트 마스킹
    
문제 요약 :
    그려진 맵을 탐험하면서 최대 양 수 출력
    * 양 <= 늑대 인 경우 줍지 못함

전략 :
    1. edges 꺼내서 맵 만들기
    2. dfs 로 탐색 시작하기
        - 지나온 경로 꺼내보면서 양 찾기
            - 양 <= 늑대라면 return
            - 양 > 늑대 라면 최대값 갱신해보기
        - 지나온 노드에서 갈 수 있는 길 전부 탐색 
            - 갈 수 있다면 비트로 포함 후 이동 (dfs)
            - 갈 수 없다면 pass
*/

import java.util.*;

class Solution {
    
    static ArrayList<Integer>[] list; // 노드간 연결 상태
    static int answer;
    
    public int solution(int[] info, int[][] edges) {
        
        answer = 0; // 최대 양 수
    
        // 1. edges 꺼내서 맵 만들기
        list = new ArrayList[info.length];
        for(int i=0; i<info.length; i++){
            list[i] = new ArrayList<>(); // 리스트 초기화
        }
        
        for(int i=0; i<edges.length; i++){
            int A = edges[i][0];
            int B = edges[i][1];
            list[A].add(B);
            list[B].add(A); // 양방향 매핑하기
        }
        
        // 그럴일 없겠지만 0번 노드가 늑대인 경우
        if(info[0] == 1) return 0;
        
        // 2. dfs 탐색로 탐색 : info 배열 넘기기 , 0번 노드 사용 처리 (시작은 0번 고정)
        dfs(info, (1<<0));

        return answer;
    }
    
    /**
        dfs
        int[] info : 양 or 늑대가 담겨있는 배열
        mask : 현재 지나온 경로
    */
    static void dfs(int[] info, int mask){
        
        // 현재 방문 경로에서 양 수 세어보기
        int countA = 0; // 양 수
        int countB = 0; // 늑대 수
        
        // 양, 늑대 수 검사
        for(int i=0; i<info.length; i++){
            if( (mask & (1<<i) ) != 0 ){ // 방문 했다면
                if(info[i] == 0){
                    countA++; // 양 수 증가
                } else {
                    countB++; // 늑대 수 증가
                }
            }
        }
        
        // 종료 조건. 양 <= 늑대 라면 못감
        if( countA <= countB) return; 
        answer = Math.max(answer, countA); // 최대 값 갱신
        
        // 지나온 경로에서
        for(int i=0; i<info.length; i++){
            if( (mask & (1<<i)) == 0 ) continue; // 지나온 땅이 아니라면, 넘기기
            // 갈 수 있는 경로
            for(int nextNode : list[i]){
                // 안 가본 땅이 있다면
                if( (mask & (1<<nextNode)) == 0 ){
                    int nextMask = (mask | (1<<nextNode)); // 다음 땅 밟음
                    dfs(info, nextMask); // dfs 이동
                }
            }
        }
    }
}