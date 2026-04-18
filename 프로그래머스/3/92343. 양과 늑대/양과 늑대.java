/**
알고리즘 :
    이전 4중 for문 -> dfs -> bfs (3번째 try) , 비트마스킹
    
문제 요약 :
    그려진 맵을 탐험하면서 최대 양 수 출력
    * 양 <= 늑대 인 경우 줍지 못함
    
전략 :
    1. ArrayList<Integer>[] list 만들기 : 노드 <-> 노드 연결 정보 담기
    2. Queue<Integer> 큐 만들기 : Integer -> 비트 마스킹 (어디어디 방문했는지 표기 용도)
    3. 큐에 담긴 방문 정보 확인하기
        - 양 수 세어보기
        - 늑대 수 세어보기
        - 양 vs 늑대 수 비교
            - 양이 더 많다면 양 최대 수 갱신 try
                - 다음 갈 수 있는 경로 방문 후 큐에 넣어주기
            - 늑대가 더 많거나, 같다면 해당 객체 소멸
    4. 최대 answer 반환
*/

import java.util.*;

class Solution {
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0; // 정답 반환 초기값
        boolean[] visited = new boolean[1<<info.length]; // 노드 중복 방지 방문 배열
        
        // 1. ArrayList<Integer>[] list 만들기
        ArrayList<Integer>[] list = new ArrayList[info.length];
        for(int i=0; i<info.length; i++){
            list[i] = new ArrayList<>();
        } // list 생성 및 초기화
        
        for(int i=0; i<edges.length; i++){
            int A = edges[i][0];
            int B = edges[i][1];
            list[A].add(B);
            list[B].add(A); // 양방향 매핑
        } 
        
        // 2. Queue<Integer> 큐 만들기
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer((1<<0)); // 0번 노드 방문을 기점으로 시작
        
        while(!q.isEmpty()){ // bfs
            int curMask = q.poll(); // 현재 방문 마스크
            if(visited[curMask]){
               continue; 
            }
            visited[curMask] = true; // q 꺼내는 시점에서 방문처리
            
            // 양, 늑대 수 세어보기
            int sheep = 0;
            int wolf = 0;
            for(int i=0; i<info.length; i++){
                // 방문 했던 경로라면,
                if( (curMask&(1<<i)) != 0 ){
                    // 양 or 늑대
                    if(info[i] == 0){
                        sheep++;
                    } else { 
                        wolf++;
                    }
                }
            }
            
            if(sheep <= wolf) continue; // 양 <= 늑대 : 패스
            answer = Math.max(answer, sheep); // 최대값 갱신
            
            // 연결되어 있는 노드에서 또 나아갈 수 있는 길 있다면 큐에 넣어주기
            for(int curNode=0; curNode<info.length; curNode++){
                // curNode : 현재 방문한 경로
                if( (curMask & (1<<curNode)) != 0 ){ // 방문 담겨있는 노드라면,
                    for(int nextNode : list[curNode] ){
                        if( (curMask & (1<<nextNode)) == 0 ){ // 다음 노드 방문 안한 경우 추가
                            int nextMask = (curMask | (1<<nextNode));
                            q.offer(nextMask); // 큐에 추가                            
                        }
                    }
                }
            } 
        }
        // 4. 정답 반환
        return answer;
    }
}