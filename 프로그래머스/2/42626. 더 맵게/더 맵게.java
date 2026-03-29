/**
알고리즘
우선순위 큐
*/

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        // 모든 음식의 스코빌지수를 담을 우선순위 큐 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 순회하며 큐에 넣어주기
        for(int next : scoville){
            pq.offer(next);
        }
        
        int count = 0;
        
        // 모든 음식 스코빌 지수 K 이상 만들기 (반복)
        while(true){
            
            if(pq.size() == 1){
                int A = pq.poll();
                if( A >= K ) return count;
                else return -1;
            }
            
            int A = pq.poll(); // 가장 맵지 않은 음식의 스코빌 지수
            int B = pq.poll(); // 두 번째로 맵지 않은 음식의 스코빌 지수
            
            if(B == 0) return -1;  // 못 만드는 경우 필터
            
            if( A >= K ) break;// 모든 음식의 스코빌 지수가 K 이상인 경우 STOP!
            
            int C = A + ( B * 2 ); // 섞은 음식의 스코빌 지수
            count++; // 카운트 증가
            pq.offer(C);
            
        }
        
        int answer = count;
        return answer;
    }
}