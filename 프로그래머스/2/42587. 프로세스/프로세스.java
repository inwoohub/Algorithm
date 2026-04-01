/**
1. 실행 대기 큐에서 대기중인 프로세스 하나 꺼냄
2. 큐 대기 > 꺼낸거 -> 꺼낸거 다시 넣기
3. 큐 대기 < 꺼낸거 -> 실행 후 종료

priorities: 프로세스들 (순서대로 중요도가 있음)
location: 해당 Index 가 몇 번째로 동작하는지?

전략 :
1. 우선순위큐 하나 만들어주기
    -> 이걸로 우선순위 있는지 없는지 확인 용도
2. 일반 큐 하나 만들어주기
    -> 이걸로 1~3 번 확인 용도
    
우선순위큐에서 우선순위 가장 높은거 꺼냄.
해당 value 가 지금 큐에서 꺼낸거와 같다면, 실행 후 지워주기
다르다면, 다시 큐에 넣어주기
*/

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        // 우선순위 큐 생성 
        // [0]: index (처음에 몇번째였는지)
        // [1]: 우선 순위 
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> Integer.compare(b[1], a[1])); // 내림차순
        
        // 일반 큐 생성 (실행 대기 큐)
        // [0]: index
        // [1]: 우선 순위
        // [2]: 뺑뺑이 순서
        Queue<int[]> q = new LinkedList<>();
        
        int count = 0;
        
        // 순회하면서 우선순위 큐와 일반 큐에 쌓아주기
        for(int next : priorities){
            pq.offer(new int[]{count, next});
            q.offer(new int[]{count, next});
            count++;
        }
        
        count = 1;
        
        // 우선 순위 큐가 바닥날 때 까지 반복
        while(!pq.isEmpty()){
            int[] pqCur = pq.poll();
            int pqIdx = pqCur[0];
            int pqValue = pqCur[1];
            
            // 실행 대기 큐에서 하나씩 꺼내보면서 탐색
            while(!q.isEmpty()){
                int[] qCur = q.poll();
                int qIdx = qCur[0];
                int qValue = qCur[1];
                
                if(qValue == pqValue){ // 우선순위 큐에서 꺼낸것과 우선순위가 같다면
                    if(qIdx == location){ // 목적지 도착 시
                        return count; // 얼마나 돌았는지 (정답 반환)
                    }
                    count++;
                    break;
                }
                else{
                    q.offer(new int[]{qIdx, qValue}); // 순서 맨뒤로 밀면서 큐에 넣어주기
                }
            }
        }
        
        int answer = 0;
        return answer;
    }
}