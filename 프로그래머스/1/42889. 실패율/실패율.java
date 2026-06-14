/**
알고리즘 :
    구현 (정렬, 우선순위 큐)
    
문제 요약 :
    전체 스테이지의 개수 : N
    게임을 이용하는 사용자가 현재 멈춰 있는 스테이지 번호 stages
    실패율이 높은 스테이지부터 내림차순
    
    실패율 = 스테이지 도달 클리어 x 수 / 스테이지 도달한 플레이어 수
    * N+1 은 모두 클리어
    * 0 은 도달 유저 x
    
전략 :
    1. 1~N 까지 각 개수 세어보기
    2. N+1 개수 세어보기
    3. 총 total 개수 세어보기
    4. 1~N 까지 빼가면서 total 감소 및 실패율 계산
    5. 실패율에 따른 정렬로 실패율 높은 것 부터 오름 차순
    
*/

import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        
        // 배열로 현재 도전중인 스테이지 번호 수 변환
        float[] person = new float[N+2];
        for(int stage : stages){
            person[stage] = person[stage] + 1f;
        }
        
        int total = stages.length - (int) person[0]; // 스테이지 도달 x 제외 수
        
        // 실패율이 높은 순으로 있는 우선순위 큐
        // 실패율이 같다면 스테이지 작은게 우선순위
        // 아니라면 실패율이 높은 것 부터
        PriorityQueue<result> pq = new PriorityQueue<>( (a,b) -> {
            if( a.fail == b.fail ) return Integer.compare(a.stage, b.stage);
            return Float.compare(b.fail, a.fail);
        });
        
        // 1부터 실패율 계산 후 값 큐에 넣기
        for(int i=1; i<person.length-1; i++){
            if(person[i] == 0 && total == 0){
                pq.offer( new result(i, 0) );    
            } else {
                float fail = person[i] / total;
                pq.offer( new result(i, fail ) );   
            }
            total -= person[i];
        }
        
        // 하나씩 뺴면서 배열에 담고 정답 반환
        int[] answer = new int[pq.size()];
        int index = 0;
        while(!pq.isEmpty()){
            answer[index++] = pq.poll().stage;
        }
        return answer;
    }
    
    static class result {
        int stage;
        float fail;
        result(int stage, float fail){
            this.stage = stage;
            this.fail = fail;
        }
    }
    
}